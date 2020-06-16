package com.zhenzhen.apps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class activity_app_02 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_02);

        TextView title = findViewById(R.id.title);
        title.setText(Html.fromHtml("安卓实验2"));
    }
}