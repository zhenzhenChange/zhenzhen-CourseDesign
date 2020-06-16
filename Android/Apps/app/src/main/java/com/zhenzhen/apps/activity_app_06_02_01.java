package com.zhenzhen.apps;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

public class activity_app_06_02_01 extends AppCompatActivity {
    private RelativeLayout root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_06_02_01);
        root = findViewById(R.id.root);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.exit:
                AlertDialog.Builder exit = new AlertDialog.Builder(this);
                exit.setCancelable(false);
                exit.setMessage("你确定要退出吗？");
                exit.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        activity_app_06_02_01.this.finish();
                    }
                });
                exit.setNegativeButton("取消", null);
                exit.create().show();
                break;
            case R.id.red:
                root.setBackgroundColor(Color.RED);
                break;
            case R.id.green:
                root.setBackgroundColor(Color.GREEN);
                break;
            case R.id.blue:
                root.setBackgroundColor(Color.BLUE);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }
}