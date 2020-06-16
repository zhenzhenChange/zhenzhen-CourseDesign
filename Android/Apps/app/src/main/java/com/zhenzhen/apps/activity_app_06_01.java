package com.zhenzhen.apps;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

public class activity_app_06_01 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_06_01);
    }

    public void exit(View view) {
        AlertDialog.Builder exit = new AlertDialog.Builder(this);
        exit.setTitle("提示");
        exit.setMessage("你确定要退出当前页吗？");
        exit.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                activity_app_06_01.this.finish();
            }
        });
        exit.setNegativeButton("取消", null);
        exit.create().show();
    }

    public void single(View view) {
        String[] state = new String[]{"在线", "隐身", "忙碌", "离开", "离线"};
        AlertDialog.Builder single = new AlertDialog.Builder(this);
        single.setIcon(R.mipmap.ic_launcher);
        single.setTitle("请选择您的状态");
        single.setSingleChoiceItems(state, 1, null);
        single.setPositiveButton("确定", null);
        single.setNegativeButton("取消", null);
        single.create().show();
    }

    public void multi(View view) {
        String[] hobbies = new String[]{"音乐", "网游", "运动", "阅读", "追剧"};
        boolean[] status = {false, true, false, false, true};
        AlertDialog.Builder multi = new AlertDialog.Builder(this);
        multi.setTitle("请勾选您的爱好");
        multi.setIcon(R.mipmap.ic_launcher);
        multi.setMultiChoiceItems(hobbies, status, null);
        multi.setPositiveButton("确定", null);
        multi.setNegativeButton("取消", null);
        multi.create().show();
    }

    public void login(View view) {
        AlertDialog.Builder login = new AlertDialog.Builder(this);
        View loginView = getLayoutInflater().inflate(R.layout.activity_app_06_01_login, null);
        login.setView(loginView);
        login.setPositiveButton("登录", null);
        login.setNegativeButton("取消", null);
        login.create().show();
    }
}