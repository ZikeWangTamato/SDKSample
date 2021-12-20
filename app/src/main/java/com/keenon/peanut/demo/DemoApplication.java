package com.keenon.peanut.demo;

import android.app.Application;
import android.content.Context;



public class DemoApplication extends Application {
  private static String TAG = "DemoApplication";
  public static Context mAppContext = null;

  @Override
  public void onCreate() {
    super.onCreate();
    mAppContext = getApplicationContext();
//    initIOT();
  }

  public static Context getAppContext() {
    return mAppContext;
  }
}
