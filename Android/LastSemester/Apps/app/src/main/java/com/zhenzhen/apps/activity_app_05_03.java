package com.zhenzhen.apps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class activity_app_05_03 extends AppCompatActivity {
    private String[] apps = new String[]{"转账", "手机充值", "淘宝电影", "校园一卡通",
            "红包", "机票火车票", "记账本", "口碑外卖",
            "理财小工具", "快的打车", "收款", "我的快递",
            "天猫", "余额宝", "亲密付", "淘宝"};
    private int[] images = new int[]{R.drawable.p1, R.drawable.p2, R.drawable.p3, R.drawable.p4,
            R.drawable.p5, R.drawable.p6, R.drawable.p7, R.drawable.p8,
            R.drawable.p9, R.drawable.p10, R.drawable.p11, R.drawable.p12,
            R.drawable.p13, R.drawable.p14, R.drawable.p15, R.drawable.p16};

    private List<Map<String, Object>> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_05_03);

        GridView grid = findViewById(R.id.grid);
        initData();
        SimpleAdapter adapter = new SimpleAdapter(this, datas, R.layout.activity_app_05_03__grid_item,
                new String[]{"app", "image"},
                new int[]{R.id.app, R.id.image});
        grid.setAdapter(adapter);
    }

    public void initData() {
        int i;
        int length = apps.length;
        for (i = 0; i < length; i++) {
            Map<String, Object> item = new HashMap<>();
            item.put("app", apps[i]);
            item.put("image", images[i]);
            datas.add(item);
        }
    }
}
