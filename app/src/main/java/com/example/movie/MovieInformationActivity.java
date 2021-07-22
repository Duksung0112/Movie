package com.example.movie;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

public class MovieInformationActivity extends Activity {
    TextView tvgenre, tvtitle, tvcontent;
    ImageView imgposter;
    MovieHelper openHelper;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_information);

        openHelper = new MovieHelper(this);
        db = openHelper.getWritableDatabase();
        tvgenre = (TextView) findViewById(R.id.tvgenre);
        tvtitle = (TextView) findViewById(R.id.tvtitle);
        tvcontent = (TextView) findViewById(R.id.tvcontent);
        imgposter = (ImageView) findViewById(R.id.imgposter);

        tvcontent.setMovementMethod(new ScrollingMovementMethod());


        // Movie 삽입
        SQLiteStatement p = db.compileStatement("insert into movie(title, content, poster_image, genre) values(?, ?, ?, ?);");

        byte[] poster = getByteArrayFromDrawable(getResources().getDrawable(R.drawable.movie_image1));

        p.bindString(1, "유전");
        p.bindString(2, "가족이기에 피할 수 없는 운명이 그들을 덮쳤다!\n" +
                "‘애니’는 일주일 전 돌아가신 엄마의 유령이 집에 나타나는 것을 느낀다.\n" +
                " 애니가 엄마와 닮았다며 접근한 수상한 이웃 ‘조안’을 통해 엄마의 비밀을 발견하고,\n" +
                " 자신이 엄마와 똑같은 일을 저질렀음을 알게 된다.\n" +
                " 그리고 마침내 애니의 엄마로부터 시작돼\n" +
                " 아들 ‘피터’와 딸 ‘찰리’에게까지 이어진 저주의 실체가 정체를 드러내는데…");
        p.bindBlob(3, poster);
        p.bindString(4, "공포");
        p.execute();



        // Movie 불러오기
        Cursor res = db.rawQuery("select * from movie where genre='공포'", null);

        while(res.moveToNext()) {
            tvgenre.setText("[" + res.getString(4) + "] ");
            tvtitle.setText(res.getString(1));
            tvcontent.setText(res.getString(2));
            imgposter.setImageBitmap(getAppIcon(res.getBlob(3)));

        }











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
