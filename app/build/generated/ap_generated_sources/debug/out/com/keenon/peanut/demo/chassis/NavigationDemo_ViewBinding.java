// Generated code from Butter Knife. Do not modify!
package com.keenon.peanut.demo.chassis;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.keenon.peanut.demo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class NavigationDemo_ViewBinding implements Unbinder {
  private NavigationDemo target;

  @UiThread
  public NavigationDemo_ViewBinding(NavigationDemo target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public NavigationDemo_ViewBinding(NavigationDemo target, View source) {
    this.target = target;

    target.textTarget1 = Utils.findRequiredViewAsType(source, R.id.text_target1, "field 'textTarget1'", EditText.class);
    target.etTimeOut = Utils.findRequiredViewAsType(source, R.id.et_timeout, "field 'etTimeOut'", EditText.class);
    target.etRepeat = Utils.findRequiredViewAsType(source, R.id.et_repeat, "field 'etRepeat'", EditText.class);
    target.etSpeed = Utils.findRequiredViewAsType(source, R.id.et_speed, "field 'etSpeed'", EditText.class);
    target.checkBoxArrival = Utils.findRequiredViewAsType(source, R.id.checkbox_arrival, "field 'checkBoxArrival'", CheckBox.class);
    target.btnStart = Utils.findRequiredViewAsType(source, R.id.btn_navigate, "field 'btnStart'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    NavigationDemo target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.textTarget1 = null;
    target.etTimeOut = null;
    target.etRepeat = null;
    target.etSpeed = null;
    target.checkBoxArrival = null;
    target.btnStart = null;
  }
}
