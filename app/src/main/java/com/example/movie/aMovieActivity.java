package com.example.movie;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;

public class aMovieActivity extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_all_movie, container, false);

        ImageView iv1 =(ImageView) view.findViewById((R.id.movie_img1));
        ImageView iv2 =(ImageView) view.findViewById((R.id.movie_img2));
        ImageView iv3 =(ImageView) view.findViewById((R.id.movie_img3));
        ImageView iv4 =(ImageView) view.findViewById((R.id.movie_img4));

        iv1.setAdjustViewBounds(true);
        iv1.setMaxWidth(300);
        iv1.setMaxHeight(500);

        iv2.setAdjustViewBounds(true);
        iv2.setMaxWidth(300);
        iv2.setMaxHeight(500);

        iv3.setAdjustViewBounds(true);
        iv3.setMaxWidth(300);
        iv3.setMaxHeight(500);

        iv4.setAdjustViewBounds(true);
        iv4.setMaxWidth(300);
        iv4.setMaxHeight(500);

        iv1.setImageDrawable(getResources().getDrawable(R.drawable.blackwidow));
        iv2.setImageDrawable(getResources().getDrawable(R.drawable.inception));
        iv3.setImageDrawable(getResources().getDrawable(R.drawable.leon));
        iv4.setImageDrawable(getResources().getDrawable(R.drawable.cruella));



        // 임시로 화면 이동 되게만 해놓음
        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.container, new MovieInformationActivity()).commit();

            }

        });


        return view;

    }
}