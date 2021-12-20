// Generated code from Butter Knife. Do not modify!
package com.keenon.peanut.demo.sensor;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.keenon.peanut.demo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SensorDemo_ViewBinding implements Unbinder {
  private SensorDemo target;

  private View view7f090265;

  private View view7f09026e;

  private View view7f090261;

  private View view7f090262;

  private View view7f090266;

  private View view7f090259;

  private View view7f09026d;

  private View view7f090268;

  private View view7f090258;

  private View view7f090263;

  private View view7f090264;

  @UiThread
  public SensorDemo_ViewBinding(SensorDemo target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SensorDemo_ViewBinding(final SensorDemo target, View source) {
    this.target = target;

    View view;
    target.tvApiLog = Utils.findRequiredViewAsType(source, R.id.tv_api_log, "field 'tvApiLog'", TextView.class);
    target.svApiLog = Utils.findRequiredViewAsType(source, R.id.sv_api_log, "field 'svApiLog'", ScrollView.class);
    view = Utils.findRequiredView(source, R.id.cb_infrared_status, "method 'onViewChecked'");
    view7f090265 = view;
    ((CompoundButton) view).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton p0, boolean p1) {
        target.onViewChecked(p0, p1);
      }
    });
    view = Utils.findRequiredView(source, R.id.cb_touch_status, "method 'onViewChecked'");
    view7f09026e = view;
    ((CompoundButton) view).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton p0, boolean p1) {
        target.onViewChecked(p0, p1);
      }
    });
    view = Utils.findRequiredView(source, R.id.cb_hrc_status, "method 'onViewChecked'");
    view7f090261 = view;
    ((CompoundButton) view).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton p0, boolean p1) {
        target.onViewChecked(p0, p1);
      }
    });
    view = Utils.findRequiredView(source, R.id.cb_image_module, "method 'onViewChecked'");
    view7f090262 = view;
    ((CompoundButton) view).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton p0, boolean p1) {
        target.onViewChecked(p0, p1);
      }
    });
    view = Utils.findRequiredView(source, R.id.cb_lidar, "method 'onViewChecked'");
    view7f090266 = view;
    ((CompoundButton) view).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton p0, boolean p1) {
        target.onViewChecked(p0, p1);
      }
    });
    view = Utils.findRequiredView(source, R.id.cb_depth_camera, "method 'onViewChecked'");
    view7f090259 = view;
    ((CompoundButton) view).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton p0, boolean p1) {
        target.onViewChecked(p0, p1);
      }
    });
    view = Utils.findRequiredView(source, R.id.cb_sonar, "method 'onViewChecked'");
    view7f09026d = view;
    ((CompoundButton) view).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton p0, boolean p1) {
        target.onViewChecked(p0, p1);
      }
    });
    view = Utils.findRequiredView(source, R.id.cb_psd, "method 'onViewChecked'");
    view7f090268 = view;
    ((CompoundButton) view).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton p0, boolean p1) {
        target.onViewChecked(p0, p1);
      }
    });
    view = Utils.findRequiredView(source, R.id.cb_collision, "method 'onViewChecked'");
    view7f090258 = view;
    ((CompoundButton) view).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton p0, boolean p1) {
        target.onViewChecked(p0, p1);
      }
    });
    view = Utils.findRequiredView(source, R.id.cb_imu, "method 'onViewChecked'");
    view7f090263 = view;
    ((CompoundButton) view).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton p0, boolean p1) {
        target.onViewChecked(p0, p1);
      }
    });
    view = Utils.findRequiredView(source, R.id.cb_imu_angle, "method 'onViewChecked'");
    view7f090264 = view;
    ((CompoundButton) view).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton p0, boolean p1) {
        target.onViewChecked(p0, p1);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    SensorDemo target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvApiLog = null;
    target.svApiLog = null;

    ((CompoundButton) view7f090265).setOnCheckedChangeListener(null);
    view7f090265 = null;
    ((CompoundButton) view7f09026e).setOnCheckedChangeListener(null);
    view7f09026e = null;
    ((CompoundButton) view7f090261).setOnCheckedChangeListener(null);
    view7f090261 = null;
    ((CompoundButton) view7f090262).setOnCheckedChangeListener(null);
    view7f090262 = null;
    ((CompoundButton) view7f090266).setOnCheckedChangeListener(null);
    view7f090266 = null;
    ((CompoundButton) view7f090259).setOnCheckedChangeListener(null);
    view7f090259 = null;
    ((CompoundButton) view7f09026d).setOnCheckedChangeListener(null);
    view7f09026d = null;
    ((CompoundButton) view7f090268).setOnCheckedChangeListener(null);
    view7f090268 = null;
    ((CompoundButton) view7f090258).setOnCheckedChangeListener(null);
    view7f090258 = null;
    ((CompoundButton) view7f090263).setOnCheckedChangeListener(null);
    view7f090263 = null;
    ((CompoundButton) view7f090264).setOnCheckedChangeListener(null);
    view7f090264 = null;
  }
}
