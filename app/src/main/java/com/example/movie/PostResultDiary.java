package com.example.movie;


import com.google.gson.annotations.SerializedName;

public class PostResultDiary {

    //string id, int num, string content

    @SerializedName("id")
    public String id;

    @SerializedName("num")
    public int num;
    // @SerializedName으로 일치시켜 주지않을 경우엔 클래스 변수명이 일치해야함

    @SerializedName("content")
    public String content;


    public PostResultDiary(String id, int num, String content) {
        this.id=id;
        this.num=num;
        this.content = content;
    }

    public PostResultDiary(String id, int num){
        this.id=id;
        this.num=num;
    }

    // toString()을 Override 해주지 않으면 객체 주소값을 출력함
    @Override
    public String toString() {
        return "PostResultDiary{" +
                "id='" + id + '\'' +
                ", num=" + num +
                ", content='" + content + '\'' +
                '}';
    }
}
