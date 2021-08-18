package com.example.movie;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.movie.RecyclerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DiaryActivity extends Fragment {

    private FloatingActionButton add;
    private RecyclerView recyclerView;
    private ArrayList<Diary_item> memoItemList = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private RecyclerAdapter adapter;
    String TAG = "Retrofit";
    MenuMainActivity activity;

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
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new MemoActivity()).commit();

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

    private void getUserList(){
        final DiaryHelper diaryHelper= new DiaryHelper(getActivity().getApplicationContext());

        Cursor cursor = diaryHelper.getUserList();

        int count=0;

        while(cursor.moveToNext()){
            Diary_item di = new Diary_item();
            di.setTitle(cursor.getString(0));
            di.setContent(cursor.getString(1));
            adapter.addItem(di);
            count++;

        }

    }


}

