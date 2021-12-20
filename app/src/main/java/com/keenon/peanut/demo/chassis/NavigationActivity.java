package com.keenon.peanut.demo.chassis;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.keenon.peanut.demo.R;
import com.keenon.peanut.demo.bean.MyPoint;
import com.keenon.peanut.demo.manager.NavManager;
import com.keenon.peanut.demo.util.BaseActivity;
import com.keenon.sdk.component.navigation.common.Navigation;
import com.keenon.sdk.component.navigation.route.RouteNode;
import com.keenon.sdk.component.runtime.PeanutRuntime;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

// This is the design of interface/page that your robot is moving.
public class NavigationActivity extends BaseActivity implements Navigation.Listener {

  FirebaseDatabase database = FirebaseDatabase.getInstance();
  DatabaseReference reference = database.getReference().child("Users");
  FirebaseAuth auth = FirebaseAuth.getInstance();
  private int taskComplete = 0;

  Runnable runnable;
  Handler handler;

  @BindView(R.id.tv_status)
  TextView tvStatus;
  @BindView(R.id.btn_manual_next)
  Button btnNext;
  @BindView(R.id.btn_pause)
  Button btnPause;
  @BindView(R.id.btn_resume)
  Button btnResume;
//  @BindView(R.id.sv_api_log)
//  ScrollView svApiLog;
//  @BindView(R.id.tv_api_log)
//  TextView tvApiLog;
  private int timeout = 0;
  private int numRobot;
  private boolean arrival;
  private int repeat = -1;
  private int speed = 80;
  private List<MyPoint> list = new ArrayList<>();
  private MyPoint curPoint;
  private StringBuilder sb = new StringBuilder();

  private Timer timer;
  private TimerTask task;

  @Override
  protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_navigation_pilot);
    ButterKnife.bind(this);

    setButtonBack();
    btnNext.setVisibility(View.INVISIBLE);

    btnNext.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        navigatenext();
      }
    });

    btnPause.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        NavManager.getInstance().readyGo(false);
      }
    });

    btnResume.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        NavManager.getInstance().readyGo(true);
      }
    });

    // We use the default value from NavigationDemo to let robot run.
    // Developers can change this default value.
    timeout = getIntent().getIntExtra("timeout", 0);
    arrival = getIntent().getBooleanExtra("arrival", false);
    repeat = getIntent().getIntExtra("repeat", -1);
    speed = getIntent().getIntExtra("speed", 80);
    numRobot = getIntent().getIntExtra("RobotNumber", 0);
    list = (List<MyPoint>) getIntent().getSerializableExtra("list");
