package com.zhenzhen.apps;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class activity_app_10_02_DataBaseHelper extends SQLiteOpenHelper {
    private String CREATE_TABLE_SQL = "create table tb_sqlite(_id integer primary key autoincrement,theme,content,date)";

    public activity_app_10_02_DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_TABLE_SQL);
    }

    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        System.out.println("[---->:" + oldVersion + "---->" + newVersion);
    }
}