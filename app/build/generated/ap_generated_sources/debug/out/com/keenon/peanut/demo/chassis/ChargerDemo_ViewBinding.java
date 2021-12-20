// Generated code from Butter Knife. Do not modify!
package com.keenon.peanut.demo.chassis;

import android.view.View;
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

public class ChargerDemo_ViewBinding implements Unbinder {
  private ChargerDemo target;

  private View view7f090237;

  private View view7f090243;

  private View view7f09024f;

  private View view7f090235;

  @UiThread
  public ChargerDemo_ViewBinding(ChargerDemo target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ChargerDemo_ViewBinding(final ChargerDemo target, View source) {
    this.target = target;

    View view;
    target.tvApiLog = Utils.findRequiredViewAsType(source, R.id.tv_api_log, "field 'tvApiLog'", TextView.class);
    target.svApiLog = Utils.findRequiredViewAsType(source, R.id.sv_api_log, "field 'svApiLog'", ScrollView.class);
    view = Utils.findRequiredView(source, R.id.btn_auto_charge, "method 'onViewClicked'");
    view7f090237 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_manual_charge, "method 'onViewClicked'");
    view7f090243 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_stop_charge, "method 'onViewClicked'");
    view7f09024f = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_adapter_charge, "method 'onViewClicked'");
    view7f090235 = view;
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
    ChargerDemo target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvApiLog = null;
    target.svApiLog = null;

    view7f090237.setOnClickListener(null);
    view7f090237 = null;
    view7f090243.setOnClickListener(null);
    view7f090243 = null;
    view7f09024f.setOnClickListener(null);
    view7f09024f = null;
    view7f090235.setOnClickListener(null);
    view7f090235 = null;
  }
}
