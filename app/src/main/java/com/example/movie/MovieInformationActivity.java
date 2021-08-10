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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class MovieInformationActivity extends Fragment {
    TextView tvgenre, tvtitle, tvcontent, tvstar;
    ImageView imgposter;
    MovieHelper openHelper;
    SQLiteDatabase db;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.movie_information, container, false);

        openHelper = new MovieHelper(getActivity());
        db = openHelper.getWritableDatabase();
        tvgenre = (TextView) view.findViewById(R.id.tvgenre);
        tvtitle = (TextView) view.findViewById(R.id.tvtitle);
        tvcontent = (TextView) view.findViewById(R.id.tvcontent);
        tvstar = (TextView) view.findViewById(R.id.tvstar);
        imgposter = (ImageView) view.findViewById(R.id.imgposter);

        tvcontent.setMovementMethod(new ScrollingMovementMethod());



        // Movie 불러오기
        Cursor res = db.rawQuery("select * from movie where genre='공포'", null);

        while(res.moveToNext()) {
            tvgenre.setText("[" + res.getString(4) + "] ");
            tvtitle.setText(res.getString(1));
            tvcontent.setText(res.getString(2));
            imgposter.setImageBitmap(getAppIcon(res.getBlob(3)));

        }


        String url = "https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=유전";
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements element = doc.select("div.info_group");

        Element el = element.get(2);
        
        tvstar.setText("(" + el.text() + "★)");




        return view;

    }



    public Bitmap getAppIcon(byte[] b) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(b,0,b.length);

        return bitmap;
    }





}
