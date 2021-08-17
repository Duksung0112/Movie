package com.example.movie;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitService {

    // @GET( EndPoint-자원위치(URI) )
    @GET("userinfo/get/{id}")
    Call<PostResultUserInfo> getId(@Path("id") String id);

    @GET("userinfo/list")
    Call<PostResultUserInfo> getList();

    @GET("userinfo/delete/{id}")
    Call<PostResultUserInfo> DeleteId(@Path("id") String id);

    @POST("userinfo/add")
    Call<PostResultUserInfo> AddUser(@Body PostResultUserInfo postresultuserinfo);

    @GET("movielist/getbygenre/{genre}")
    Call<List<PostResultMovie>> getByGenre(@Path("genre") String genre);

    @GET("movielist/getbytitle/{title}")
    Call<List<PostResultMovie>> getByTitle(@Path("title") String title);



}
