package com.example.movie;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class JoinActivity extends Activity {
    TextView tvId, tvPw , tvPwcheck, tvName;
    EditText edtId, edtPw , edtPwcheck, edtName;
    Button btnIdcheck , btnJoin2;
    UserHelper openHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle SavedInstanceState){
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.join);
        openHelper = new UserHelper(this);
        db = openHelper.getWritableDatabase();
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
                String sql1 = "select * from userinfo where id = '" + userid1 +"';";
                Cursor cursor1 = db.rawQuery(sql1, null);
                if(cursor1.getCount() == 1) {
                    Toast.makeText(JoinActivity.this, "이미 존재하는 ID입니다.", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(JoinActivity.this, "이 ID를 사용할 수 있습니다.", Toast.LENGTH_SHORT).show();
                }
                cursor1.close();
            }

        });


        btnJoin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userid2 = edtId.getText().toString();
                String pw = edtPw.getText().toString();
                String pwcheck = edtPwcheck.getText().toString();
                String username = edtName.getText().toString();
                String sql2 = "select * from userinfo where id = '" + userid2+"';";
                Cursor cursor2 = db.rawQuery(sql2, null);
                if(edtId.getText().length() == 0||edtPw.getText().length() == 0||edtPwcheck.getText().length() == 0||edtName.getText().length() == 0) {
                    Toast.makeText(JoinActivity.this, "빈 항목을 확인해주세요.", Toast.LENGTH_SHORT).show();

                } else if (cursor2.getCount() == 1) {
                    Toast.makeText(JoinActivity.this, "이미 존재하는 ID입니다.", Toast.LENGTH_SHORT).show();

                } else if (edtPw.getText().toString().equals(edtPwcheck.getText().toString())==false) {
                    Toast.makeText(JoinActivity.this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();

                } else {
                    String sql3 = "insert into userinfo(id, pw, name) values('" + userid2 + "','" + pw + "','" + username + "');";
                    db.execSQL(sql3);
                    Toast.makeText(JoinActivity.this, "회원가입을 축하합니다", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(JoinActivity.this, MainActivity.class));

                }
                cursor2.close();

            }

        });






    }

}

