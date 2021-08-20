package com.example.movie;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.movie.RecyclerAdapter;
import com.example.movie.Room.AppDatabase;
import com.example.movie.Room.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;


import java.util.ArrayList;
import java.util.List;


public class DiaryActivity extends Fragment {

    private final int SAVE_MEMO_ACTIVITY = 1;
    private FloatingActionButton add;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerAdapter adapter;
    private List<User> users;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_diary, container, false);

        add = (FloatingActionButton) view.findViewById(R.id.addMemo);
        recyclerView = (RecyclerView) view.findViewById(R.id.mainRecyclerView);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        adapter = new RecyclerAdapter();

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