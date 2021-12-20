package com.keenon.peanut.demo.util;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ThingUtils {
  /**
   * 注意：该场景只适合于设备未激活的场景
   * 验证一型一密 需要以下步骤：
   * 1.云端创建产品，开启产品的动态注册功能；
   * 2.创建一个设备，在文件中(raw/deviceinfo)填写改设备信息 productKey，deviceName， productSecret;
   * 3.通过这三个信息可以去云端动态拿到deviceSecret，并建立长连接；
   *
   * @return
   */
  public static String getFromRaw(Context context, String assetPath) {
    InputStreamReader inputReader = null;
    BufferedReader bufReader = null;
    try {
      inputReader = new InputStreamReader(context.getApplicationContext().getResources().getAssets().open(assetPath));
      bufReader = new BufferedReader(inputReader);
      String line = "";
      String Result = "";
      while ((line = bufReader.readLine()) != null) {
        Result += line;
      }
      return Result;
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (bufReader != null) {
          bufReader.close();
        }
        if (inputReader != null) {
          inputReader.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return null;
  }
}
