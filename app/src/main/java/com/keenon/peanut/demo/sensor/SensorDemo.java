package com.keenon.peanut.demo.sensor;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ScrollView;
import android.widget.TextView;

import com.keenon.ISensorData;
import com.keenon.peanut.demo.R;
import com.keenon.peanut.demo.util.BaseActivity;
import com.keenon.peanut.demo.util.PrintLnLog;
import com.keenon.sdk.component.runtime.PeanutRuntime;
import com.keenon.sdk.constant.ApiConstants;
import com.keenon.sdk.external.IDataCallback;
import com.keenon.sdk.external.PeanutSDK;
import com.keenon.sdk.constant.TopicName;
import com.keenon.sdk.protocol.model.ApiError;
import com.keenon.sensor.SensorManager;
import com.keenon.sensor.model.Point;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;

public class SensorDemo extends BaseActivity {

  @BindView(R.id.tv_api_log)
  TextView tvApiLog;
  @BindView(R.id.sv_api_log)
  ScrollView svApiLog;
  private StringBuilder sb = new StringBuilder();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sensor);
    ButterKnife.bind(this);
    setButtonBack();

    initData();
  }

  @Override
  protected void onDestroy() {
    PeanutRuntime.getInstance().setWorkMode(ApiConstants.WorkMode.AUTO);
    super.onDestroy();
  }


  private void initData() {
    // step 1: switch work mode to factory mode
    PeanutRuntime.getInstance().setWorkMode(ApiConstants.WorkMode.MFG_TEST);
  }

  @OnCheckedChanged({
      R.id.cb_infrared_status,
      R.id.cb_touch_status,
      R.id.cb_hrc_status,
      R.id.cb_image_module,
      R.id.cb_lidar,
      R.id.cb_depth_camera,
      R.id.cb_sonar,
      R.id.cb_psd,
      R.id.cb_collision,
      R.id.cb_imu,
      R.id.cb_imu_angle,

  })
  public void onViewChecked(CompoundButton view, boolean isChecked) {
    switch (view.getId()) {
      case R.id.cb_infrared_status:
        if (isChecked) {
          PeanutSDK.getInstance().sensor().subscribeInfrared(new IDataCallback() {
            @Override
            public void success(String result) {
              PrintLnLog.d(SensorDemo.this, tvApiLog, svApiLog, sb, "infrared success = " + result);
            }

            @Override
            public void error(ApiError error) {
              PrintLnLog.d(SensorDemo.this, tvApiLog, svApiLog, sb, "infrared error = " + error.toString());
            }
          });

          PeanutSDK.getInstance().sensor().subscribeInfraredOld(new IDataCallback() {
            @Override
            public void success(String result) {
              PrintLnLog.d(SensorDemo.this, tvApiLog, svApiLog, sb, "old infrared success = " + result);
            }

            @Override
            public void error(ApiError error) {
              PrintLnLog.d(SensorDemo.this, tvApiLog, svApiLog, sb, "old infrared error = " + error.toString());
            }
          });
        } else {
          PeanutSDK.getInstance().sensor().cancelInfrared();
          PeanutSDK.getInstance().sensor().cancelInfraredOld();
        }
        break;

      case R.id.cb_touch_status:
        if (isChecked) {
          PeanutSDK.getInstance().sensor().subscribeTouch(new IDataCallback() {
            @Override
            public void success(String result) {
              PrintLnLog.d(SensorDemo.this, tvApiLog, svApiLog, sb, "touch success = " + result);
            }

            @Override
            public void error(ApiError error) {
              PrintLnLog.d(SensorDemo.this, tvApiLog, svApiLog, sb, "touch error = " + error.toString());
            }
          });

          PeanutSDK.getInstance().sensor().subscribeTouchOld(new IDataCallback() {
            @Override
            public void success(String result) {
              PrintLnLog.d(SensorDemo.this, tvApiLog, svApiLog, sb, "old touch success = " + result);
            }

            @Override
            public void error(ApiError error) {
              PrintLnLog.d(SensorDemo.this, tvApiLog, svApiLog, sb, "old touch error = " + error.toString());
            }
          });
        } else {
          PeanutSDK.getInstance().sensor().cancelTouch();
          PeanutSDK.getInstance().sensor().cancelTouchOld();
        }
        break;

      case R.id.cb_hrc_status:
        if (isChecked) {
          PeanutSDK.getInstance().sensor().subscribeLidar(new IDataCallback() {
            @Override
            public void success(String result) {
              PrintLnLog.d(SensorDemo.this, tvApiLog, svApiLog, sb, "lidar success = " + result);
            }

            @Override
            public void error(ApiError error) {
              PrintLnLog.d(SensorDemo.this, tvApiLog, svApiLog, sb, "lidar error = " + error.toString());
            }
          });
        } else {
          PeanutSDK.getInstance().sensor().cancelLidar();
        }
        break;


      case R.id.cb_image_module:
        if (isChecked) {
          PeanutSDK.getInstance().sensor().subscribeLidar(new IDataCallback() {
            @Override
            public void success(String result) {
              PrintLnLog.d(SensorDemo.this, tvApiLog, svApiLog, sb, "lidar success = " + result);
            }

            @Override
            public void error(ApiError error) {
              PrintLnLog.d(SensorDemo.this, tvApiLog, svApiLog, sb, "lidar error = " + error.toString());
            }
          });
        } else {
          PeanutSDK.getInstance().sensor().cancelLidar();
        }
        break;

      case R.id.cb_lidar:
        if (isChecked) {
          PeanutSDK.getInstance().sensor().subscribeLidar(new IDataCallback() {
            @Override
            public void success(String result) {
              PrintLnLog.d(SensorDemo.this, tvApiLog, svApiLog, sb, "lidar success = " + result);
            }

            @Override
            public void error(ApiError error) {
              PrintLnLog.d(SensorDemo.this, tvApiLog, svApiLog, sb, "lidar error = " + error.toString());
            }
          });
        } else {
          PeanutSDK.getInstance().sensor().cancelLidar();
        }
        break;

      case R.id.cb_depth_camera:
        if (isChecked) {
          SensorManager.getInstance().subDepth(new ISensorData<List<Point>>() {
            @Override
            public void onData(List<Point> data) {
              PrintLnLog.d(SensorDemo.this, tvApiLog, svApiLog, sb, "depth camera success = " + data.toString());
            }
          });
        } else {
          SensorManager.getInstance().cancelSubDepth();
        }
        break;

      case R.id.cb_sonar:
        if (isChecked) {
          PeanutSDK.getInstance().sensor().subscribeSonar(new IDataCallback() {
            @Override
            public void success(String result) {
              PrintLnLog.d(SensorDemo.this, tvApiLog, svApiLog, sb, "sonar success = " + result);
            }

            @Override
            public void error(ApiError error) {
              PrintLnLog.d(SensorDemo.this, tvApiLog, svApiLog, sb, "sonar error = " + error.toString());
            }
          });
        } else {
          PeanutSDK.getInstance().sensor().cancelSonar();
        }
        break;

      case R.id.cb_psd:
        if (isChecked) {
          PeanutSDK.getInstance().sensor().subscribePsd(new IDataCallback() {
            @Override
            public void success(String result) {
              PrintLnLog.d(SensorDemo.this, tvApiLog, svApiLog, sb, "psd success = " + result);
            }

            @Override
            public void error(ApiError error) {
              PrintLnLog.d(SensorDemo.this, tvApiLog, svApiLog, sb, "psd error = " + error.toString());
            }
          });
        } else {
          PeanutSDK.getInstance().sensor().cancelPsd();
        }
        break;

      case R.id.cb_collision:
        if (isChecked) {
          PeanutSDK.getInstance().subscribe(TopicName.SENSOR_COLLISION, new IDataCallback() {
            @Override
            public void success(String result) {
              PrintLnLog.d(SensorDemo.this, tvApiLog, svApiLog, sb, "collision success = " + result);
            }

            @Override
            public void error(ApiError error) {
              PrintLnLog.d(SensorDemo.this, tvApiLog, svApiLog, sb, "collision error = " + error.toString());
            }
          });
        } else {
          PeanutSDK.getInstance().unSubscribe(TopicName.SENSOR_COLLISION);
        }
        break;

      case R.id.cb_imu:
        if (isChecked) {
          PeanutSDK.getInstance().sensor().subscribeImu(new IDataCallback() {
            @Override
            public void success(String result) {
              PrintLnLog.d(SensorDemo.this, tvApiLog, svApiLog, sb, "imu success = " + result);
            }

            @Override
            public void error(ApiError error) {
              PrintLnLog.d(SensorDemo.this, tvApiLog, svApiLog, sb, "imu error = " + error.toString());
            }
          });
        } else {
          PeanutSDK.getInstance().sensor().cancelImu();
        }
        break;

      case R.id.cb_imu_angle:
        if (isChecked) {
          PeanutSDK.getInstance().sensor().subscribeImuAngle(new IDataCallback() {
            @Override
            public void success(String result) {
              PrintLnLog.d(SensorDemo.this, tvApiLog, svApiLog, sb, "imu angle success = " + result);
            }

            @Override
            public void error(ApiError error) {
              PrintLnLog.d(SensorDemo.this, tvApiLog, svApiLog, sb, "imu angle error = " + error.toString());
            }
          });
        } else {
          PeanutSDK.getInstance().sensor().cancelImuAngle();
        }
        break;
      default:
        break;
    }
  }
}