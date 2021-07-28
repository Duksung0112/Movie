package com.example.movie;

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

import java.io.ByteArrayOutputStream;

public class MovieRecommendActivity extends Fragment {
    TextView tvgenre, tvtitle, tvexplain;
    ImageView imgposter;
    MovieHelper openHelper;
    SQLiteDatabase db;

    public MovieRecommendActivity() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.movie_recommend, container, false);

        openHelper = new MovieHelper(getActivity());
        db = openHelper.getWritableDatabase();
        tvgenre = (TextView) view.findViewById(R.id.tvgenre);
        tvtitle = (TextView) view.findViewById(R.id.tvtitle);
        tvexplain = (TextView) view.findViewById(R.id.tvexplain);
        imgposter = (ImageView) view.findViewById(R.id.imgposter);

        tvexplain.setMovementMethod(new ScrollingMovementMethod());


        // Movie 불러오기
        Cursor res = db.rawQuery("select * from movie where genre='공포'", null);

        while(res.moveToNext()) {
            tvgenre.setText("[" + res.getString(4) + "] ");
            tvtitle.setText(res.getString(1));
            imgposter.setImageBitmap(getAppIcon(res.getBlob(3)));

        }


        return view;

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
