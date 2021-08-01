package com.example.movie;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

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
}
