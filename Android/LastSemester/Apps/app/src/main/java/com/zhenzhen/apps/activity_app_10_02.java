package com.zhenzhen.apps;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.Calendar;

public class activity_app_10_02 extends AppCompatActivity {
    private EditText theme, content, date;
    private ListView result;
    private LinearLayout table;
    private activity_app_10_02_DataBaseHelper dataBaseHelper;
    private SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_10_02);
        theme = findViewById(R.id.theme);
        content = findViewById(R.id.content);
        date = findViewById(R.id.date);
        result = findViewById(R.id.result);
        table = findViewById(R.id.table);
        table.setVisibility(View.INVISIBLE);
        dataBaseHelper = new activity_app_10_02_DataBaseHelper(this, "SQLiteBase.db", null, 1);
        sqLiteDatabase = dataBaseHelper.getReadableDatabase();
    }

    public void btnDate(View view) {
        Calendar calendar = Calendar.getInstance();
        new DatePickerDialog(activity_app_10_02.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                date.setText(year + "-" + (month + 1) + "-" + day);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    public void add(View view) {
        sqLiteDatabase.execSQL("insert into tb_sqlite values(null,?,?,?)",
                new String[]{theme.getText().toString(), content.getText().toString(), date.getText().toString()});
        this.theme.setText("");
        this.content.setText("");
        this.date.setText("");
        table.setVisibility(View.INVISIBLE);
        Toast.makeText(activity_app_10_02.this, "添加成功~", Toast.LENGTH_SHORT).show();
        result.setAdapter(null);
    }

    public void show(View view) {
        table.setVisibility(View.VISIBLE);
        Cursor cursor = sqLiteDatabase.rawQuery("select * from tb_sqlite where theme like ? and content like ? and date like ?",
                new String[]{"%" + theme.getText().toString() + "%",
                        "%" + content.getText().toString() + "%",
                        "%" + date.getText().toString() + "%"});
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.activity_app_10_02__list, cursor,
                new String[]{"_id", "theme", "content", "date"},
                new int[]{R.id.tbID, R.id.tbTheme, R.id.tbContent, R.id.tbDate,},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        result.setAdapter(adapter);
    }

    protected void onDestroy() {
        super.onDestroy();
        if (sqLiteDatabase != null) {
            sqLiteDatabase.close();
            dataBaseHelper.close();
        }
    }
}