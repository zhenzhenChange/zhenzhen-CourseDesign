package com.zhenzhen.apps;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class activity_app_09_02_start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_09_02_start);
        TextView startTextView = findViewById(R.id.startTextView);
        Intent intent = getIntent();
        if (intent != null) {
            startTextView.setText(intent.getStringExtra("send"));
        }
    }
}