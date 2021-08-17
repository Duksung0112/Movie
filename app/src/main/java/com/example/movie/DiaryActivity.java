package com.example.movie;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
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
    MenuMainActivity activity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        activity = (MenuMainActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();

        activity = null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_diary, container, false);

        add = (FloatingActionButton) view.findViewById(R.id.addMemo);
        recyclerView = (RecyclerView) view.findViewById(R.id.mainRecyclerView);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        adapter = new RecyclerAdapter();



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

