package com.keenon.peanut.demo.chassis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.keenon.peanut.demo.R;
import com.keenon.peanut.demo.util.BaseActivity;
import com.keenon.peanut.demo.util.DemoInfo;
import com.keenon.peanut.demo.util.DemoListAdapter;


public class ChassisList extends BaseActivity {

  private static final DemoInfo[] DEMOS = {
      new DemoInfo(R.string.demo_title_navigation, R.string.demo_desc_navigation, NavigationDemo.class),
      new DemoInfo(R.string.demo_title_charger, R.string.demo_desc_charger, ChargerDemo.class),
      new DemoInfo(R.string.demo_title_motor, R.string.demo_desc_motor, MotorDemo.class)
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_list);
    setButtonBack();
    ListView demoList = (ListView) findViewById(R.id.advanceList);
    // 添加ListItem，设置事件响应
    demoList.setAdapter(new DemoListAdapter(ChassisList.this, DEMOS));
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