package com.example.movie;


import com.google.gson.annotations.SerializedName;

public class PostResultDiary {

    //string id, int num, string content

    @SerializedName("id")
    public String id;

    @SerializedName("num")
    public int num;
    // @SerializedName으로 일치시켜 주지않을 경우엔 클래스 변수명이 일치해야함

    @SerializedName("title")
    public String title;

    @SerializedName("content")
    public String content;

    @SerializedName("poster_image")
    public String poster_image;


    public PostResultDiary(String id, int num,  String title, String content, String poster_image) {
        this.id=id;
        this.num=num;
        this.content = content;
        this.title= title;
        this.poster_image=poster_image;
    }


    // toString()을 Override 해주지 않으면 객체 주소값을 출력함
    @Override
    public String toString() {
        return "PostResultDiary{" +
                "id='" + id + '\'' +
                ", num=" + num +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", poster_image='" + poster_image + '\'' +
                '}';
    }
}
