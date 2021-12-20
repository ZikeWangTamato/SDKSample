// Generated code from Butter Knife. Do not modify!
package com.keenon.peanut.demo.chassis;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.keenon.peanut.demo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class NavigationActivity_ViewBinding implements Unbinder {
  private NavigationActivity target;

  @UiThread
  public NavigationActivity_ViewBinding(NavigationActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public NavigationActivity_ViewBinding(NavigationActivity target, View source) {
    this.target = target;

    target.tvStatus = Utils.findRequiredViewAsType(source, R.id.tv_status, "field 'tvStatus'", TextView.class);
    target.btnNext = Utils.findRequiredViewAsType(source, R.id.btn_manual_next, "field 'btnNext'", Button.class);
    target.btnPause = Utils.findRequiredViewAsType(source, R.id.btn_pause, "field 'btnPause'", Button.class);
    target.btnResume = Utils.findRequiredViewAsType(source, R.id.btn_resume, "field 'btnResume'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    NavigationActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvStatus = null;
    target.btnNext = null;
    target.btnPause = null;
    target.btnResume = null;
  }
}
