package com.zhenzhen.apps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class activity_app_10_01 extends AppCompatActivity {
    private String fileName = "AppData.txt";

    private EditText read, write;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_10_01);
        read = findViewById(R.id.read);
        write = findViewById(R.id.write);
    }

    public void Write(View view) {
        try {
            FileOutputStream fileOutputStream = openFileOutput(fileName, Context.MODE_APPEND);
            fileOutputStream.write(write.getText().toString().getBytes());
            write.setText("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Read(View view) {
        try {
            FileInputStream fileInputStream = openFileInput(fileName);
            StringBuilder stringBuffer = new StringBuilder();
            byte[] bytes = new byte[256];
            int hasRead;
            while ((hasRead = fileInputStream.read(bytes)) != -1) {
                stringBuffer.append(new String(bytes, 0, hasRead));
            }
            read.setText(stringBuffer.toString());
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}