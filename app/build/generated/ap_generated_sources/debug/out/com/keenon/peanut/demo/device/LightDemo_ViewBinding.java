// Generated code from Butter Knife. Do not modify!
package com.keenon.peanut.demo.device;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.keenon.peanut.demo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LightDemo_ViewBinding implements Unbinder {
  private LightDemo target;

  private View view7f090234;

  private View view7f090233;

  @UiThread
  public LightDemo_ViewBinding(LightDemo target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LightDemo_ViewBinding(final LightDemo target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.bt_light_warning, "field 'btnLightWarning' and method 'onViewClicked'");
    target.btnLightWarning = Utils.castView(view, R.id.bt_light_warning, "field 'btnLightWarning'", Button.class);
    view7f090234 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.bt_light_off, "field 'btnLightOff' and method 'onViewClicked'");
    target.btnLightOff = Utils.castView(view, R.id.bt_light_off, "field 'btnLightOff'", Button.class);
    view7f090233 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.spinnerLight = Utils.findRequiredViewAsType(source, R.id.spinner_light, "field 'spinnerLight'", Spinner.class);
    target.tvDensity = Utils.findRequiredViewAsType(source, R.id.tv_density, "field 'tvDensity'", EditText.class);
    target.tvApiLog = Utils.findRequiredViewAsType(source, R.id.tv_api_log, "field 'tvApiLog'", TextView.class);
    target.svApiLog = Utils.findRequiredViewAsType(source, R.id.sv_api_log, "field 'svApiLog'", ScrollView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LightDemo target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btnLightWarning = null;
    target.btnLightOff = null;
    target.spinnerLight = null;
    target.tvDensity = null;
    target.tvApiLog = null;
    target.svApiLog = null;

    view7f090234.setOnClickListener(null);
    view7f090234 = null;
    view7f090233.setOnClickListener(null);
    view7f090233 = null;
  }
}
