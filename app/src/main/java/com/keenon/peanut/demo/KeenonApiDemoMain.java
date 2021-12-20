/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.keenon.peanut.demo;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.keenon.peanut.demo.advance.AdvanceList;
import com.keenon.peanut.demo.base.BaseDemo;
import com.keenon.peanut.demo.chassis.ChassisList;
import com.keenon.peanut.demo.chassis.NavigationDemo;
import com.keenon.peanut.demo.device.DeviceList;
import com.keenon.peanut.demo.sensor.SensorDemo;
import com.keenon.peanut.demo.util.BaseActivity;
import com.keenon.sdk.component.runtime.PeanutRuntime;
import com.keenon.sdk.constant.PeanutConstants;
import com.keenon.sdk.external.PeanutConfig;
import com.keenon.sdk.external.PeanutSDK;
import com.keenon.sdk.utils.LogUtils;
import com.keenon.sdk.utils.VersionInfo;

import java.util.ArrayList;


import static com.keenon.sdk.external.PeanutSDK.SDK_INIT_SUCCESS;


public class KeenonApiDemoMain extends BaseActivity {

  FirebaseAuth auth;
  private static String numRobot;

  private static final String TAG = KeenonApiDemoMain.class.getSimpleName();
  private static final DemoInfo[] DEMOS = {
      //new DemoInfo(R.drawable.info, R.string.demo_title_baselist, R.string.demo_desc_baselist, BaseDemo.class),
      new DemoInfo(R.drawable.chassis, R.string.demo_title_chassislist, R.string.demo_desc_chassislist, NavigationDemo.class),
      //new DemoInfo(R.drawable.sensor, R.string.demo_title_sensorlist, R.string.demo_desc_sensorlist, SensorDemo.class),
      //new DemoInfo(R.drawable.util, R.string.demo_title_devicelist, R.string.demo_desc_devicelist, DeviceList.class),
      //new DemoInfo(R.drawable.draw, R.string.demo_title_advancelist, R.string.demo_desc_advancelist, AdvanceList.class)
  };
  private boolean isPermissionRequested;
  private PeanutSDK.ErrorListener mErrorListener = errorCode -> {
    Log.d(TAG, "onInit:" + errorCode);
    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        TextView text = (TextView) findViewById(R.id.text_Info);

        if (errorCode == SDK_INIT_SUCCESS) {
          text.setTextColor(Color.GREEN);
          text.setText("SDK initialization：" + errorCode);
        } else {
          text.setTextColor(Color.RED);
          text.setText("SDK initialization：" + errorCode);
        }
      }
    });
  };

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {

    MenuInflater menuInflater = getMenuInflater();
    menuInflater.inflate(R.menu.keenon_menu, menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if(item.getItemId() == R.id.logout_menu) {
      auth.signOut();
      startActivity(new Intent(KeenonApiDemoMain.this, SrvRobotLoginActivity2.class));
    }

    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    auth = FirebaseAuth.getInstance();
    numRobot = getIntent().getStringExtra("RobotNumber");

    TextView text = (TextView) findViewById(R.id.text_Info);
    text.setTextColor(Color.GREEN);
    text.setText("Welcome to use Keenon Android SDK v" + VersionInfo.versionName);
    setTitle(getTitle() + " v" + VersionInfo.versionName);
    ListView mListView = (ListView) findViewById(R.id.listView);
    // insert ListItem，Set event listener
    mListView.setAdapter(new DemoListAdapter());
    mListView.setOnItemClickListener(new OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> arg0, View v, int index, long arg3) {
        onListItemClick(index);
      }
    });

    // 申请动态权限
    requestPermission();
    initSDK();
  }

  private void initSDK() {
    PeanutConfig config = new PeanutConfig.Builder()
        /**
         * If ROS system is equipped for laser robot，you need to change another IP
         */

        .setLinkIP(PeanutConstants.REMOTE_LINK_PROXY)
        .enableLog(true)
        .setLogLevel(Log.DEBUG)
//        .enableLogBack(true)
        .setDoorNum(4)
        .build();
    PeanutSDK.getInstance().init(this.getApplicationContext(), config, mErrorListener);
    PeanutRuntime.getInstance().registerListener(new PeanutRuntime.Listener() {
      @Override
      public void onEvent(int event, Object obj) {
        LogUtils.d(TAG, "onEvent:" + event + ", content: " + obj);
      }

      @Override
      public void onHealth(Object content) {
        LogUtils.d(TAG, "onHealth:" + content);
      }

      @Override
      public void onHeartbeat(Object content) {
        LogUtils.d(TAG, "onHeartbeat:" + content);
      }
    });
  }

  void onListItemClick(int index) {
    Intent intent;
    intent = new Intent(KeenonApiDemoMain.this, DEMOS[index].demoClass);
    intent.putExtra("RobotNumber", numRobot);
    this.startActivity(intent);
  }

  @Override
  protected void onResume() {
    super.onResume();
  }

  @Override
  protected void onDestroy() {
    PeanutSDK.getInstance().release();
    super.onDestroy();
  }

  /**
   * Android6.0之后需要动态申请权限
   */
  private void requestPermission() {
    if (Build.VERSION.SDK_INT >= 23 && !isPermissionRequested) {
      isPermissionRequested = true;
      ArrayList<String> permissionsList = new ArrayList<>();
      String[] permissions = {
          Manifest.permission.ACCESS_NETWORK_STATE,
          Manifest.permission.INTERNET,
          Manifest.permission.WRITE_EXTERNAL_STORAGE,
          Manifest.permission.READ_EXTERNAL_STORAGE,
          Manifest.permission.ACCESS_WIFI_STATE,
          Manifest.permission.READ_PHONE_STATE,
      };

      for (String perm : permissions) {
        if (PackageManager.PERMISSION_GRANTED != checkSelfPermission(perm)) {
          permissionsList.add(perm);
          // 进入到这里代表没有权限.
        }
      }

      if (!permissionsList.isEmpty()) {
        String[] strings = new String[permissionsList.size()];
        requestPermissions(permissionsList.toArray(strings), 0);
      }
    }
  }

  private static class DemoInfo {
    private final int image;
    private final int title;
    private final int desc;
    private final Class<? extends Activity> demoClass;

    private DemoInfo(int image, int title, int desc, Class<? extends Activity> demoClass) {
      this.image = image;
      this.title = title;
      this.desc = desc;
      this.demoClass = demoClass;
    }
  }

  private class DemoListAdapter extends BaseAdapter {
    private DemoListAdapter() {
      super();
    }

    @Override
    public View getView(int index, View convertView, ViewGroup parent) {
      if (null == convertView) {
        convertView = View.inflate(KeenonApiDemoMain.this, R.layout.item_demo, null);
      }
      ImageView imageView = (ImageView) convertView.findViewById(R.id.image);
      TextView title = (TextView) convertView.findViewById(R.id.title);
      TextView desc = (TextView) convertView.findViewById(R.id.desc);
      imageView.setBackgroundResource(DEMOS[index].image);
      title.setText(DEMOS[index].title);
      desc.setText(DEMOS[index].desc);
      return convertView;
    }

    @Override
    public int getCount() {
      return DEMOS.length;
    }

    @Override
    public Object getItem(int index) {
      return DEMOS[index];
    }

    @Override
    public long getItemId(int id) {
      return id;
    }
  }
}