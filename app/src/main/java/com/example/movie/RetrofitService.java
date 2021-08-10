package com.example.movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitService {

    // @GET( EndPoint-자원위치(URI) )
    @GET("userinfo/get/{id}")
    Call<PostResultUserInfo> getId(@Path("id") String id);

    @GET("userinfo/list")
    Call<PostResultUserInfo> getList();

    @GET("userinfo/delete/{id}")
    Call<PostResultUserInfo> DeleteId(@Path("id") String id);





}
