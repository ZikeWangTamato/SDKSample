// Generated code from Butter Knife. Do not modify!
package com.keenon.peanut.demo.advance;

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

public class OtherDemo_ViewBinding implements Unbinder {
  private OtherDemo target;

  private View view7f09026b;

  private View view7f09026c;

  private View view7f090269;

  private View view7f09026a;

  @UiThread
  public OtherDemo_ViewBinding(OtherDemo target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public OtherDemo_ViewBinding(final OtherDemo target, View source) {
    this.target = target;

    View view;
    target.tvApiLog = Utils.findRequiredViewAsType(source, R.id.tv_api_log, "field 'tvApiLog'", TextView.class);
    target.svApiLog = Utils.findRequiredViewAsType(source, R.id.sv_api_log, "field 'svApiLog'", ScrollView.class);
    view = Utils.findRequiredView(source, R.id.cb_query_schedule_live, "method 'onViewChecked'");
    view7f09026b = view;
    ((CompoundButton) view).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton p0, boolean p1) {
        target.onViewChecked(p0, p1);
      }
    });
    view = Utils.findRequiredView(source, R.id.cb_query_schedule_status, "method 'onViewChecked'");
    view7f09026c = view;
    ((CompoundButton) view).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton p0, boolean p1) {
        target.onViewChecked(p0, p1);
      }
    });
    view = Utils.findRequiredView(source, R.id.cb_query_elevator_forward, "method 'onViewChecked'");
    view7f090269 = view;
    ((CompoundButton) view).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton p0, boolean p1) {
        target.onViewChecked(p0, p1);
      }
    });
    view = Utils.findRequiredView(source, R.id.cb_query_elevator_status, "method 'onViewChecked'");
    view7f09026a = view;
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
    OtherDemo target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvApiLog = null;
    target.svApiLog = null;

    ((CompoundButton) view7f09026b).setOnCheckedChangeListener(null);
    view7f09026b = null;
    ((CompoundButton) view7f09026c).setOnCheckedChangeListener(null);
    view7f09026c = null;
    ((CompoundButton) view7f090269).setOnCheckedChangeListener(null);
    view7f090269 = null;
    ((CompoundButton) view7f09026a).setOnCheckedChangeListener(null);
    view7f09026a = null;
  }
}
