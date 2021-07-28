package com.example.movie;

public class DataModel {
    String title;
    String content;
    int image_path;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.title = content;
    }

    public int getImage_path() {
        return image_path;
    }

    public void setImage_path(int image_path) {
        this.image_path = image_path;
    }

    public DataModel(String title, String content, int image_path) {
        this.title = title;
        this.image_path = image_path;
        this.content = content;
    }
}
