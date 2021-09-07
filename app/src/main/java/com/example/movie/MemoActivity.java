package com.example.movie;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import retrofit2.converter.gson.GsonConverterFactory;

public class MemoActivity extends Fragment {

    String genre, title;
    TextView tvgenre, tvtitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.diary_memo, container, false);

        //Retrofit 인스턴스 생성
        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl("http://3.36.121.174:8081/")    // baseUrl 등록
                .addConverterFactory(GsonConverterFactory.create())  // Gson 변환기 등록
                .build();

        // 레트로핏 인터페이스 객체 구현
        RetrofitService service = retrofit.create(RetrofitService.class);

        tvgenre = (TextView) view.findViewById(R.id.tvgenre);
        tvtitle = (TextView) view.findViewById(R.id.tvtitle);

        if (getArguments() != null) {
            genre = getArguments().getString("genre"); // 프래그먼트1에서 받아온 값 넣기
            title = getArguments().getString("title"); // 프래그먼트1에서 받아온 값 넣기

            System.out.println(genre);

            tvgenre.setText("[" + genre + "] ");
            tvtitle.setText(title);
        }
        return view;
    }
}
