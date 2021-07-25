package com.example.movie;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class UserHelper extends SQLiteOpenHelper {

    Context context;

    public UserHelper(Context context) {
        super(context, "userinfo.db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            String sql = "create table userinfo (" +
                    "id varchar(20) not null primary key," +
                    "pw varchar(20) not null," +
                    "name varchar(20) not null" +
                    ");";
            db.execSQL(sql);
            Toast.makeText(context, "[userinfo] 테이블 생성", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
