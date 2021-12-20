package com.keenon.peanut.demo.chassis;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.keenon.peanut.demo.R;
import com.keenon.peanut.demo.bean.MyPoint;
import com.keenon.peanut.demo.util.BaseActivity;
import com.keenon.sdk.component.navigation.route.RouteNode;
import com.keenon.sdk.component.runtime.PeanutRuntime;
import com.keenon.sdk.utils.TextUtils;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NavigationDemo extends BaseActivity {

  FirebaseDatabase database = FirebaseDatabase.getInstance();
  DatabaseReference reference = database.getReference().child("Users");
  FirebaseAuth auth = FirebaseAuth.getInstance();
  // set four navigation points
  private String point1;
  private String point2;
  private String point3;
  private String point4;
  private String SetSpeed;
  Runnable runnable;
  Handler handler;

  private static int numRobot;


  private static final int ARRIVE_STAY_DURATION = 10000;//table waiting time in mss
=======
  // When robot reached a destination point but not the last one,
  // it will stay 3000ms there and move to the next point.


  @BindView(R.id.text_target1)
  EditText textTarget1;
//  @BindView(R.id.text_target2)
//  EditText textTarget2;
//  @BindView(R.id.text_target3)
//  EditText textTarget3;
  @BindView(R.id.et_timeout)
  EditText etTimeOut;
  @BindView(R.id.et_repeat)
  EditText etRepeat;
  @BindView(R.id.et_speed)
  EditText etSpeed;
  @BindView(R.id.checkbox_arrival)
  CheckBox checkBoxArrival;
  @BindView(R.id.btn_navigate)
  Button btnStart;
  List<MyPoint> list = new ArrayList<>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_navigation);
    ButterKnife.bind(this);
    //setButtonBack();

    if(getIntent() != null) {
      numRobot = Integer.parseInt(getIntent().getStringExtra("RobotNumber"));
      reference = reference.child(auth.getUid()).child("Robot" + numRobot);

      //In this interface, robot info will also be updated similar to NavigationActivity
      //every 2000 ms
      handler = new Handler();
      runnable = new Runnable() {
        @Override
        public void run() {
          int power = PeanutRuntime.getInstance().getRuntimeInfo().getPower();
          Double odo = PeanutRuntime.getInstance().getRuntimeInfo().getTotalOdo();
          String odoText;
          odoText = new DecimalFormat("#.##").format(odo);

          reference.child("Power").setValue(power);
          reference.child("Distance").setValue(odoText);

          handler.postDelayed(runnable, 3000);
        }
      };

      handler.post(runnable);
    }

    //Receive the user's command from ServiceApp of info of point 1 2 3 4, speed etc.
    reference.child("Point1").addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot snapshot) {
        point1 = snapshot.getValue().toString();
      }

      @Override
      public void onCancelled(@NonNull DatabaseError error) {

      }
    });

    reference.child("Point2").addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot snapshot) {
        point2 = snapshot.getValue().toString();
      }

      @Override
      public void onCancelled(@NonNull DatabaseError error) {

      }
    });

    reference.child("Point3").addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot snapshot) {
        point3 = snapshot.getValue().toString();
      }

      @Override
      public void onCancelled(@NonNull DatabaseError error) {

      }
    });

    reference.child("Point4").addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot snapshot) {
        point4 = snapshot.getValue().toString();
      }

      @Override
      public void onCancelled(@NonNull DatabaseError error) {

      }
    });

    reference.child("SetSpeed").addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot snapshot) {
        if(!snapshot.getValue().equals("")) {
          SetSpeed = snapshot.getValue().toString();
        }
        else {
          //default value is 40 cm/s
          SetSpeed = "40";
          reference.child("SetSpeed").setValue("40");
        }
      }

      @Override
      public void onCancelled(@NonNull DatabaseError error) {

      }
    });

    // Allow network delay, once info of points are changed and received by RobotApp,
    // After 1000ms defined in the serviceApp, Start flag in the database will set to 1
    // Here we will use the API RemoteAddPoint from Keenon company for preparation of navigation.
    reference.child("Start").addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot snapshot) {

          if(snapshot.getValue().toString().equals("1")) {
              list.clear();
              RemoteAddPoint(point1, false);
              RemoteAddPoint(point2, false);
              RemoteAddPoint(point3, false);
              RemoteAddPoint(point4, true);

              if (list.size() == 0) {
                return;
              }
              // Send all the info by intent such as speed, list of navi points,
              // number of Robot and finally reset all of points and flag Start into 0
              // on the firebase.
              Intent intent = new Intent(NavigationDemo.this, NavigationActivity.class);
              intent.putExtra("timeout", 30);
              intent.putExtra("arrival", true);
              intent.putExtra("repeat", 1);

              intent.putExtra("speed", SetSpeed);
              intent.putExtra("list", (Serializable) list);
              intent.putExtra("RobotNumber", numRobot);
              reference.child("Point1").setValue(0);
              reference.child("Point2").setValue(0);
              reference.child("Point3").setValue(0);
              reference.child("Point4").setValue(0);
              reference.child("Start").setValue(0);
              startActivity(intent);
          }
      }

      @Override
      public void onCancelled(@NonNull DatabaseError error) {

      }
    });

    // This is mainly created from engineers in Keenon company, comments out point 2 & 3
    btnStart.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        try {
          list.clear();
          addPoint(textTarget1, true);
//          addPoint(textTarget2, false);
//          addPoint(textTarget3, true);

          if (list.size() == 0) {
            return;
          }
          Intent intent = new Intent(NavigationDemo.this, NavigationActivity.class);
          intent.putExtra("timeout", Integer.parseInt(etTimeOut.getText().toString()));
          intent.putExtra("arrival", checkBoxArrival.isChecked());
          intent.putExtra("repeat", Integer.parseInt(etRepeat.getText().toString()));
          int speed = Integer.parseInt(etSpeed.getText().toString());
          intent.putExtra("speed", speed);
          reference.child("SetSpeed").setValue(speed);
          intent.putExtra("list", (Serializable) list);
          intent.putExtra("RobotNumber", numRobot);

          startActivity(intent);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  // This is mainly created from engineers in Keenon company
  private void addPoint(EditText editText, boolean manualNext) {
    if (!TextUtils.isEmpty(editText.getText().toString())) {
      MyPoint point = new MyPoint();
      point.setManualControl(manualNext);
      point.setDuration(ARRIVE_STAY_DURATION);
      RouteNode node = new RouteNode();
      node.setId(Integer.parseInt(editText.getText().toString()));
      node.setName("Point:" + editText.getText().toString());
      point.setRouteNode(node);
      list.add(point);
    }
  }
  // This is mainly created from engineers in Keenon company
  private void RemoteAddPoint(String point1, boolean manualNext) {
      MyPoint point = new MyPoint();
      point.setManualControl(manualNext);
      point.setDuration(ARRIVE_STAY_DURATION);
      RouteNode node = new RouteNode();
      node.setId(Integer.parseInt(point1));
      node.setName("Point:" + point1);
      point.setRouteNode(node);
      list.add(point);
  }
}
