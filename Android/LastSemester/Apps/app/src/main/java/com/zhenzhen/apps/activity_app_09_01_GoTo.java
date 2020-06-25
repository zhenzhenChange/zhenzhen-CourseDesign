package com.zhenzhen.apps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class activity_app_09_01_GoTo extends AppCompatActivity {
    public static final String LogInfo = "The Life Cycle ---> ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_09_01_go_to);
        Log.i(LogInfo, "onCreate");
    }

    protected void onStart() {
        super.onStart();
        Log.i(LogInfo, "onStart");
    }

    protected void onResume() {
        super.onResume();
        Log.i(LogInfo, "onResume");
    }

    protected void onRestart() {
        super.onRestart();
        Log.i(LogInfo, "onRestart");
    }

    protected void onPause() {
        super.onPause();
        Log.i(LogInfo, "onPause");
    }

    protected void onStop() {
        super.onStop();
        Log.i(LogInfo, "onStop");
    }

    protected void onDestroy() {
        super.onDestroy();
        Log.i(LogInfo, "onDestroy");
    }
}