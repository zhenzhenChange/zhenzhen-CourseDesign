package com.zhenzhen.apps;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class activity_app_05_02_01 extends ListActivity {
    private String[] foods = {"面条", "挂面", "冷面", "意大利面", "泡面", "火鸡面"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, foods);
        setListAdapter(adapter);
    }
}