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

    @GET("wishlist/list")
    Call<List<PostResultWishlist>> getWishlist();

    @POST("wishlist/add")
    Call<PostResultWishlist> AddWishlist(@Body PostResultWishlist postresultwishlist);

    @GET("wishlist/getbygenre/{genre}")
    Call<List<PostResultWishlist>> getByWishlistGenre(@Path("genre") String genre);

    @GET("wishlist/getbytitle/{title}")
    Call<List<PostResultWishlist>> getByWishlistTitle(@Path("title") String title);

    // @GET( EndPoint-자원위치(URI) )
    @POST("diary/add")
    Call<PostResultDiary> AddDiary(@Body PostResultDiary postresultdiary);

    @GET("diary/list")
    Call<List<PostResultDiary>> getDiaryList();

    @POST("diary/update")
    Call<PostResultDiary> UpdateDiary(@Body PostResultDiary postresultdiary);

    @GET("diary/delete/{num}")
    Call<PostResultDiary> DeleteNum (@Path("num") String num);



}
