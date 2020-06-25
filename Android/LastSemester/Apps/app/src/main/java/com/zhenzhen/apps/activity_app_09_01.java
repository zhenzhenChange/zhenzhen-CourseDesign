package com.zhenzhen.apps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class activity_app_09_01 extends AppCompatActivity {
    public static final String LogInfo = "The Life Cycle ---> ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_09_01);
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

    public void goTo(View view) {
        Intent intent = new Intent(this, activity_app_09_01_GoTo.class);
        startActivity(intent);
    }

    public void goToNext(View view) {
        Intent intent = new Intent(this, activity_app_09_01_GoToNext.class);
        startActivity(intent);
    }
}