package com.example.movie;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


import com.example.movie.Recycler.RecyclerAdapter;
import com.example.movie.Room.AppDatabase;
import com.example.movie.Room.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;


public class DiaryActivity extends Fragment {

    private final int SAVE_MEMO_ACTIVITY = 1;
    private FloatingActionButton add;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerAdapter adapter;
    private List<User> users;
    String TAG = "Retrofit";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_diary, container, false);

        add = (FloatingActionButton) view.findViewById(R.id.addMemo);
        recyclerView = (RecyclerView) view.findViewById(R.id.mainRecyclerView);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        adapter = new RecyclerAdapter();

        //Retrofit 인스턴스 생성
        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl("http://3.34.186.243:8081/")    // baseUrl 등록
                .addConverterFactory(GsonConverterFactory.create())  // Gson 변환기 등록
                .build();

        // 레트로핏 인터페이스 객체 구현
        RetrofitService service = retrofit.create(RetrofitService.class);

        users = AppDatabase.getInstance(getActivity()).userDao().getAll();
        int size = users.size();
        for(int i = 0; i < size; i++){
            adapter.addItem(users.get(i));
        }

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);


        View.OnClickListener SaveMemoOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new SaveMemoActivity()).commit();

            }
        };


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "SaveMemo", Snackbar.LENGTH_LONG)
                        .setAction("Go", SaveMemoOnClickListener).show();

                /*Call<PostResultUserInfo> call = service.getId(id);

                call.enqueue(new Callback<PostResultUserInfo>() {
                    @Override
                    public void onResponse(Call<PostResultUserInfo> call, Response<PostResultUserInfo> response) {
                        Log.e(TAG, "onResponse");
                        if(response.isSuccessful()){
                            Log.e(TAG, "onResponse success");
                            PostResultUserInfo result = response.body();

                            if (pw.equals(result.pw)) {

                                Toast.makeText(MainActivity.this, id + "님 환영합니다", Toast.LENGTH_SHORT).show();

                                startActivity(new Intent(MainActivity.this, MenuMainActivity.class));
                                finish();

                            } else {
                                Toast.makeText(MainActivity.this, "아이디 또는 비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show();
                            }


                        }
                        else{
                            // 실패
                            Log.e(TAG, "onResponse fail");
                        }
                    }

                    @Override
                    public void onFailure(Call<PostResultUserInfo> call, Throwable t) {
                        // 통신 실패
                        Log.e(TAG, "onFailure: " + t.getMessage());
                    }
                });*/

            }
        });




        return view;
    }
    @Override
    public void onStart() {
        users = AppDatabase.getInstance(getActivity()).userDao().getAll();
        adapter.addItems((ArrayList) users);
        super.onStart();
    }


}

