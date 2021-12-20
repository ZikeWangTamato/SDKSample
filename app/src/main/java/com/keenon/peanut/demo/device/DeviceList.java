package com.keenon.peanut.demo.device;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.keenon.peanut.demo.R;
import com.keenon.peanut.demo.util.BaseActivity;
import com.keenon.peanut.demo.util.DemoInfo;
import com.keenon.peanut.demo.util.DemoListAdapter;
import com.keenon.sdk.component.gating.manager.PeanutDoor;


public class DeviceList extends BaseActivity {

  private static final DemoInfo[] DEMOS = {
      new DemoInfo(R.string.demo_title_emotion, R.string.demo_desc_emotion, EmotionDemo.class),
      new DemoInfo(R.string.demo_title_light, R.string.demo_desc_light, LightDemo.class),
      new DemoInfo(R.string.demo_title_door, R.string.demo_desc_door, DoorDemo.class),
      new DemoInfo(R.string.demo_title_disinfect, R.string.demo_desc_disinfect, DisinfectDemo.class)
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_list);
    setButtonBack();
    ListView demoList = (ListView) findViewById(R.id.advanceList);
    // 添加ListItem，设置事件响应
    demoList.setAdapter(new DemoListAdapter(DeviceList.this, DEMOS));
    demoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      public void onItemClick(AdapterView<?> arg0, View v, int index, long arg3) {
        onListItemClick(index);
      }
    });
  }

  void onListItemClick(int index) {
    Intent intent;
    intent = new Intent(this, DEMOS[index].demoClass);
    this.startActivity(intent);
  }
}