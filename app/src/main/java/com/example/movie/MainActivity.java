package com.example.movie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    EditText edtId, edtPw;
    Button btnLogin, btnJoin;
    UserHelper openHelper;
    SQLiteDatabase db;
    String TAG = "Retrofit";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Retrofit 인스턴스 생성
        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl("http://3.34.186.243:8081/")    // baseUrl 등록
                .addConverterFactory(GsonConverterFactory.create())  // Gson 변환기 등록
                .build();

        // 레트로핏 인터페이스 객체 구현
        RetrofitService service = retrofit.create(RetrofitService.class);



        openHelper = new UserHelper(this);
        db = openHelper.getWritableDatabase();
        edtId= (EditText) findViewById(R.id.edtId);
        edtPw = (EditText) findViewById(R.id.edtPw);
        btnLogin=(Button) findViewById(R.id.btnLogin);
        btnJoin=(Button) findViewById(R.id.btnJoin);



        btnJoin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),JoinActivity.class);
                startActivity(intent);
                finish();
            }
        });



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = edtId.getText().toString();
                String pw = edtPw.getText().toString();


                /*
                String sql = "select * from userinfo where id = '"+ id + "' and pw = '" + pw+ "';";
                Cursor cursor = db.rawQuery(sql, null);
                while (cursor.moveToNext()) {
                    String no = cursor.getString(0);
                    String rest_id = cursor.getString(1);
                    Log.d("select", "no : " + no + "\nrest_id : " + rest_id);
                }
                if(cursor.getCount() == 1) {
                    Toast.makeText(MainActivity.this, id + "님 환영합니다", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(MainActivity.this, MenuMainActivity.class));
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "아이디 또는 비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show();
                }
                cursor.close();


                 */


                Call<PostResultUserInfo> call = service.getId(id);

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
                });


            }

        }) ;



    }
}