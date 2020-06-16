package com.zhenzhen.apps;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.RelativeLayout;

public class activity_app_08 extends AppCompatActivity {
    private int[] colors = {Color.RED, Color.BLUE, Color.MAGENTA, Color.YELLOW};
    private int currentIndex = 0;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_08);
        final RelativeLayout root = findViewById(R.id.root);
        handler = new Handler() {
            public void handleMessage(Message message) {
                int length = colors.length;
                if (message.what == 0x11) {
                    currentIndex = (currentIndex + 1) % length;
                    root.setBackgroundColor(colors[currentIndex]);
                }
            }
        };
        start();
    }

    private void start() {
        new Thread() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(4000);
                        handler.sendEmptyMessage(0x11);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}