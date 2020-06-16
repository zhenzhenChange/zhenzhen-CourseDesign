package com.zhenzhen.apps;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class activity_app_09_02 extends AppCompatActivity {
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_09_02);
        resultText = findViewById(R.id.resultText);
    }

    public void Start(View view) {
        Intent intent = new Intent(this, activity_app_09_02_start.class);
        intent.putExtra("send", "我是Main传递过来的字符串");
        startActivity(intent);
    }

    public void Result(View view) {
        Intent intent = new Intent(this, activity_app_09_02_result.class);
        intent.putExtra("send", "我是Main传递过来的字符串");
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (resultCode == 0x11) {
                String str = data.getStringExtra("dataInfo");
                resultText.setText(str);
            }
        }
    }
}