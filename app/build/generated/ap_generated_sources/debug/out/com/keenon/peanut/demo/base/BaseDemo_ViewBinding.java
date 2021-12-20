// Generated code from Butter Knife. Do not modify!
package com.keenon.peanut.demo.base;

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

public class BaseDemo_ViewBinding implements Unbinder {
  private BaseDemo target;

  private View view7f09006e;

  private View view7f090070;

  private View view7f09006f;

  @UiThread
  public BaseDemo_ViewBinding(BaseDemo target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public BaseDemo_ViewBinding(final BaseDemo target, View source) {
    this.target = target;

    View view;
    target.tvApiLog = Utils.findRequiredViewAsType(source, R.id.tv_api_log, "field 'tvApiLog'", TextView.class);
    target.svApiLog = Utils.findRequiredViewAsType(source, R.id.sv_api_log, "field 'svApiLog'", ScrollView.class);
    view = Utils.findRequiredView(source, R.id.btn_query_all_dest, "method 'onViewClicked'");
    view7f09006e = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_query_power, "method 'onViewClicked'");
    view7f090070 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_query_mileage, "method 'onViewClicked'");
    view7f09006f = view;
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
    BaseDemo target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvApiLog = null;
    target.svApiLog = null;

    view7f09006e.setOnClickListener(null);
    view7f09006e = null;
    view7f090070.setOnClickListener(null);
    view7f090070 = null;
    view7f09006f.setOnClickListener(null);
    view7f09006f = null;
  }
}
