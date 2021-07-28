package com.example.movie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button imageButton = (Button) findViewById(R.id.btn1);
        imageButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent=new Intent(getApplicationContext(), PhotoActivity.class);

                startActivity(intent);
            }
        });

        Button imageButton2 = (Button) findViewById(R.id.btn2);
        imageButton2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent=new Intent(getApplicationContext(), DiaryActivity.class);

                startActivity(intent);
            }
        });

        Button imageButton3 = (Button) findViewById(R.id.btn3);
        imageButton3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent=new Intent(getApplicationContext(), aMovieActivity.class);

                startActivity(intent);
            }
        });
    }
}