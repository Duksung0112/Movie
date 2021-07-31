package com.example.movie;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.movie.Recycler.RecyclerAdapter;
import com.example.movie.Room.AppDatabase;
import com.example.movie.Room.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.ArrayList;
import java.util.List;


public class DiaryActivity extends Fragment {

    private final int SAVE_MEMO_ACTIVITY = 1;
    private FloatingActionButton add;

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerAdapter adapter;
    private List<User> users;

    public void initialized() {
        users = AppDatabase.getInstance(getActivity().getApplicationContext()).userDao().getAll();
        int size = users.size();
        for(int i = 0; i < size; i++){
            adapter.addItem(users.get(i));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_diary, container, false);

        recyclerView = (RecyclerView)view.findViewById(R.id.mainRecyclerView);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        adapter = new RecyclerAdapter();

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        add = view.findViewById(R.id.addMemo);

        initialized();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                Intent intent = new Intent(getActivity().getApplicationContext(), SaveMemoActivity.class);
                startActivity(intent);
            }
        });

        if (android.os.Build.VERSION.SDK_INT > 9) { StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); StrictMode.setThreadPolicy(policy); }



        return view;
    }

    @Override
    public void onStart() {
        users = AppDatabase.getInstance(getActivity()).userDao().getAll();
        adapter.addItems((ArrayList) users);
        super.onStart();
    }


}