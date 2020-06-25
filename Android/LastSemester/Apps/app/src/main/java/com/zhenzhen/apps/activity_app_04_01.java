package com.zhenzhen.apps;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class activity_app_04_01 extends AppCompatActivity {

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_04_01);

        final ImageView image = findViewById(R.id.image);
        image.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        image.setImageResource(R.drawable.green);
                        break;
                    case MotionEvent.ACTION_UP:
                        image.setImageResource(R.drawable.blue);
                        break;
                }
                return true;
            }
        });
    }
}