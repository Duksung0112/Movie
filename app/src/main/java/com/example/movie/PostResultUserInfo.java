package com.example.movie;


import com.google.gson.annotations.SerializedName;

public class PostResultUserInfo {

    @SerializedName("id")
    public String id;
    // @SerializedName으로 일치시켜 주지않을 경우엔 클래스 변수명이 일치해야함

    @SerializedName("pw")
    public String pw;

    @SerializedName("name")
    public String name;

    // toString()을 Override 해주지 않으면 객체 주소값을 출력함
    @Override
    public String toString() {
        return "PostResultUserInfo{" +
                "id='" + id + '\'' +
                ", pw='" + pw + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
