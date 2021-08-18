package com.example.movie;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    public Activity activity;
    private ArrayList<Diary_item> di;

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.diary_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return di.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView key, memoTextView1, memoTextView2;
        public ViewHolder(final View itemView) {
            super(itemView);
            key = itemView.findViewById(R.id.key);
            memoTextView1 = itemView.findViewById(R.id.memoTextView1);
            memoTextView2 = itemView.findViewById(R.id.memoTextView2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Context context = itemView.getContext();
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", getAdapterPosition());
                    Intent mainIntent = new Intent(context, ModifyActivity.class);
                    mainIntent.putExtras(bundle);
                    mainIntent.putExtra("title", di.get(getAdapterPosition()).getTitle());
                    mainIntent.putExtra("content", di.get(getAdapterPosition()).getContent());
                    mainIntent.putExtra("itemnum", getAdapterPosition());
                    context.startActivity(mainIntent);
                }
            });
        }
    }
}