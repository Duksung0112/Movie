package com.example.movie;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;

public class MovieRecommendActivity extends AppCompatActivity {
    TextView tvgenre, tvtitle, tvexplain;
    ImageView imgposter;
    MovieHelper openHelper;
    SQLiteDatabase db;
    Button btnback;

    public MovieRecommendActivity() {
        // Required empty public constructor
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_recommend);


        openHelper = new MovieHelper(this);
        db = openHelper.getWritableDatabase();
        tvgenre = (TextView) findViewById(R.id.tvgenre);
        tvtitle = (TextView) findViewById(R.id.tvtitle);
        tvexplain = (TextView) findViewById(R.id.tvexplain);
        imgposter = (ImageView) findViewById(R.id.imgposter);
        btnback = (Button) findViewById(R.id.btnback);

        tvexplain.setMovementMethod(new ScrollingMovementMethod());


        // Movie 불러오기
        Cursor res = db.rawQuery("select * from movie where genre='공포'", null);

        while(res.moveToNext()) {
            tvgenre.setText("[" + res.getString(4) + "] ");
            tvtitle.setText(res.getString(1));
            imgposter.setImageBitmap(getAppIcon(res.getBlob(3)));

        }

        btnback.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MenuMainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }

    public byte[] getByteArrayFromDrawable(Drawable d) {
        Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] data = stream.toByteArray();

        return data;
    }

    public Bitmap getAppIcon(byte[] b) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(b,0,b.length);

        return bitmap;
    }



}
