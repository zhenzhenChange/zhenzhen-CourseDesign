package com.zhenzhen.apps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

public class activity_app_04_02 extends AppCompatActivity {
    private int[] images = {R.drawable.pic0, R.drawable.pic1, R.drawable.pic2, R.drawable.pic3};
    private int currentIndex = 0;
    private ImageSwitcher switcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_04_02);
        switcher = findViewById(R.id.switcher);
        switcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                return new ImageView(activity_app_04_02.this);
            }
        });
        switcher.setImageResource(images[currentIndex]);
    }

    public void pre(View view) {
        int imagesLength = images.length;
        currentIndex = (currentIndex - 1 + imagesLength) % imagesLength;
        switcher.setImageResource(images[currentIndex]);
    }

    public void next(View view) {
        int imagesLength = images.length;
        currentIndex = (currentIndex + 1) % imagesLength;
        switcher.setImageResource(images[currentIndex]);
    }
}