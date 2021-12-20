// Generated code from Butter Knife. Do not modify!
package com.keenon.peanut.demo.chassis;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.keenon.peanut.demo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MotorDemo_ViewBinding implements Unbinder {
  private MotorDemo target;

  private View view7f09027e;

  private View view7f09027f;

  private View view7f090267;

  private View view7f090244;

  private View view7f090246;

  private View view7f090245;

  private View view7f090248;

  private View view7f090247;

  private View view7f090256;

  private View view7f090255;

  @UiThread
  public MotorDemo_ViewBinding(MotorDemo target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  @SuppressLint("ClickableViewAccessibility")
  public MotorDemo_ViewBinding(final MotorDemo target, View source) {
    this.target = target;

    View view;
    target.tvApiLog = Utils.findRequiredViewAsType(source, R.id.tv_api_log, "field 'tvApiLog'", TextView.class);
    target.svApiLog = Utils.findRequiredViewAsType(source, R.id.sv_api_log, "field 'svApiLog'", ScrollView.class);
    view = Utils.findRequiredView(source, R.id.sw_motor_enable, "method 'onViewChecked'");
    view7f09027e = view;
    ((CompoundButton) view).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton p0, boolean p1) {
        target.onViewChecked(p0, p1);
      }
    });
    view = Utils.findRequiredView(source, R.id.sw_motor_hrc, "method 'onViewChecked'");
    view7f09027f = view;
    ((CompoundButton) view).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton p0, boolean p1) {
        target.onViewChecked(p0, p1);
      }
    });
    view = Utils.findRequiredView(source, R.id.cb_motor_status, "method 'onViewChecked'");
    view7f090267 = view;
    ((CompoundButton) view).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton p0, boolean p1) {
        target.onViewChecked(p0, p1);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_motor_encoder, "method 'onViewClicked'");
    view7f090244 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_motor_speed, "method 'onViewClicked'");
    view7f090246 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_motor_health, "method 'onViewClicked'");
    view7f090245 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_move_forward, "method 'onViewTouched'");
    view7f090248 = view;
    view.setOnTouchListener(new View.OnTouchListener() {
      @Override
      public boolean onTouch(View p0, MotionEvent p1) {
        return target.onViewTouched(p0, p1);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_move_backward, "method 'onViewTouched'");
    view7f090247 = view;
    view.setOnTouchListener(new View.OnTouchListener() {
      @Override
      public boolean onTouch(View p0, MotionEvent p1) {
        return target.onViewTouched(p0, p1);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_turn_right, "method 'onViewTouched'");
    view7f090256 = view;
    view.setOnTouchListener(new View.OnTouchListener() {
      @Override
      public boolean onTouch(View p0, MotionEvent p1) {
        return target.onViewTouched(p0, p1);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_turn_left, "method 'onViewTouched'");
    view7f090255 = view;
    view.setOnTouchListener(new View.OnTouchListener() {
      @Override
      public boolean onTouch(View p0, MotionEvent p1) {
        return target.onViewTouched(p0, p1);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    MotorDemo target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvApiLog = null;
    target.svApiLog = null;

    ((CompoundButton) view7f09027e).setOnCheckedChangeListener(null);
    view7f09027e = null;
    ((CompoundButton) view7f09027f).setOnCheckedChangeListener(null);
    view7f09027f = null;
    ((CompoundButton) view7f090267).setOnCheckedChangeListener(null);
    view7f090267 = null;
    view7f090244.setOnClickListener(null);
    view7f090244 = null;
    view7f090246.setOnClickListener(null);
    view7f090246 = null;
    view7f090245.setOnClickListener(null);
    view7f090245 = null;
    view7f090248.setOnTouchListener(null);
    view7f090248 = null;
    view7f090247.setOnTouchListener(null);
    view7f090247 = null;
    view7f090256.setOnTouchListener(null);
    view7f090256 = null;
    view7f090255.setOnTouchListener(null);
    view7f090255 = null;
  }
}
