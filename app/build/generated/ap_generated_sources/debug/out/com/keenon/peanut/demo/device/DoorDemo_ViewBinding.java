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

public class DoorDemo_ViewBinding implements Unbinder {
  private DoorDemo target;

  private View view7f090249;

  private View view7f09023b;

  private View view7f09023a;

  private View view7f09023e;

  private View view7f09023f;

  private View view7f090240;

  private View view7f090241;

  private View view7f09024c;

  private View view7f090239;

  private View view7f090242;

  private View view7f090253;

  private View view7f090251;

  private View view7f090252;

  private View view7f090250;

  private View view7f090238;

  @UiThread
  public DoorDemo_ViewBinding(DoorDemo target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DoorDemo_ViewBinding(final DoorDemo target, View source) {
    this.target = target;

    View view;
    target.cbCoverStatus = Utils.findRequiredViewAsType(source, R.id.cb_door_status, "field 'cbCoverStatus'", CheckBox.class);
    target.tvApiLog = Utils.findRequiredViewAsType(source, R.id.tv_api_log, "field 'tvApiLog'", TextView.class);
    target.tvVersion = Utils.findRequiredViewAsType(source, R.id.tv_version, "field 'tvVersion'", TextView.class);
    target.svApiLog = Utils.findRequiredViewAsType(source, R.id.sv_api_log, "field 'svApiLog'", ScrollView.class);
    view = Utils.findRequiredView(source, R.id.btn_query_cover_status, "method 'onViewClicked'");
    view7f090249 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_cover_open, "method 'onViewClicked'");
    view7f09023b = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_cover_close, "method 'onViewClicked'");
    view7f09023a = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_door1, "method 'onViewClicked'");
    view7f09023e = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_door2, "method 'onViewClicked'");
    view7f09023f = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_door3, "method 'onViewClicked'");
    view7f090240 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_door4, "method 'onViewClicked'");
    view7f090241 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_single_open_2, "method 'onViewClicked'");
    view7f09024c = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_closeall, "method 'onViewClicked'");
    view7f090239 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_if_allclose, "method 'onViewClicked'");
    view7f090242 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_switch_type4, "method 'onViewClicked'");
    view7f090253 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_switch_type3, "method 'onViewClicked'");
    view7f090251 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_switch_type3_reverse, "method 'onViewClicked'");
    view7f090252 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_switch_type2, "method 'onViewClicked'");
    view7f090250 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_auto_type, "method 'onViewClicked'");
    view7f090238 = view;
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
    DoorDemo target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.cbCoverStatus = null;
    target.tvApiLog = null;
    target.tvVersion = null;
    target.svApiLog = null;

    view7f090249.setOnClickListener(null);
    view7f090249 = null;
    view7f09023b.setOnClickListener(null);
    view7f09023b = null;
    view7f09023a.setOnClickListener(null);
    view7f09023a = null;
    view7f09023e.setOnClickListener(null);
    view7f09023e = null;
    view7f09023f.setOnClickListener(null);
    view7f09023f = null;
    view7f090240.setOnClickListener(null);
    view7f090240 = null;
    view7f090241.setOnClickListener(null);
    view7f090241 = null;
    view7f09024c.setOnClickListener(null);
    view7f09024c = null;
    view7f090239.setOnClickListener(null);
    view7f090239 = null;
    view7f090242.setOnClickListener(null);
    view7f090242 = null;
    view7f090253.setOnClickListener(null);
    view7f090253 = null;
    view7f090251.setOnClickListener(null);
    view7f090251 = null;
    view7f090252.setOnClickListener(null);
    view7f090252 = null;
    view7f090250.setOnClickListener(null);
    view7f090250 = null;
    view7f090238.setOnClickListener(null);
    view7f090238 = null;
  }
}
