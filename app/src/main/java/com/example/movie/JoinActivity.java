package com.example.movie;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class JoinActivity extends Activity {
    TextView tvId, tvPw , tvPwcheck, tvName;
    EditText edtId, edtPw , edtPwcheck, edtName;
    Button btnIdcheck , btnJoin2;
    String TAG = "Retrofit";

    @Override
    protected void onCreate(Bundle SavedInstanceState){
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.join);

        //Retrofit 인스턴스 생성
        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl("http://3.36.121.174:8081/")    // baseUrl 등록
                .addConverterFactory(GsonConverterFactory.create())  // Gson 변환기 등록
                .build();

        // 레트로핏 인터페이스 객체 구현
        RetrofitService service = retrofit.create(RetrofitService.class);

        btnJoin2 = (Button) findViewById(R.id.btnJoin2);
        btnIdcheck = (Button) findViewById(R.id.btnIdcheck);
        edtId = (EditText) findViewById(R.id.edtId);
        edtPw = (EditText) findViewById(R.id.edtPw);
        edtPwcheck = (EditText) findViewById(R.id.edtPwcheck);
        edtName = (EditText) findViewById(R.id.edtName);


        btnIdcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String userid1 = edtId.getText().toString();

                Call<PostResultUserInfo> call = service.getId(userid1);

                call.enqueue(new Callback<PostResultUserInfo>() {
                    @Override
                    public void onResponse(Call<PostResultUserInfo> call, Response<PostResultUserInfo> response) {
                        Log.e(TAG, "onResponse");
                        if(response.isSuccessful()){
                            Log.e(TAG, "onResponse success");
                            PostResultUserInfo result = response.body();

                            if (result != null) {
                                Toast.makeText(JoinActivity.this, "이미 존재하는 ID입니다.", Toast.LENGTH_SHORT).show();

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
                        Toast.makeText(JoinActivity.this, "이 ID를 사용할 수 있습니다.", Toast.LENGTH_SHORT).show();
                    }
                });


            }

        });


        btnJoin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userid2 = edtId.getText().toString();
                String pw = edtPw.getText().toString();
                String pwcheck = edtPwcheck.getText().toString();
                String username = edtName.getText().toString();


                if(edtId.getText().length() == 0||edtPw.getText().length() == 0||edtPwcheck.getText().length() == 0||edtName.getText().length() == 0) {
                    Toast.makeText(JoinActivity.this, "빈 항목을 확인해주세요.", Toast.LENGTH_SHORT).show();

                } else if (edtPw.getText().toString().equals(edtPwcheck.getText().toString())==false) {
                    Toast.makeText(JoinActivity.this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();

                } else {

                    PostResultUserInfo postresultuserinfo = new PostResultUserInfo(userid2, pw, username);

                    Call<PostResultUserInfo> call = service.AddUser(postresultuserinfo);

                    call.enqueue(new Callback<PostResultUserInfo>() {
                        @Override
                        public void onResponse(Call<PostResultUserInfo> call, Response<PostResultUserInfo> response) {
                            if(response.isSuccessful()) {
                                Log.e(TAG, "adduser success");
                                Toast.makeText(JoinActivity.this, "회원가입을 축하합니다", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(JoinActivity.this, MainActivity.class));

                            } else{
                                // 실패
                                Log.e(TAG, "adduser fail");
                                Log.e(TAG, " " + response.code());
                            }
                        }

                        @Override
                        public void onFailure(Call<PostResultUserInfo> call, Throwable t) {
                            Log.e(TAG, "onFailure: " + t.getMessage());
                        }
                    });



                }

            }

        });


    }

}

