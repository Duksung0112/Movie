package com.example.movie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edtId, edtPw;
    Button btnLogin, btnJoin;
    UserHelper openHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
            }

        }) ;









    }
}