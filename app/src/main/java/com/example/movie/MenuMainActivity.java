package com.example.movie;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MenuMainActivity extends AppCompatActivity {

    HomeActivity fragment1;
    DiaryActivity fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menumain);

        fragment1 = new HomeActivity();
        fragment2 = new DiaryActivity();


        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment1).commit();

        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.tabHome:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment1).commit();
                        return true;

                    case R.id.tabDiary:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment2).commit();
                        return true;

                }
                return false;
            }
        });
    }

}
