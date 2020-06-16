package com.zhenzhen.apps;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class activity_app_06_02_02 extends AppCompatActivity {
    private Button btn1, btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_06_02_02);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        registerForContextMenu(btn1);
        registerForContextMenu(btn2);
    }

    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        switch (view.getId()) {
            case R.id.btn1:
                getMenuInflater().inflate(R.menu.context_menu_btn1, contextMenu);
                break;
            case R.id.btn2:
                getMenuInflater().inflate(R.menu.context_menu_btn2, contextMenu);
                break;
            default:
                break;
        }
        super.onCreateContextMenu(contextMenu, view, contextMenuInfo);
    }

    public boolean onContextItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.red:
                btn1.setTextColor(Color.RED);
                break;
            case R.id.green:
                btn1.setTextColor(Color.GREEN);
                break;
            case R.id.blue:
                btn1.setTextColor(Color.BLUE);
                break;
            case R.id.rename:
                AlertDialog.Builder name = new AlertDialog.Builder(this);
                name.setTitle("请输入新的名称");
                final EditText nameText = new EditText(this);
                name.setView(nameText);
                name.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        btn1.setText(nameText.getText());
                    }
                });
                name.setNegativeButton("取消", null);
                name.create().show();
                break;
            case R.id.bgBlack:
                btn2.setBackgroundColor(Color.BLACK);
                break;
            case R.id.bgGray:
                btn2.setBackgroundColor(Color.GRAY);
                break;
            case R.id.bgMagenta:
                btn2.setBackgroundColor(Color.MAGENTA);
            case R.id.small:
                btn2.setTextSize(10);
                break;
            case R.id.middle:
                btn2.setTextSize(30);
                break;
            case R.id.large:
                btn2.setTextSize(50);
                break;
            default:
                break;
        }
        return super.onContextItemSelected(menuItem);
    }
}