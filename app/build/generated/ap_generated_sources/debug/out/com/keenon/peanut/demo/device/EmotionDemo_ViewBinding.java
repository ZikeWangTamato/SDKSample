// Generated code from Butter Knife. Do not modify!
package com.keenon.peanut.demo.device;

import android.view.View;
import android.widget.Button;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.keenon.peanut.demo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class EmotionDemo_ViewBinding implements Unbinder {
  private EmotionDemo target;

  private View view7f090230;

  private View view7f090231;

  private View view7f090232;

  @UiThread
  public EmotionDemo_ViewBinding(EmotionDemo target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public EmotionDemo_ViewBinding(final EmotionDemo target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.bt_emotion_happy, "field 'btnHappy' and method 'onViewClicked'");
    target.btnHappy = Utils.castView(view, R.id.bt_emotion_happy, "field 'btnHappy'", Button.class);
    view7f090230 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.bt_emotion_sad, "field 'btnSad' and method 'onViewClicked'");
    target.btnSad = Utils.castView(view, R.id.bt_emotion_sad, "field 'btnSad'", Button.class);
    view7f090231 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.bt_emotion_sleepy, "field 'btnSleepy' and method 'onViewClicked'");
    target.btnSleepy = Utils.castView(view, R.id.bt_emotion_sleepy, "field 'btnSleepy'", Button.class);
    view7f090232 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    EmotionDemo target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btnHappy = null;
    target.btnSad = null;
    target.btnSleepy = null;

    view7f090230.setOnClickListener(null);
    view7f090230 = null;
    view7f090231.setOnClickListener(null);
    view7f090231 = null;
    view7f090232.setOnClickListener(null);
    view7f090232 = null;
  }
}
