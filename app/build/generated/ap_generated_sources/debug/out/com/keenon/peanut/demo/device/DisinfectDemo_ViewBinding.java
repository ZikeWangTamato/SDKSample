// Generated code from Butter Knife. Do not modify!
package com.keenon.peanut.demo.device;

import android.view.View;
import android.widget.CheckBox;
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

public class DisinfectDemo_ViewBinding implements Unbinder {
  private DisinfectDemo target;

  private View view7f09023c;

  private View view7f09023d;

  @UiThread
  public DisinfectDemo_ViewBinding(DisinfectDemo target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DisinfectDemo_ViewBinding(final DisinfectDemo target, View source) {
    this.target = target;

    View view;
    target.cbDisinfectStatus = Utils.findRequiredViewAsType(source, R.id.cb_disinfect_status, "field 'cbDisinfectStatus'", CheckBox.class);
    target.cbDisinfectLiquid = Utils.findRequiredViewAsType(source, R.id.cb_disinfect_liquid, "field 'cbDisinfectLiquid'", CheckBox.class);
    target.cbDisinfectCabin = Utils.findRequiredViewAsType(source, R.id.cb_disinfect_cabin, "field 'cbDisinfectCabin'", CheckBox.class);
    target.cbDisinfectDrainage = Utils.findRequiredViewAsType(source, R.id.cb_disinfect_drainage, "field 'cbDisinfectDrainage'", CheckBox.class);
    target.cbDisinfectAtomizer = Utils.findRequiredViewAsType(source, R.id.cb_disinfect_atomizer, "field 'cbDisinfectAtomizer'", CheckBox.class);
    target.cbDisinfectUltraviolet = Utils.findRequiredViewAsType(source, R.id.cb_disinfect_ultraviolet, "field 'cbDisinfectUltraviolet'", CheckBox.class);
    target.tvApiLog = Utils.findRequiredViewAsType(source, R.id.tv_api_log, "field 'tvApiLog'", TextView.class);
    target.svApiLog = Utils.findRequiredViewAsType(source, R.id.sv_api_log, "field 'svApiLog'", ScrollView.class);
    view = Utils.findRequiredView(source, R.id.btn_disinfect_fan_high, "method 'onViewClicked'");
    view7f09023c = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_disinfect_fan_low, "method 'onViewClicked'");
    view7f09023d = view;
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
    DisinfectDemo target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.cbDisinfectStatus = null;
    target.cbDisinfectLiquid = null;
    target.cbDisinfectCabin = null;
    target.cbDisinfectDrainage = null;
    target.cbDisinfectAtomizer = null;
    target.cbDisinfectUltraviolet = null;
    target.tvApiLog = null;
    target.svApiLog = null;

    view7f09023c.setOnClickListener(null);
    view7f09023c = null;
    view7f09023d.setOnClickListener(null);
    view7f09023d = null;
  }
}
