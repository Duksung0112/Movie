package com.example.movie;

import android.graphics.Bitmap;
import android.widget.ImageView;

public class MyItem {

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
