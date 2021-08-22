package com.example.movie;

public class Diary_item {
    private String title;
    private String content;

    public Diary_item(){

    }

    public Diary_item(String title, String content){
        this.title = title;
        this.content = content;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title=title;
    }

    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.title=content;
    }
}
