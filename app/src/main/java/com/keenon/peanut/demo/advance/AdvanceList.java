package com.keenon.peanut.demo.advance;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.keenon.peanut.demo.R;
import com.keenon.peanut.demo.base.BaseDemo;
import com.keenon.peanut.demo.util.BaseActivity;
import com.keenon.peanut.demo.util.DemoInfo;
import com.keenon.peanut.demo.util.DemoListAdapter;


public class AdvanceList extends BaseActivity {

  private static final DemoInfo[] DEMOS = {
      new DemoInfo(R.string.demo_title_config, R.string.demo_desc_config, BaseDemo.class),
      new DemoInfo(R.string.demo_title_update, R.string.demo_desc_update, BaseDemo.class),
      new DemoInfo(R.string.demo_title_map, R.string.demo_desc_map, BaseDemo.class),
      new DemoInfo(R.string.demo_title_other, R.string.demo_desc_other, OtherDemo.class)
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_list);
    setButtonBack();
    ListView demoList = (ListView) findViewById(R.id.advanceList);
    // 添加ListItem，设置事件响应
    demoList.setAdapter(new DemoListAdapter(AdvanceList.this, DEMOS));
    demoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> arg0, View v, int index, long arg3) {
        onListItemClick(index);
      }
    });
  }

  void onListItemClick(int index) {
    if (index < 5) {
      Toast.makeText(this, getString(R.string.unavailable_at_the_moment), Toast.LENGTH_SHORT).show();
      return;
    }
    Intent intent;
    intent = new Intent(this, DEMOS[index].demoClass);
    this.startActivity(intent);
  }
}