//    for (int i = 0; i < list.size(); i++) {
//      PrintLnLog.d(NavigationActivity.this, tvApiLog, svApiLog, sb, "list  " + i + list.get(i).getRouteNode().getName());
//    }
    // This is the code from engineers in the Keenon Company
    NavManager.getInstance().init(this, timeout, repeat, arrival);
    NavManager.getInstance().setSpeed(speed);
    NavManager.getInstance().setTargets(list);
    NavManager.getInstance().prepare();
    curPoint = NavManager.getInstance().getCurNode();
    tvStatus.setText(getString(R.string.go_to) + curPoint.getRouteNode().getName());

    // We need the exact robot info on the firebase
    reference = reference.child(auth.getUid()).child("Robot" + numRobot);

    // Every 2000 ms, we update the information to firebase, such as the power,
    // the distance etc.
    handler = new Handler();
    runnable = new Runnable() {
      @Override
      public void run() {
        // send power and distance info to firebase and update the info on the
        // service app.
        int power = PeanutRuntime.getInstance().getRuntimeInfo().getPower();
        Double odo = PeanutRuntime.getInstance().getRuntimeInfo().getTotalOdo();
        String odoText;
        odoText = new DecimalFormat("#.##").format(odo);
        reference.child("Power").setValue(power);
        reference.child("Distance").setValue(odoText);
        handler.postDelayed(runnable, 2000);
      }
    };

    handler.post(runnable);

    // Update the speed if the user set 0 from serviceApp
    reference.child("TaskCompleted").addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot snapshot) {
        taskComplete = Integer.parseInt(snapshot.getValue().toString());
      }

      @Override
      public void onCancelled(@NonNull DatabaseError error) {

      }
    });

    // Update the speed if the user send info from serviceApp
    reference.child("SetSpeed").addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot snapshot) {
        speed = Integer.parseInt(snapshot.getValue().toString());
        NavManager.getInstance().setSpeed(speed);
      }

      @Override
      public void onCancelled(@NonNull DatabaseError error) {

      }
    });

    // Once our robot is reached the last point, we change the flag Over to 0
    // and go back to NavigationDemo interface simply using finish()
    reference.child("Over").addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot snapshot) {
        if(snapshot.getValue().equals("1")) {
          reference.child("Over").setValue("0");
          finish(); // change back to NavigationDemo interface
        }
      }

      @Override
      public void onCancelled(@NonNull DatabaseError error) {

      }
    });

  }

  // This is the code from engineers in Keenon Company
  @Override
  protected void onDestroy() {
    super.onDestroy();
    NavManager.getInstance().stop();
    NavManager.getInstance().release();
    if (timer != null) {
      timer.cancel();
    }
    if (task != null) {
      task.cancel();
    }
  }

  // This is the code from engineers in Keenon Company, I only translate the toast messages
  // into English
  @Override
  public void onStateChanged(int state, int schedule) {
    //PrintLnLog.d(NavigationActivity.this, tvApiLog, svApiLog, sb, "state = " + state);
    switch (state) {
      case Navigation.STATE_DESTINATION:
        arrived();
        break;
      case Navigation.STATE_COLLISION:
      case Navigation.STATE_BLOCKED:
        Toast.makeText(this, R.string.avoid_tip, Toast.LENGTH_SHORT).show();

        break;
      case Navigation.STATE_BLOCKING:
        Toast.makeText(this, R.string.block_timeout_tip, Toast.LENGTH_SHORT).show();

        break;
    }
  }

  // I modify the arrived() function, simply add some codes to manipulate the firebase
  // isManualControl means this is the last point.
  // other points are autoControl.
  // Once it reached the point, you add the taskComplete number.
  // You can change the logic such as if it is the same point from previous point,
  // the number of task won't be added.
  private void arrived() {
    taskComplete++;
    reference.child("TaskCompleted").setValue(taskComplete);
    // 2021.10.14 Since the format of output looks like this: "Point:number", I removed the "Point:"
    reference.child("Destination").setValue(curPoint.getRouteNode().getName().substring(6));
    if (curPoint.isManualControl()) {
      btnNext.setVisibility(View.VISIBLE);
      tvStatus.setText(R.string.arrive_tip_2);
      reference.child("Over").setValue("1");
    } else {
      btnNext.setVisibility(View.INVISIBLE);
      tvStatus.setText(getString(R.string.arrive_tip_1, curPoint.getDuration() + ""));
      autoRunNextPoint();

    }
  }

  // This is the code from engineers in Keenon Company
  private void autoRunNextPoint() {
    timer = new Timer();
    task = new TimerTask() {
      @Override
      public void run() {
        navigatenext();
      }
    };
    timer.schedule(task, curPoint.getDuration());
  }

  // This is the code from engineers in Keenon Company
  private void navigatenext() {
    if (NavManager.getInstance().getNextNode() == null) {
      Toast.makeText(this, R.string.no_more_point, Toast.LENGTH_SHORT).show();

    } else {
      // nextDes() readyGo() getCurNode() runOnUiThread()
      NavManager.getInstance().nextDes();
      NavManager.getInstance().readyGo(true);
      curPoint = NavManager.getInstance().getCurNode();
      runOnUiThread(new Runnable() {
        @Override
        public void run() {
          tvStatus.setText(getString(R.string.go_to) + curPoint.getRouteNode().getName());
          //Keenon engineer solution didn't work: see next lime, not sure about inner system
          //Toast.makeText(NavigationActivity.this, R.string.no_more_point, Toast.LENGTH_SHORT).show();
        }
      });
    }
  }

  @Override
  public void onRouteNode(int index, RouteNode routeNode) {
    //PrintLnLog.d(NavigationActivity.this, tvApiLog, svApiLog, sb, "onRouteNode = " + "index:" + index + " , routeNode" + routeNode.getName());
  }

  @Override
  public void onRoutePrepared(RouteNode... routeNodes) {
    //PrintLnLog.d(NavigationActivity.this, tvApiLog, svApiLog, sb, "onRoutePrepared = " + routeNodes.length);
    NavManager.getInstance().readyGo(true);
  }

  @Override
  public void onDistanceChanged(float distance) {

  }

  @Override
  public void onError(int code) {
    //PrintLnLog.d(NavigationActivity.this, tvApiLog, svApiLog, sb, "error = " + code);
  }
}
