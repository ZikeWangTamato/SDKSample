package com.keenon.peanut.demo.base;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.keenon.peanut.demo.R;
import com.keenon.peanut.demo.util.BaseActivity;
import com.keenon.peanut.demo.util.PrintLnLog;
import com.keenon.sdk.api.DeviceListApi;
import com.keenon.sdk.component.runtime.PeanutRuntime;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseAuth;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BaseDemo extends BaseActivity {
  private static final String TAG = "BaseDemo";
  private static int numRobot;

  @BindView(R.id.tv_api_log) TextView tvApiLog;
  @BindView(R.id.sv_api_log) ScrollView svApiLog;

  private StringBuilder sb = new StringBuilder();
  private List<DeviceListApi.Bean.DataBean> deviceList;

  FirebaseDatabase database = FirebaseDatabase.getInstance();
  DatabaseReference reference = database.getReference().child("Users");
  FirebaseAuth auth = FirebaseAuth.getInstance();

  private PeanutRuntime.Listener mRuntimeListener = new PeanutRuntime.Listener() {
    @Override
    public void onEvent(int event, Object obj) {
      Log.d(TAG, "onEvent:" + event + ", content: " + obj);
      tvApiLog.setTextColor(Color.RED);
      PrintLnLog.d(BaseDemo.this, tvApiLog, svApiLog, sb, "event  = " + event);
    }

    @Override
    public void onHealth(Object content) {
      Log.d(TAG, "onHealth:" + content);
      tvApiLog.setTextColor(Color.GREEN);
      PrintLnLog.d(BaseDemo.this, tvApiLog, svApiLog, sb, "health success = " + content);
    }

    @Override
    public void onHeartbeat(Object content) {
      Log.d(TAG, "onHeartbeat:" + content);
      tvApiLog.setTextColor(Color.BLACK);
      PrintLnLog.d(BaseDemo.this, tvApiLog, svApiLog, sb, "heartbeat success = " + content);
    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_robot_base);
    numRobot = Integer.parseInt(getIntent().getStringExtra("RobotNumber"));
    reference = reference.child(auth.getUid()).child("Robot" + numRobot);
    ButterKnife.bind(this);
    setButtonBack();
    initData();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    PeanutRuntime.getInstance().removeListener(mRuntimeListener);
  }

  private void initData() {
    PeanutRuntime.getInstance().registerListener(mRuntimeListener);
  }

  @OnClick({
//      R.id.btn_arm_info,
//      R.id.btn_stm32_version,
//      R.id.btn_query_ip,
//      R.id.btn_slam_location,
//      R.id.btn_sync_params,
      R.id.btn_query_all_dest,
      R.id.btn_query_power,
      R.id.btn_query_mileage,
  })
  public void onViewClicked(View view) {
    switch (view.getId()) {
//      case R.id.btn_arm_info:
//        String armInfo = PeanutRuntime.getInstance().getRuntimeInfo().getRobotArmInfo();
//        PrintLnLog.d(BaseDemo.this, tvApiLog, svApiLog, sb, "query arm board = " + armInfo);
//        break;
//      case R.id.btn_stm32_version:
//        String stm32Info = PeanutRuntime.getInstance().getRuntimeInfo().getRobotStm32Info();
//        PrintLnLog.d(BaseDemo.this, tvApiLog, svApiLog, sb, "query stm32 board = " + stm32Info);
//        break;
//      case R.id.btn_query_ip: //robot IP 地址
//        String ip = PeanutRuntime.getInstance().getRuntimeInfo().getRobotIp();
//        PrintLnLog.d(BaseDemo.this, tvApiLog, svApiLog, sb, "query IP = " + ip);
//        break;
//      case R.id.btn_slam_location:
//        PeanutRuntime.getInstance().location();
//        break;
//        case R.id.btn_sync_params:
//        PeanutRuntime.getInstance().syncParams2Robot(true);
//        break;
      case R.id.btn_query_all_dest: //all destination points
        String dest = PeanutRuntime.getInstance().getRuntimeInfo().getDestList();
        PrintLnLog.d(BaseDemo.this, tvApiLog, svApiLog, sb, "dest list = " + dest);
        break;
      case R.id.btn_query_power: //power of robot
        int power = PeanutRuntime.getInstance().getRuntimeInfo().getPower();
        PrintLnLog.d(BaseDemo.this, tvApiLog, svApiLog, sb, "query power = " + power);
        reference.child("Power").setValue(power);
        break;
      case R.id.btn_query_mileage: //overall mileage
        Double odo = PeanutRuntime.getInstance().getRuntimeInfo().getTotalOdo();
        String odoText;
        odoText = new DecimalFormat("#.##").format(odo);
        PrintLnLog.d(BaseDemo.this, tvApiLog, svApiLog, sb, "query odo = " + odo);
        reference.child("Distance").setValue(odoText);
        break;
      default:
        break;
    }
  }
}
