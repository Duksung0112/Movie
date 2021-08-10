package com.example.movie;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListPopupWindow;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;

public class HomeActivity extends Fragment {

    Button btstart;
    MovieHelper openHelper;
    SQLiteDatabase db;
    FloatingActionButton add;

    public HomeActivity() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home, container, false);

        ListView listView = (ListView)view.findViewById(R.id.movie_list);
        openHelper = new MovieHelper(getActivity());
        db = openHelper.getWritableDatabase();
        btstart = (Button) view.findViewById(R.id.btstart);
        add = (FloatingActionButton) view.findViewById(R.id.add);


        MyAdapter mMyAdapter = new MyAdapter();

        if (android.os.Build.VERSION.SDK_INT > 9) { StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); StrictMode.setThreadPolicy(policy); }

        db.execSQL("delete from movie where title='유전' or title='어바웃 타임'");

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


        byte[] poster2 = getByteArrayFromDrawable(getResources().getDrawable(R.drawable.movie_image2));

        p.bindString(1, "어바웃 타임");
        p.bindString(2, "모태솔로 팀(돔놀 글리슨)은 성인이 된 날, 아버지(빌 나이)로부터 놀랄만한 가문의 비밀을 듣게 된다.\n" +
                " 바로 시간을 되돌릴 수 있는 능력이 있다는 것!\n" +
                " 그것이 비록 히틀러를 죽이거나 여신과 뜨거운 사랑을 할 수는 없지만,\n" +
                " 여자친구는 만들어 줄 순 있으리..\n" +
                " \n" +
                " 꿈을 위해 런던으로 간 팀은 우연히 만난 사랑스러운 여인 메리에게 첫눈에 반하게 된다.\n" +
                " 그녀의 사랑을 얻기 위해 자신의 특별한 능력을 마음껏 발휘하는 팀.\n" +
                " 어설픈 대시, 어색한 웃음은 리와인드! 뜨거웠던 밤은 더욱 뜨겁게 리플레이!\n" +
                " 꿈에 그리던 그녀와 매일매일 최고의 순간을 보낸다.\n" +
                " \n" +
                " 하지만 그와 그녀의 사랑이 완벽해질수록 팀을 둘러싼 주변 상황들은 미묘하게 엇갈리고,\n" +
                " 예상치 못한 사건들이 여기저기 나타나기 시작하는데…\n" +
                " \n" +
                " 어떠한 순간을 다시 살게 된다면, 과연 완벽한 사랑을 이룰 수 있을까?");
        p.bindBlob(3, poster2);
        p.bindString(4, "로맨스");
        p.execute();


        String sql = "select * from movie;";
        Cursor cursor = db.rawQuery(sql, null);

        while (cursor.moveToNext()) {

            Bitmap posterimg = getAppIcon(cursor.getBlob(3)) ;
            String title = cursor.getString(1) ;


            mMyAdapter.addItem(posterimg, title);

        }


        listView.setAdapter(mMyAdapter);

        Spinner genreSpinner = (Spinner) view.findViewById(R.id.spGenre);
        ArrayAdapter genreAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.genre_choice, android.R.layout.simple_spinner_item);
        genreAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genreSpinner.setAdapter(genreAdapter);

        try {
            Field popup = Spinner.class.getDeclaredField("mPopup");
            popup.setAccessible(true);

            // Get private mPopup member variable and try cast to ListPopupWindow
            ListPopupWindow popupWindow = (ListPopupWindow) popup.get(genreSpinner);


            // Set popupWindow height to 500px
            popupWindow.setHeight(600);
        }
        catch (NoClassDefFoundError | ClassCastException | NoSuchFieldException | IllegalAccessException e) {
            // silently fail...
        }

        btstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), PhotoActivity.class));


            }

        });

        View.OnClickListener allMovieOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new aMovieActivity()).commit();

            }
        };

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "전체 영화 리스트", Snackbar.LENGTH_LONG)
                        .setAction("AllMovie", allMovieOnClickListener).show();
            }
        });


        return view;
    }


    public Bitmap getAppIcon(byte[] b) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(b,0,b.length);

        return bitmap;
    }

    public byte[] getByteArrayFromDrawable(Drawable d) {
        Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] data = stream.toByteArray();

        return data;
    }
}
