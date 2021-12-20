package com.keenon.peanut.demo.device;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ScrollView;
import android.widget.TextView;

import com.keenon.peanut.demo.R;
import com.keenon.peanut.demo.util.BaseActivity;
import com.keenon.peanut.demo.util.PrintLnLog;
import com.keenon.sdk.component.gating.callback.DoorListener;
import com.keenon.sdk.component.gating.data.Faults;
import com.keenon.sdk.component.gating.data.GatingType;
import com.keenon.sdk.component.gating.manager.Door;
import com.keenon.sdk.component.gating.manager.PeanutDoor;
import com.keenon.sdk.component.gating.state.GatingState;
import com.keenon.sdk.external.IDataCallback;
import com.keenon.sdk.external.PeanutSDK;
import com.keenon.sdk.protocol.model.ApiError;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DoorDemo extends BaseActivity implements DoorListener {

  private static final String TAG = DoorDemo.class.getSimpleName();
  @BindView(R.id.cb_door_status)
  CheckBox cbCoverStatus;
  @BindView(R.id.tv_api_log)
  TextView tvApiLog;
  @BindView(R.id.tv_version)
  TextView tvVersion;
  @BindView(R.id.sv_api_log)
  ScrollView svApiLog;
  private StringBuilder sb = new StringBuilder();
  private PeanutDoor mPeanutDoor;
  private int typeIndex;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_door);
    ButterKnife.bind(this);
    setButtonBack();
    PeanutDoor.getInstance().init(getApplicationContext());
    initData();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    mPeanutDoor.release();
  }

  private void initData() {

    mPeanutDoor = PeanutDoor.getInstance();
    mPeanutDoor.setDoorListerner(TAG, this);
    tvVersion.setText("固件版本号 ：" + mPeanutDoor.getDoorVersion());
  }

  @OnClick({R.id.btn_query_cover_status, R.id.btn_cover_open, R.id.btn_cover_close, R.id.btn_door1, R.id.btn_door2,
      R.id.btn_door3, R.id.btn_door4, R.id.btn_single_open_2, R.id.btn_closeall, R.id.btn_if_allclose, R.id.btn_switch_type4, R.id.btn_switch_type3, R.id.btn_switch_type3_reverse, R.id.btn_switch_type2
      , R.id.btn_auto_type})
  public void onViewClicked(View view) {
    switch (view.getId()) {
      case R.id.btn_query_cover_status:
        PeanutSDK.getInstance().door().getState(new IDataCallback() {
          @Override
          public void success(String response) {
            PrintLnLog.d(DoorDemo.this, tvApiLog, svApiLog, sb, "cover status success = " + response);
          }

          @Override
          public void error(ApiError error) {
            PrintLnLog.d(DoorDemo.this, tvApiLog, svApiLog, sb, "cover status error = " + error.toString());
          }
        }, 2);
        break;
      case R.id.btn_cover_open:
        PeanutSDK.getInstance().door().open(new IDataCallback() {
          @Override
          public void success(String response) {
            PrintLnLog.d(DoorDemo.this, tvApiLog, svApiLog, sb, "cover open success = " + response);
          }

          @Override
          public void error(ApiError error) {
            PrintLnLog.d(DoorDemo.this, tvApiLog, svApiLog, sb, "cover open error = " + error.toString());
          }
        }, 2);
        break;
      case R.id.btn_cover_close:
        PeanutSDK.getInstance().door().close(new IDataCallback() {
          @Override
          public void success(String response) {
            PrintLnLog.d(DoorDemo.this, tvApiLog, svApiLog, sb, "cover close success = " + response);
          }

          @Override
          public void error(ApiError error) {
            PrintLnLog.d(DoorDemo.this, tvApiLog, svApiLog, sb, "cover close error = " + error.toString());
          }
        }, 2);
        break;

      case R.id.btn_door1:
        controlDoor(0);
        break;


      case R.id.btn_door2:
        controlDoor(1);
        break;


      case R.id.btn_door3:
        controlDoor(2);
        break;


      case R.id.btn_door4:
        controlDoor(3);
        break;
      case R.id.btn_single_open_2:
        mPeanutDoor.openDoor(1, true);
        break;
      case R.id.btn_closeall:
        mPeanutDoor.closeAllDoor();
        break;
      case R.id.btn_if_allclose:
        PrintLnLog.d(DoorDemo.this, tvApiLog, svApiLog, sb, "all door close ? : " + mPeanutDoor.isAllDoorClose());
        break;

      case R.id.btn_switch_type4:
        mPeanutDoor.setDoorType(Door.SET_TYPE_FOUR, TAG, this);
        break;
      case R.id.btn_switch_type3:
        mPeanutDoor.setDoorType(Door.SET_TYPE_THREE, TAG, this);
        break;
      case R.id.btn_switch_type3_reverse:
        mPeanutDoor.setDoorType(Door.SET_TYPE_THREE_REV, TAG, this);
        break;
      case R.id.btn_switch_type2:
        mPeanutDoor.setDoorType(Door.SET_TYPE_DOUBLE, TAG, this);
        break;

      case R.id.btn_auto_type:
        mPeanutDoor.setDoorType(Door.SET_TYPE_AUTO, TAG, this);
      default:

        break;
    }
  }

  private void controlDoor(int doorId) {

    if (mPeanutDoor.getDoorState(doorId) == GatingState.CLOSED || mPeanutDoor.getDoorState(doorId) == GatingState.OPENING) {
      mPeanutDoor.openDoor(doorId, false);
    } else if (mPeanutDoor.getDoorState(doorId) == GatingState.OPENED || mPeanutDoor.getDoorState(doorId) == GatingState.CLOSING) {
      mPeanutDoor.closeDoor(doorId);
    }

  }


  @Override
  public void onFault(Faults type, int doorId) {
    PrintLnLog.d(DoorDemo.this, tvApiLog, svApiLog, sb, "cover close error ！ door id is :" + doorId);
  }

  @Override
  public void onStateChange(int floorId, int state) {
    PrintLnLog.d(DoorDemo.this, tvApiLog, svApiLog, sb, "door state changed --->" + floorId + state);
  }

  @Override
  public void onTypeChange(GatingType gatingType) {
    PrintLnLog.d(DoorDemo.this, tvApiLog, svApiLog, sb, "door type changed --->" + gatingType.name());
  }

  @Override
  public void onTypeSetting(boolean success) {
    PrintLnLog.d(DoorDemo.this, tvApiLog, svApiLog, sb, "set door type : " + success);
  }

  @Override
  public void onError(int errorCode) {
    PrintLnLog.d(DoorDemo.this, tvApiLog, svApiLog, sb, "error! code id  : " + errorCode);
  }


}
