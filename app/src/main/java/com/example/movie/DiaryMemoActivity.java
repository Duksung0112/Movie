package com.example.movie;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class DiaryMemoActivity extends Fragment {

    String title, content, poster_image;
    TextView tvtitle;
    TextView tvcontent;
    int num;
    Button btnedit, btnback;
    String TAG = "Retrofit diarymemo";
    String base = "http://3.36.121.174";

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

        tvtitle = (TextView) view.findViewById(R.id.tvtitle);
        btnedit = (Button) view.findViewById(R.id.btnedit);
        btnback = (Button) view.findViewById(R.id.btnback);
        tvcontent = (TextView) view.findViewById(R.id.diarytext);

        Bundle extra = this.getArguments();
        if (extra != null) {
            title = getArguments().getString("title"); // 프래그먼트1에서 받아온 값 넣기
            poster_image = getArguments().getString("poster_img"); // 프래그먼트1에서 받아온 값 넣기
            content=getArguments().getString("content");
            num=extra.getInt("num");
            tvtitle.setText(title);
            tvcontent.setText(content);


            btnedit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Call<List<PostResultDiary>> call = service.getDiaryList();

                    call.enqueue(new Callback<List<PostResultDiary>>() {
                        @Override
                        public void onResponse(Call<List<PostResultDiary>> call, Response<List<PostResultDiary>> response) {
                            Log.e(TAG, "onResponse");
                            if(response.isSuccessful()){
                                Log.e(TAG, "onResponse success");

                                System.out.println("num="+num);
                                System.out.println("title="+title);
                                System.out.println("poster_image="+poster_image);
                                System.out.println("content="+content);



                                List<PostResultDiary> result = response.body();

                                Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                                bundle.putString("title", result.get(0).title);//번들에 넘길 값 저장
                                bundle.putString("poster_image", result.get(0).poster_image);//번들에 넘길 값 저장
                                bundle.putInt("num", result.get(0).num);//번들에 넘길 값 저장
                                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                                DiaryDetailActivity fragment4 = new DiaryDetailActivity();//프래그먼트4 선언
                                fragment4.setArguments(bundle);//번들을 프래그먼트4로 보낼 준비
                                transaction.replace(R.id.container, fragment4);
                                transaction.commit();

                            }
                            else{
                                // 실패
                                Log.e(TAG, "onResponse fail");
                            }
                        }

                        @Override
                        public void onFailure(Call<List<PostResultDiary>> call, Throwable t) {
                            // 통신 실패
                            Log.e(TAG, "onFailure: " + t.getMessage());
                        }
                    });
                }
            });
        }

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getActivity(), MenuMainActivity.class));

             }
            });

        return view;

    }

}