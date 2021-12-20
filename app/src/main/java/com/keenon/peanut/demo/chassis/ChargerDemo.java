package com.keenon.peanut.demo.chassis;

import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import com.keenon.peanut.demo.R;
import com.keenon.peanut.demo.util.BaseActivity;
import com.keenon.peanut.demo.util.PrintLnLog;
import com.keenon.sdk.component.charger.PeanutCharger;
import com.keenon.sdk.component.charger.common.Charger;
import com.keenon.sdk.component.charger.common.ChargerInfo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChargerDemo extends BaseActivity {
  private static final String TAG = "ChargerDemo";

  @BindView(R.id.tv_api_log)
  TextView tvApiLog;
  @BindView(R.id.sv_api_log)
  ScrollView svApiLog;
  PeanutCharger mPeanutCharger;
  private StringBuilder sb = new StringBuilder();

  //充电回调
  Charger.Listener listener=new Charger.Listener() {
    @Override
    public void onChargerInfoChanged(int event, ChargerInfo chargerInfo) {
      PrintLnLog.d(ChargerDemo.this, tvApiLog, svApiLog, sb, "event = " + event+
              " Power = " + chargerInfo.getPower()+" ChargeEvent = " + chargerInfo.getEvent());
    }

    @Override
    public void onChargerStatusChanged(int status) {
      PrintLnLog.d(ChargerDemo.this, tvApiLog, svApiLog, sb, "status = " + status);
    }

    @Override
    public void onError(int errorCode) {
      PrintLnLog.d(ChargerDemo.this, tvApiLog, svApiLog, sb, "errorCode = " + errorCode);
    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_charge);
    ButterKnife.bind(this);
    setButtonBack();
    initData();
  }

  private void initData() {
    //初始化
    mPeanutCharger = new PeanutCharger.Builder()
        .setListener(listener)
        .build();
    mPeanutCharger.execute();
  }

  @OnClick({R.id.btn_auto_charge, R.id.btn_manual_charge, R.id.btn_stop_charge,R.id.btn_adapter_charge})
  public void onViewClicked(View view) {
    switch (view.getId()) {
      case R.id.btn_manual_charge:
        mPeanutCharger.performAction(PeanutCharger.CHARGE_ACTION_MANUAL);
        break;
      case R.id.btn_auto_charge:
        mPeanutCharger.performAction(PeanutCharger.CHARGE_ACTION_AUTO);
        break;
      case R.id.btn_stop_charge:
        mPeanutCharger.performAction(PeanutCharger.CHARGE_ACTION_STOP);
        break;
      case R.id.btn_adapter_charge:
        mPeanutCharger.performAction(PeanutCharger.CHARGE_ACTION_ADAPTER);
        break;
      default:
        break;
    }
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();

    if (mPeanutCharger != null) {
      mPeanutCharger.release();
    }
  }
}
