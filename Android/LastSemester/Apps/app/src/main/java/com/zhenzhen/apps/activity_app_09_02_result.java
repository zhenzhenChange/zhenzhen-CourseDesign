package com.zhenzhen.apps;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class activity_app_09_02_result extends AppCompatActivity {
    private EditText resultEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_09_02_result);
        resultEdit = findViewById(R.id.resultEdit);
        Intent intent = getIntent();
        if (intent != null) {
            resultEdit.setText(intent.getStringExtra("send"));
        }
    }

    public void ResultToMain(View view) {
        Intent intent = new Intent();
        intent.putExtra("dataInfo", resultEdit.getText().toString());
        setResult(0x11, intent);
        activity_app_09_02_result.this.finish();
    }
}