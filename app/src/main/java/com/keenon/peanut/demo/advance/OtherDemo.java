package com.keenon.peanut.demo.advance;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ScrollView;
import android.widget.TextView;

import com.keenon.peanut.demo.R;
import com.keenon.peanut.demo.util.BaseActivity;
import com.keenon.peanut.demo.util.PrintLnLog;
import com.keenon.sdk.external.IDataCallback;
import com.keenon.sdk.external.PeanutSDK;
import com.keenon.sdk.constant.TopicName;
import com.keenon.sdk.protocol.model.ApiError;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;

public class OtherDemo extends BaseActivity {

  @BindView(R.id.tv_api_log)
  TextView tvApiLog;
  @BindView(R.id.sv_api_log)
  ScrollView svApiLog;
  private StringBuilder sb = new StringBuilder();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_advance_other);
    ButterKnife.bind(this);
    setButtonBack();

  }

  @OnCheckedChanged({
      R.id.cb_query_schedule_live,
      R.id.cb_query_schedule_status,
      R.id.cb_query_elevator_forward,
      R.id.cb_query_elevator_status
  })
  public void onViewChecked(CompoundButton view, boolean isChecked) {
    switch (view.getId()) {
      case R.id.cb_query_schedule_live:
        if (isChecked) {
          PeanutSDK.getInstance().subscribe(TopicName.SCHEDULE_LIVE, new IDataCallback() {
            @Override
            public void success(String result) {
              PrintLnLog.d(OtherDemo.this, tvApiLog, svApiLog, sb, "schedule live success = " + result);
            }

            @Override
            public void error(ApiError error) {
              PrintLnLog.d(OtherDemo.this, tvApiLog, svApiLog, sb, "schedule live error = " + error.toString());
            }
          });
        } else {
          PeanutSDK.getInstance().unSubscribe(TopicName.SCHEDULE_LIVE);
        }
        break;
      case R.id.cb_query_schedule_status:
        if (isChecked) {
          PeanutSDK.getInstance().subscribe(TopicName.SCHEDULE_STATUS, new IDataCallback() {
            @Override
            public void success(String result) {
              PrintLnLog.d(OtherDemo.this, tvApiLog, svApiLog, sb, "schedule status success = " + result);
            }

            @Override
            public void error(ApiError error) {
              PrintLnLog.d(OtherDemo.this, tvApiLog, svApiLog, sb, "schedule status error = " + error.toString());
            }
          });
        } else {
          PeanutSDK.getInstance().unSubscribe(TopicName.SCHEDULE_STATUS);
        }
        break;
      case R.id.cb_query_elevator_forward:
        if (isChecked) {
          PeanutSDK.getInstance().subscribe(TopicName.ELEVATOR_FORWARD, new IDataCallback() {
            @Override
            public void success(String result) {
              PrintLnLog.d(OtherDemo.this, tvApiLog, svApiLog, sb, "elevator forward success = " + result);
            }

            @Override
            public void error(ApiError error) {
              PrintLnLog.d(OtherDemo.this, tvApiLog, svApiLog, sb, "elevator forward error = " + error.toString());
            }
          });
        } else {
          PeanutSDK.getInstance().unSubscribe(TopicName.ELEVATOR_FORWARD);
        }
        break;
      case R.id.cb_query_elevator_status:
        if (isChecked) {
          PeanutSDK.getInstance().subscribe(TopicName.ELEVATOR_STATUS, new IDataCallback() {
            @Override
            public void success(String result) {
              PrintLnLog.d(OtherDemo.this, tvApiLog, svApiLog, sb, "elevator status success = " + result);
            }

            @Override
            public void error(ApiError error) {
              PrintLnLog.d(OtherDemo.this, tvApiLog, svApiLog, sb, "elevator status error = " + error.toString());
            }
          });
        } else {
          PeanutSDK.getInstance().unSubscribe(TopicName.ELEVATOR_STATUS);
        }
        break;
      default:
        break;
    }
  }
}
