package com.example.movie;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.CaseMap;
import android.widget.Toast;

public class DiaryHelper extends SQLiteOpenHelper {

    Context d_context;

    public DiaryHelper(Context d_context) {
        super(d_context, "diary.db", null, 1);
        this.d_context = d_context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            String sql = "create table diary (" +
                    "num integer not null primary key autoincrement," +
                    "id varchar(30) not null," +
                    "title varchar(255) not null," +
                    "content varchar(255) not null" +
                    ");";
            db.execSQL(sql);
            Toast.makeText(d_context, "[diary] 테이블 생성", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
