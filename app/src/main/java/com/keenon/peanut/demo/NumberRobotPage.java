package com.keenon.peanut.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberRobotPage extends AppCompatActivity {

    private EditText editTextRobotNum;
    private Button buttonConfrim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_robot_page);
        editTextRobotNum = findViewById(R.id.editTextNumberRobot);
        buttonConfrim = findViewById(R.id.ButtonConfrim);

        buttonConfrim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numRobot = editTextRobotNum.getText().toString();
                Pattern p = Pattern.compile("(^100$)|^[1-9]\\d?$");
                Matcher m = p.matcher(numRobot);
                // regular expression number from 1 to 100
                if(m.find()) {
                    Intent intent = new Intent(NumberRobotPage.this, KeenonApiDemoMain.class);
                    intent.putExtra("RobotNumber", numRobot);
                    startActivity(intent);
                    Toast.makeText(NumberRobotPage.this, "Your robot number is " + numRobot,
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(NumberRobotPage.this, "Please enter valid number (range: 1-100)",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}