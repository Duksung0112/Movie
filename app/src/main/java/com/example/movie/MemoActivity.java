package com.example.movie;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;


public class MemoActivity extends Fragment {
    ImageView poster;
    EditText diary_title;
    EditText diary_content;
    Button btn_add;
    DiaryHelper openHelper;
    SQLiteDatabase db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_save_memo, container, false);

        btn_add = (Button) view.findViewById(R.id.btn_add);
        openHelper = new DiaryHelper(getActivity());
        db = openHelper.getWritableDatabase();
        diary_title = (EditText)view.findViewById(R.id.diary_title);
        diary_content = (EditText)view.findViewById(R.id.diary_content);
        poster = (ImageView)view.findViewById(R.id.poster);

        //Retrofit 인스턴스 생성
        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl("http://52.79.129.64:8081/")    // baseUrl 등록
                .addConverterFactory(GsonConverterFactory.create())  // Gson 변환기 등록
                .build();

        // 레트로핏 인터페이스 객체 구현
        RetrofitService service = retrofit.create(RetrofitService.class);



        //add 버튼을 누르면 메모 저장
        btn_add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String userTitle = diary_title.getText().toString();
                String userContent = diary_content.getText().toString();

                if(diary_title.getText().length() == 0||diary_content.getText().length() == 0) {
                    Toast.makeText(getActivity(), "빈 항목을 확인해주세요.", Toast.LENGTH_SHORT).show();

                }
                else {
                    PostResultDiary postResultDiary = new PostResultDiary(userTitle,userContent);

                    Call<PostResultDiary> call = service.AddDiary(postResultDiary);

                    call.enqueue(new Callback<PostResultDiary>() {
                        @Override
                        public void onResponse(Call<PostResultDiary> call, Response<PostResultDiary> response) {
                            if(response.isSuccessful()) {
                                Log.e(TAG, "adduser success");
                                Toast.makeText(getActivity(), "저장되었습니다", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getActivity(), MainActivity.class));

                            } else{
                                // 실패
                                Log.e(TAG, "adduser fail");
                                Log.e(TAG, " " + response.code());
                            }
                        }

                        @Override
                        public void onFailure(Call<PostResultDiary> call, Throwable t) {
                            Log.e(TAG, "onFailure: " + t.getMessage());
                        }
                    });

                }

            }
        });


        return view;
    }

}