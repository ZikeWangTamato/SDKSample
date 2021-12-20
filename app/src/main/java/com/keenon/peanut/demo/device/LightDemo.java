package com.keenon.peanut.demo.device;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.keenon.Light.LightManager;
import com.keenon.Light.model.LightEvent;
import com.keenon.PluginConstants;
import com.keenon.peanut.demo.R;
import com.keenon.peanut.demo.util.BaseActivity;
import com.keenon.peanut.demo.util.PrintLnLog;
import com.keenon.sdk.external.IDataCallback;
import com.keenon.sdk.external.PeanutSDK;
import com.keenon.sdk.protocol.model.ApiError;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LightDemo extends BaseActivity implements Handler.Callback {
  private static final String TAG = "LightDemo";
  private static final int MSG_NOTIFY_50 = 1;
  private static final int MSG_NOTIFY_40 = 2;
  private static final int MSG_NOTIFY_30 = 3;
  private static final int MSG_NOTIFY_20 = 4;
  private static final int MSG_NOTIFY_10 = 5;

  @BindView(R.id.bt_light_warning)
  Button btnLightWarning;
  @BindView(R.id.bt_light_off)
  Button btnLightOff;
  @BindView(R.id.spinner_light)
  Spinner spinnerLight;
  @BindView(R.id.tv_density)
  EditText tvDensity;

  @BindView(R.id.tv_api_log)
  TextView tvApiLog;
  @BindView(R.id.sv_api_log)
  ScrollView svApiLog;
  private StringBuilder sb = new StringBuilder();

  private LightManager lightManager;
  private int mode = 0;
  private Handler mHandler;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_lightl);
    ButterKnife.bind(this);
    setButtonBack();
    initData();
  }

  private void initData() {
    String[] mItems = getResources().getStringArray(R.array.light_mode);
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mItems);
    spinnerLight.setDropDownVerticalOffset(20);
    spinnerLight.setAdapter(adapter);
    spinnerLight.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.d(TAG, "mode onItemSelected() called with: parent = [" + parent + "], view = [" + view + "], position = [" + position + "], id = [" + id + "]");
        mode = position;
      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {

      }
    });
    lightManager = new LightManager(this.getApplicationContext());
    mHandler = new Handler(Looper.getMainLooper());
  }


  @OnClick({R.id.bt_light_warning, R.id.bt_light_off})
  public void onViewClicked(View view) {
    switch (view.getId()) {
      case R.id.bt_light_warning:
        lightManager.open(LightEvent.OPEN_MODE_BUTTON);
        break;
      case R.id.bt_light_off:
        lightManager.close(LightEvent.CLOSE_MODE_BUTTON);
        break;
      default:
        break;
    }
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
  }

  public void startLight(View view) {
    Log.d(TAG, "startLight");
    if (tvDensity.getText().length() == 0) {
      showToast("请输入亮度！");
      return;
    }
    int density = Integer.parseInt(tvDensity.getText().toString());

    Log.d(TAG, "startLight, density: " + density + ", mode: " + mode);
    PeanutSDK.getInstance().emotion().displayLight(new IDataCallback() {
      @Override
      public void success(String result) {
        PrintLnLog.d(LightDemo.this, tvApiLog, svApiLog, sb, "light status success = " + result);
      }

      @Override
      public void error(ApiError error) {
        PrintLnLog.d(LightDemo.this, tvApiLog, svApiLog, sb, "light status error = " + error.toString());
      }
    }, PluginConstants.LightType.WARNING, density, mode);
  }

  private boolean clicked = false;
  public void startBreath(View view) {
    clicked = !clicked;
    mHandler.removeMessages(MSG_NOTIFY_50);
    mHandler.removeMessages(MSG_NOTIFY_40);
    mHandler.removeMessages(MSG_NOTIFY_30);
    mHandler.removeMessages(MSG_NOTIFY_20);
    while (clicked) {
      mHandler.sendEmptyMessageDelayed(MSG_NOTIFY_50, 200);
      mHandler.sendEmptyMessageDelayed(MSG_NOTIFY_40, 200);
      mHandler.sendEmptyMessageDelayed(MSG_NOTIFY_30, 200);
      mHandler.sendEmptyMessageDelayed(MSG_NOTIFY_20, 200);
      mHandler.sendEmptyMessageDelayed(MSG_NOTIFY_10, 200);
      mHandler.sendEmptyMessageDelayed(MSG_NOTIFY_20, 200);
      mHandler.sendEmptyMessageDelayed(MSG_NOTIFY_30, 200);
      mHandler.sendEmptyMessageDelayed(MSG_NOTIFY_40, 200);
    }
  }

  private void displayLight(int density) {
    PeanutSDK.getInstance().emotion().displayLight(new IDataCallback() {
      @Override
      public void success(String result) {
        PrintLnLog.d(LightDemo.this, tvApiLog, svApiLog, sb, "light status success = " + result);
      }

      @Override
      public void error(ApiError error) {
        PrintLnLog.d(LightDemo.this, tvApiLog, svApiLog, sb, "light status error = " + error.toString());
      }
    }, PluginConstants.LightType.WARNING, density, 1);
  }

  @Override
  public boolean handleMessage(@NonNull Message msg) {
    try {
      switch (msg.what) {
        case MSG_NOTIFY_50: {
          displayLight(50);
          return true;
        }
        case MSG_NOTIFY_40: {
          displayLight(40);
          return true;
        }
        case MSG_NOTIFY_30: {
          displayLight(30);
          return true;
        }
        case MSG_NOTIFY_20: {
          displayLight(20);
          return true;
        }
        case MSG_NOTIFY_10: {
          displayLight(10);
          return true;
        }
        default:
          return false;
      }
    } catch (RuntimeException e) {
      return true;
    }
  }
}
