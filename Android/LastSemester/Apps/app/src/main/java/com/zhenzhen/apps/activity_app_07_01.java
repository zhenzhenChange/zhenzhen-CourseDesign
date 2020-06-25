package com.zhenzhen.apps;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class activity_app_07_01 extends AppCompatActivity implements View.OnClickListener {
    private boolean isBold = false, isTilt = false;
    private TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_07_01);

        test = findViewById(R.id.test);
        test.setTypeface(Typeface.DEFAULT);

        Button red = findViewById(R.id.red);
        Button blue = findViewById(R.id.blue);
        Button green = findViewById(R.id.green);

        Button bigger = findViewById(R.id.bigger);
        Button smaller = findViewById(R.id.smaller);

        Button bold = findViewById(R.id.bold);
        Button old = findViewById(R.id.old);
        Button tilt = findViewById(R.id.tilt);

        ColorListner colorListner = new ColorListner();
        activity_app_07_01_SizeListener sizeListener = new activity_app_07_01_SizeListener(test);

        red.setOnClickListener(colorListner);
        blue.setOnClickListener(colorListner);
        green.setOnClickListener(colorListner);

        bigger.setOnClickListener(sizeListener);
        smaller.setOnClickListener(sizeListener);

        tilt.setOnClickListener(this);
        bold.setOnClickListener(this);
        old.setOnClickListener(this);

        test.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(activity_app_07_01.this);
                alert.setTitle("请输入新内容");
                alert.setIcon(R.mipmap.ic_launcher);
                final EditText content = new EditText(activity_app_07_01.this);
                alert.setView(content);
                alert.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        test.setText(content.getText().toString());
                    }
                });
                alert.setNegativeButton("取消", null);
                alert.create().show();
                return false;
            }
        });
    }

    private class ColorListner implements View.OnClickListener {
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.red:
                    test.setTextColor(Color.RED);
                    break;
                case R.id.green:
                    test.setTextColor(Color.GREEN);
                    break;
                case R.id.blue:
                    test.setTextColor(Color.BLUE);
                    break;
                default:
                    break;
            }
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tilt:
                isTilt = !isTilt;
                break;
            case R.id.bold:
                isBold = !isBold;
                break;
            case R.id.old:
                isBold = false;
                isTilt = false;
                break;
            default:
                break;
        }
        if (isTilt) {
            if (isBold) test.setTypeface(Typeface.MONOSPACE, Typeface.BOLD_ITALIC);
            else test.setTypeface(Typeface.MONOSPACE, Typeface.ITALIC);
        } else {
            if (isBold) test.setTypeface(Typeface.MONOSPACE, Typeface.BOLD);
            else test.setTypeface(Typeface.MONOSPACE, Typeface.NORMAL);
        }
    }
}