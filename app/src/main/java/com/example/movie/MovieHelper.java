package com.example.movie;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MovieHelper extends SQLiteOpenHelper {

    Context context;

    public MovieHelper(Context context) {
        super(context, "movie.db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            String sql = "create table movie (" +
                    "num integer not null primary key autoincrement," +
                    "title varchar(30) not null," +
                    "content varchar(255) not null," +
                    "poster_image blob not null," +
                    "genre varchar(10) not null" +
                    ");";
            db.execSQL(sql);
            Toast.makeText(context, "[movie] 테이블 생성", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
