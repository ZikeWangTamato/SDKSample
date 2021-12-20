package com.keenon.peanut.demo.device;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ScrollView;
import android.widget.TextView;

import com.keenon.peanut.demo.R;
import com.keenon.peanut.demo.util.BaseActivity;
import com.keenon.peanut.demo.util.PrintLnLog;
import com.keenon.sdk.component.disinfection.PeanutDisinfection;
import com.keenon.sdk.component.disinfection.common.DisinfectInfo;
import com.keenon.sdk.component.disinfection.common.Disinfection;
import com.keenon.sdk.constant.TopicName;
import com.keenon.sdk.external.IDataCallback;
import com.keenon.sdk.external.PeanutSDK;
import com.keenon.sdk.protocol.model.ApiError;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DisinfectDemo extends BaseActivity {

  @BindView(R.id.cb_disinfect_status)
  CheckBox cbDisinfectStatus;
  @BindView(R.id.cb_disinfect_liquid)
  CheckBox cbDisinfectLiquid;
  @BindView(R.id.cb_disinfect_cabin)
  CheckBox cbDisinfectCabin;
  @BindView(R.id.cb_disinfect_drainage)
  CheckBox cbDisinfectDrainage;
  @BindView(R.id.cb_disinfect_atomizer)
  CheckBox cbDisinfectAtomizer;
  @BindView(R.id.cb_disinfect_ultraviolet)
  CheckBox cbDisinfectUltraviolet;


  @BindView(R.id.tv_api_log)
  TextView tvApiLog;
  @BindView(R.id.sv_api_log)
  ScrollView svApiLog;
  private StringBuilder sb = new StringBuilder();
  private Disinfection.Listener mListener = new Disinfection.Listener() {
    @Override
    public void onStateChanged(int state) {
      tvApiLog.setTextColor(Color.RED);
      PrintLnLog.d(DisinfectDemo.this, tvApiLog, svApiLog, sb, "disinfect status success = " + state);
    }

    @Override
    public void onInfoChanged(DisinfectInfo disinfectInfo) {
      tvApiLog.setTextColor(Color.GREEN);
      PrintLnLog.d(DisinfectDemo.this, tvApiLog, svApiLog, sb, "disinfect info success = " + disinfectInfo.toString());
    }

    @Override
    public void onError(int code) {
      tvApiLog.setTextColor(Color.YELLOW);
      PrintLnLog.d(DisinfectDemo.this, tvApiLog, svApiLog, sb, "disinfect error success = " + code);
    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_disinfect);
    ButterKnife.bind(this);
    setButtonBack();

    initData();
  }

  @Override
  protected void onStop() {
    super.onStop();
    PeanutDisinfection.getInstance().stop();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    PeanutDisinfection.getInstance().release();
  }

  private void initData() {
    PeanutDisinfection.getInstance().start();
    PeanutDisinfection.getInstance().addListener(mListener);
    cbDisinfectStatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
          PeanutSDK.getInstance().subscribe(TopicName.DISINFECT_STATUS, new IDataCallback() {
            @Override
            public void success(String response) {
              PrintLnLog.d(DisinfectDemo.this, tvApiLog, svApiLog, sb, "disinfect status success = " + response);
            }

            @Override
            public void error(ApiError error) {
              PrintLnLog.d(DisinfectDemo.this, tvApiLog, svApiLog, sb, "disinfect status error = " + error.toString());
            }
          });
        } else {
          PeanutSDK.getInstance().unSubscribe(TopicName.DISINFECT_STATUS);
        }
      }
    });
    cbDisinfectLiquid.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
          PeanutSDK.getInstance().subscribe(TopicName.DISINFECT_LIQUID, new IDataCallback() {
            @Override
            public void success(String response) {
              PrintLnLog.d(DisinfectDemo.this, tvApiLog, svApiLog, sb, "disinfect liquid success = " + response);
            }

            @Override
            public void error(ApiError error) {
              PrintLnLog.d(DisinfectDemo.this, tvApiLog, svApiLog, sb, "disinfect liquid error = " + error.toString());
            }
          });
        } else {
          PeanutSDK.getInstance().unSubscribe(TopicName.DISINFECT_LIQUID);
        }
      }
    });
    cbDisinfectCabin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        PeanutDisinfection.getInstance().enableCabin(isChecked);
      }
    });
    cbDisinfectDrainage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        PeanutDisinfection.getInstance().enableDrainage(isChecked);
      }
    });
    cbDisinfectAtomizer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        PeanutDisinfection.getInstance().enableAtomizer(isChecked);
      }
    });
    cbDisinfectUltraviolet.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        PeanutDisinfection.getInstance().enableUltraviolet(isChecked);
      }
    });
  }

  @OnClick({R.id.btn_disinfect_fan_high, R.id.btn_disinfect_fan_low})
  public void onViewClicked(View view) {
    switch (view.getId()) {
      case R.id.btn_disinfect_fan_high:
        PeanutDisinfection.getInstance().setFanSpeed(4);
        break;
      case R.id.btn_disinfect_fan_low:
        PeanutDisinfection.getInstance().setFanSpeed(0);
        break;
      default:
        break;
    }
  }
}
