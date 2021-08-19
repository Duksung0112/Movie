package com.example.movie;


import com.google.gson.annotations.SerializedName;

public class PostResultWishlist {

    //int num, title, synopsis, poster_image, genre

    @SerializedName("num")
    public int num;
    // @SerializedName으로 일치시켜 주지않을 경우엔 클래스 변수명이 일치해야함

    @SerializedName("title")
    public String title;

    @SerializedName("synopsis")
    public String synopsis;

    @SerializedName("poster_image")
    public String poster_image;

    @SerializedName("genre")
    public String genre;

    public PostResultWishlist(String title, String synopsis, String poster_image, String genre) {
        this.title = title;
        this.synopsis = synopsis;
        this.poster_image = poster_image;
        this.genre = genre;
    }

    // toString()을 Override 해주지 않으면 객체 주소값을 출력함
    @Override
    public String toString() {
        return "PostResultWishlist{" +
                "num=" + num +
                ", title='" + title + '\'' +
                ", synopsis='" + synopsis + '\'' +
                ", poster_image='" + poster_image + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}
