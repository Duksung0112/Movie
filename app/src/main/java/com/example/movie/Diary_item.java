package com.example.movie;

import android.graphics.Bitmap;

public class Diary_item {
    private Bitmap posterimg;
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Bitmap getPosterImg() {
        return posterimg;
    }

    public void setPosterImg(Bitmap posterimg) {
        this.posterimg = posterimg;
    }
}
