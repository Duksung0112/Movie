package com.example.movie;


import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.movie.Room.AppDatabase;
import com.example.movie.Room.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DetailActivity extends AppCompatActivity {
    private final int REQUEST_CODE = 200;

    private EditText detailTitle;
    private ImageView detailImage;
    private EditText detailDes;
    private AppDatabase db;

    private FloatingActionButton exit;
    private FloatingActionButton update;

    private int id;
    private String title;
    private String des;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initialized();

        update.setOnClickListener(v -> {
            title = detailTitle.getText().toString();
            des = detailDes.getText().toString();
            System.out.println("####1" + title + " " +des + " " + id);
            db.userDao().update(title, des, id);
            finish();
        });
        exit.setOnClickListener(v -> {
            Intent intent = new Intent(getApplication(), DiaryActivity.class);
            startActivity(intent);
        });
    }

    private void initialized() {
        update = findViewById(R.id.update);
        exit = findViewById(R.id.exit);
        detailTitle = findViewById(R.id.detailTitle);
        detailImage = findViewById(R.id.detailImage);
        detailDes = findViewById(R.id.detailDes);
        db = AppDatabase.getInstance(this);

        User detail = getIntent().getParcelableExtra("data");

        id = detail.getId();
        title = detail.getTitle();
        des = detail.getDes();

        detailTitle.setText(title);
        detailDes.setText(des);
    }


}