package com.zhenzhen.apps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class activity_app_05_02_02 extends AppCompatActivity {
    private String[] bookNames = {"Android应用程序开发", "Android编程", "Android编程经典案例解析",
            "移动电子商务", "IOS应用开发基础教程", "App开发案例教程"};

    private String[] authors = {"钟元生 高成珍", "钟元生 高成珍", "高成珍 钟元生",
            "钟元生 曹权", "钟元生", "钟元生"};

    private String[] presses = {"江西高校出版社", "清华大学出版社", "清华大学出版社",
            "电子工业出版社", "复旦大学出版社", "清华大学出版社"};

    private int[] bookIcons = {R.drawable.book01, R.drawable.book02, R.drawable.book03,
            R.drawable.book04, R.drawable.book05, R.drawable.book06};

    private List<Map<String, Object>> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_05_02_02);
        ListView booksList = findViewById(R.id.bookList);
        initData();
        SimpleAdapter adapter = new SimpleAdapter(this, datas, R.layout.activity_app_05_02_02__list_item,
                new String[]{"icon", "bookName", "author", "press"},
                new int[]{R.id.icon, R.id.name, R.id.author, R.id.press});
        booksList.setAdapter(adapter);
    }

    public void initData() {
        int i;
        int length = bookNames.length;
        for (i = 0; i < length; i++) {
            Map<String, Object> item = new HashMap<>();
            item.put("bookName", "书名:" + bookNames[i]);
            item.put("author", "作者:" + authors[i]);
            item.put("press", "出版社:" + presses[i]);
            item.put("icon", bookIcons[i]);
            datas.add(item);
        }
    }
}