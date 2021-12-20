package com.keenon.peanut.demo.device;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.keenon.emotion.FaceScene;
import com.keenon.peanut.demo.R;
import com.keenon.peanut.demo.util.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EmotionDemo extends BaseActivity {
  private static final String TAG = EmotionDemo.class.getSimpleName();
  @BindView(R.id.bt_emotion_happy)
  Button btnHappy;
  @BindView(R.id.bt_emotion_sad)
  Button btnSad;
  @BindView(R.id.bt_emotion_sleepy)
  Button btnSleepy;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_emotion);
    ButterKnife.bind(this);
    setButtonBack();
  }


  @OnClick({R.id.bt_emotion_happy, R.id.bt_emotion_sad, R.id.bt_emotion_sleepy})
  public void onViewClicked(View view) {
    Log.d(TAG, "onViewClicked:");
    switch (view.getId()) {
      case R.id.bt_emotion_happy:
        FaceScene.ROBOT_WORKING_V2.run(getApplicationContext(), true, 3, false);
        break;
      case R.id.bt_emotion_sad:
        FaceScene.ROBOT_BLOCK_V2.run(getApplicationContext(), true, 3, false);
        break;
      case R.id.bt_emotion_sleepy:
        FaceScene.ROBOT_CHARGING.run(getApplicationContext(), true, 3, false);
        break;
      default:
        break;
    }
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
  }
}
