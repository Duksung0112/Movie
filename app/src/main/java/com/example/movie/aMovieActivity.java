package com.example.movie;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class aMovieActivity extends Fragment {
    String TAG = "Retrofit aMovie";
    Bitmap bitmap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_all_movie, container, false);

        //Retrofit 인스턴스 생성
        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl("http://3.38.103.7:8081/")    // baseUrl 등록
                .addConverterFactory(GsonConverterFactory.create())  // Gson 변환기 등록
                .build();

        // 레트로핏 인터페이스 객체 구현
        RetrofitService service = retrofit.create(RetrofitService.class);

        ImageView iv1 =(ImageView) view.findViewById((R.id.movie_img1));
        ImageView iv2 =(ImageView) view.findViewById((R.id.movie_img2));
        ImageView iv3 =(ImageView) view.findViewById((R.id.movie_img3));
        ImageView iv4 =(ImageView) view.findViewById((R.id.movie_img4));
        ImageView iv5 =(ImageView) view.findViewById((R.id.movie_img5));
        ImageView iv6 =(ImageView) view.findViewById((R.id.movie_img6));
        ImageView iv7 =(ImageView) view.findViewById((R.id.movie_img7));
        ImageView iv8 =(ImageView) view.findViewById((R.id.movie_img8));

        iv1.setAdjustViewBounds(true);
        iv1.setMaxWidth(300);
        iv1.setMaxHeight(500);

        iv2.setAdjustViewBounds(true);
        iv2.setMaxWidth(300);
        iv2.setMaxHeight(500);

        iv3.setAdjustViewBounds(true);
        iv3.setMaxWidth(300);
        iv3.setMaxHeight(500);

        iv4.setAdjustViewBounds(true);
        iv4.setMaxWidth(300);
        iv4.setMaxHeight(500);

        iv5.setAdjustViewBounds(true);
        iv5.setMaxWidth(300);
        iv5.setMaxHeight(500);

        iv6.setAdjustViewBounds(true);
        iv6.setMaxWidth(300);
        iv6.setMaxHeight(500);

        iv7.setAdjustViewBounds(true);
        iv7.setMaxWidth(300);
        iv7.setMaxHeight(500);

        iv8.setAdjustViewBounds(true);
        iv8.setMaxWidth(300);
        iv8.setMaxHeight(500);


        Call<List<PostResultMovie>> call = service.getByGenre("코미디");

        call.enqueue(new Callback<List<PostResultMovie>>() {
            @Override
            public void onResponse(Call<List<PostResultMovie>> call, Response<List<PostResultMovie>> response) {
                Log.e(TAG, "call onResponse");
                if (response.isSuccessful()) {
                    Log.e(TAG, "call onResponse success");
                    List<PostResultMovie> result = response.body();

                    Thread uThread = new Thread() {
                        @Override
                        public void run(){
                            try{
                                //서버에 올려둔 이미지 URL
                                URL url = new URL("http://3.38.103.7" + result.get(0).poster_image);
                                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                                conn.setDoInput(true); //Server 통신에서 입력 가능한 상태로 만듦
                                conn.connect(); //연결된 곳에 접속할 때 (connect() 호출해야 실제 통신 가능함)
                                InputStream is = conn.getInputStream(); //inputStream 값 가져오기
                                bitmap = BitmapFactory.decodeStream(is); // Bitmap으로 반환
                            }catch (MalformedURLException e){
                                e.printStackTrace();
                            }catch (IOException e){
                                e.printStackTrace();
                            }
                        }
                    };
                    uThread.start(); // 작업 Thread 실행
                    try{
                        uThread.join();
                        iv1.setImageBitmap(bitmap);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }

                    Thread uThread2 = new Thread() {
                        @Override
                        public void run(){
                            try{
                                //서버에 올려둔 이미지 URL
                                URL url = new URL("http://3.38.103.7" + result.get(1).poster_image);
                                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                                conn.setDoInput(true); //Server 통신에서 입력 가능한 상태로 만듦
                                conn.connect(); //연결된 곳에 접속할 때 (connect() 호출해야 실제 통신 가능함)
                                InputStream is = conn.getInputStream(); //inputStream 값 가져오기
                                bitmap = BitmapFactory.decodeStream(is); // Bitmap으로 반환
                            }catch (MalformedURLException e){
                                e.printStackTrace();
                            }catch (IOException e){
                                e.printStackTrace();
                            }
                        }
                    };
                    uThread2.start(); // 작업 Thread 실행
                    try{
                        uThread2.join();
                        iv2.setImageBitmap(bitmap);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                     

                } else {
                    // 실패
                    Log.e(TAG, "call onResponse fail");
                }
            }
            @Override
            public void onFailure(Call<List<PostResultMovie>> call, Throwable t) {
                // 통신 실패
                Log.e(TAG, "call onFailure: " + t.getMessage());
            }

        });


        Call<List<PostResultMovie>> call2 = service.getByGenre("로맨스");

        call2.enqueue(new Callback<List<PostResultMovie>>() {
            @Override
            public void onResponse(Call<List<PostResultMovie>> call, Response<List<PostResultMovie>> response) {
                Log.e(TAG, "call onResponse");
                if (response.isSuccessful()) {
                    Log.e(TAG, "call onResponse success");
                    List<PostResultMovie> result = response.body();

                    Thread uThread = new Thread() {
                        @Override
                        public void run(){
                            try{
                                //서버에 올려둔 이미지 URL
                                URL url = new URL("http://3.38.103.7" + result.get(0).poster_image);
                                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                                conn.setDoInput(true); //Server 통신에서 입력 가능한 상태로 만듦
                                conn.connect(); //연결된 곳에 접속할 때 (connect() 호출해야 실제 통신 가능함)
                                InputStream is = conn.getInputStream(); //inputStream 값 가져오기
                                bitmap = BitmapFactory.decodeStream(is); // Bitmap으로 반환
                            }catch (MalformedURLException e){
                                e.printStackTrace();
                            }catch (IOException e){
                                e.printStackTrace();
                            }
                        }
                    };
                    uThread.start(); // 작업 Thread 실행
                    try{
                        uThread.join();
                        iv3.setImageBitmap(bitmap);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }

                    Thread uThread2 = new Thread() {
                        @Override
                        public void run(){
                            try{
                                //서버에 올려둔 이미지 URL
                                URL url = new URL("http://3.38.103.7" + result.get(1).poster_image);
                                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                                conn.setDoInput(true); //Server 통신에서 입력 가능한 상태로 만듦
                                conn.connect(); //연결된 곳에 접속할 때 (connect() 호출해야 실제 통신 가능함)
                                InputStream is = conn.getInputStream(); //inputStream 값 가져오기
                                bitmap = BitmapFactory.decodeStream(is); // Bitmap으로 반환
                            }catch (MalformedURLException e){
                                e.printStackTrace();
                            }catch (IOException e){
                                e.printStackTrace();
                            }
                        }
                    };
                    uThread2.start(); // 작업 Thread 실행
                    try{
                        uThread2.join();
                        iv4.setImageBitmap(bitmap);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }


                } else {
                    // 실패
                    Log.e(TAG, "call onResponse fail");
                }
            }
            @Override
            public void onFailure(Call<List<PostResultMovie>> call, Throwable t) {
                // 통신 실패
                Log.e(TAG, "call onFailure: " + t.getMessage());
            }

        });



        Call<List<PostResultMovie>> call3 = service.getByGenre("액션");

        call3.enqueue(new Callback<List<PostResultMovie>>() {
            @Override
            public void onResponse(Call<List<PostResultMovie>> call, Response<List<PostResultMovie>> response) {
                Log.e(TAG, "call onResponse");
                if (response.isSuccessful()) {
                    Log.e(TAG, "call onResponse success");
                    List<PostResultMovie> result = response.body();

                    Thread uThread = new Thread() {
                        @Override
                        public void run(){
                            try{
                                //서버에 올려둔 이미지 URL
                                URL url = new URL("http://3.38.103.7" + result.get(0).poster_image);
                                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                                conn.setDoInput(true); //Server 통신에서 입력 가능한 상태로 만듦
                                conn.connect(); //연결된 곳에 접속할 때 (connect() 호출해야 실제 통신 가능함)
                                InputStream is = conn.getInputStream(); //inputStream 값 가져오기
                                bitmap = BitmapFactory.decodeStream(is); // Bitmap으로 반환
                            }catch (MalformedURLException e){
                                e.printStackTrace();
                            }catch (IOException e){
                                e.printStackTrace();
                            }
                        }
                    };
                    uThread.start(); // 작업 Thread 실행
                    try{
                        uThread.join();
                        iv5.setImageBitmap(bitmap);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }

                    Thread uThread2 = new Thread() {
                        @Override
                        public void run(){
                            try{
                                //서버에 올려둔 이미지 URL
                                URL url = new URL("http://3.38.103.7" + result.get(1).poster_image);
                                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                                conn.setDoInput(true); //Server 통신에서 입력 가능한 상태로 만듦
                                conn.connect(); //연결된 곳에 접속할 때 (connect() 호출해야 실제 통신 가능함)
                                InputStream is = conn.getInputStream(); //inputStream 값 가져오기
                                bitmap = BitmapFactory.decodeStream(is); // Bitmap으로 반환
                            }catch (MalformedURLException e){
                                e.printStackTrace();
                            }catch (IOException e){
                                e.printStackTrace();
                            }
                        }
                    };
                    uThread2.start(); // 작업 Thread 실행
                    try{
                        uThread2.join();
                        iv6.setImageBitmap(bitmap);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }


                } else {
                    // 실패
                    Log.e(TAG, "call onResponse fail");
                }
            }
            @Override
            public void onFailure(Call<List<PostResultMovie>> call, Throwable t) {
                // 통신 실패
                Log.e(TAG, "call onFailure: " + t.getMessage());
            }

        });

        Call<List<PostResultMovie>> call4 = service.getByGenre("공포");

        call4.enqueue(new Callback<List<PostResultMovie>>() {
            @Override
            public void onResponse(Call<List<PostResultMovie>> call, Response<List<PostResultMovie>> response) {
                Log.e(TAG, "call onResponse");
                if (response.isSuccessful()) {
                    Log.e(TAG, "call onResponse success");
                    List<PostResultMovie> result = response.body();

                    Thread uThread = new Thread() {
                        @Override
                        public void run(){
                            try{
                                //서버에 올려둔 이미지 URL
                                URL url = new URL("http://3.38.103.7" + result.get(0).poster_image);
                                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                                conn.setDoInput(true); //Server 통신에서 입력 가능한 상태로 만듦
                                conn.connect(); //연결된 곳에 접속할 때 (connect() 호출해야 실제 통신 가능함)
                                InputStream is = conn.getInputStream(); //inputStream 값 가져오기
                                bitmap = BitmapFactory.decodeStream(is); // Bitmap으로 반환
                            }catch (MalformedURLException e){
                                e.printStackTrace();
                            }catch (IOException e){
                                e.printStackTrace();
                            }
                        }
                    };
                    uThread.start(); // 작업 Thread 실행
                    try{
                        uThread.join();
                        iv7.setImageBitmap(bitmap);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }

                    Thread uThread2 = new Thread() {
                        @Override
                        public void run(){
                            try{
                                //서버에 올려둔 이미지 URL
                                URL url = new URL("http://3.38.103.7" + result.get(1).poster_image);
                                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                                conn.setDoInput(true); //Server 통신에서 입력 가능한 상태로 만듦
                                conn.connect(); //연결된 곳에 접속할 때 (connect() 호출해야 실제 통신 가능함)
                                InputStream is = conn.getInputStream(); //inputStream 값 가져오기
                                bitmap = BitmapFactory.decodeStream(is); // Bitmap으로 반환
                            }catch (MalformedURLException e){
                                e.printStackTrace();
                            }catch (IOException e){
                                e.printStackTrace();
                            }
                        }
                    };
                    uThread2.start(); // 작업 Thread 실행
                    try{
                        uThread2.join();
                        iv8.setImageBitmap(bitmap);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }


                } else {
                    // 실패
                    Log.e(TAG, "call onResponse fail");
                }
            }
            @Override
            public void onFailure(Call<List<PostResultMovie>> call, Throwable t) {
                // 통신 실패
                Log.e(TAG, "call onFailure: " + t.getMessage());
            }

        });





        // 임시로 화면 이동 되게만 해놓음
        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getFragmentManager().beginTransaction().replace(R.id.container, new MovieInformationActivity()).commit();

                }

        });


        return view;

    }
}