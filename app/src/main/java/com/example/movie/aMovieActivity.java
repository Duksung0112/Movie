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
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
                .baseUrl("http://52.79.129.64:8081/")    // baseUrl 등록
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
        ImageView iv9 =(ImageView) view.findViewById((R.id.movie_img9));
        ImageView iv10 =(ImageView) view.findViewById((R.id.movie_img10));
        ImageView iv11 =(ImageView) view.findViewById((R.id.movie_img11));
        ImageView iv12 =(ImageView) view.findViewById((R.id.movie_img12));
        ImageView iv13 =(ImageView) view.findViewById((R.id.movie_img13));
        ImageView iv14 =(ImageView) view.findViewById((R.id.movie_img14));

        ImageView iv16 =(ImageView) view.findViewById((R.id.movie_img16));
        ImageView iv17 =(ImageView) view.findViewById((R.id.movie_img17));
        ImageView iv18 =(ImageView) view.findViewById((R.id.movie_img18));

        ImageView iv20 =(ImageView) view.findViewById((R.id.movie_img20));
        ImageView iv21 =(ImageView) view.findViewById((R.id.movie_img21));
        ImageView iv22 =(ImageView) view.findViewById((R.id.movie_img22));

        ImageView iv24 =(ImageView) view.findViewById((R.id.movie_img24));
        ImageView iv25 =(ImageView) view.findViewById((R.id.movie_img25));
        ImageView iv26 =(ImageView) view.findViewById((R.id.movie_img26));

        ImageView iv28 =(ImageView) view.findViewById((R.id.movie_img28));
        ImageView iv29 =(ImageView) view.findViewById((R.id.movie_img29));
        ImageView iv30 =(ImageView) view.findViewById((R.id.movie_img30));

        ImageView iv32 =(ImageView) view.findViewById((R.id.movie_img32));
        ImageView iv33 =(ImageView) view.findViewById((R.id.movie_img33));
        ImageView iv34 =(ImageView) view.findViewById((R.id.movie_img34));



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

        iv9.setAdjustViewBounds(true);
        iv9.setMaxWidth(300);
        iv9.setMaxHeight(500);

        iv10.setAdjustViewBounds(true);
        iv10.setMaxWidth(300);
        iv10.setMaxHeight(500);

        iv11.setAdjustViewBounds(true);
        iv11.setMaxWidth(300);
        iv11.setMaxHeight(500);

        iv12.setAdjustViewBounds(true);
        iv12.setMaxWidth(300);
        iv12.setMaxHeight(500);

        iv13.setAdjustViewBounds(true);
        iv13.setMaxWidth(300);
        iv13.setMaxHeight(500);

        iv14.setAdjustViewBounds(true);
        iv14.setMaxWidth(300);
        iv14.setMaxHeight(500);

        iv16.setAdjustViewBounds(true);
        iv16.setMaxWidth(300);
        iv16.setMaxHeight(500);

        iv17.setAdjustViewBounds(true);
        iv17.setMaxWidth(300);
        iv17.setMaxHeight(500);

        iv18.setAdjustViewBounds(true);
        iv18.setMaxWidth(300);
        iv18.setMaxHeight(500);


        iv20.setAdjustViewBounds(true);
        iv20.setMaxWidth(300);
        iv20.setMaxHeight(500);

        iv21.setAdjustViewBounds(true);
        iv21.setMaxWidth(300);
        iv21.setMaxHeight(500);

        iv22.setAdjustViewBounds(true);
        iv22.setMaxWidth(300);
        iv22.setMaxHeight(500);

        iv24.setAdjustViewBounds(true);
        iv24.setMaxWidth(300);
        iv24.setMaxHeight(500);

        iv25.setAdjustViewBounds(true);
        iv25.setMaxWidth(300);
        iv25.setMaxHeight(500);

        iv26.setAdjustViewBounds(true);
        iv26.setMaxWidth(300);
        iv26.setMaxHeight(500);

        iv28.setAdjustViewBounds(true);
        iv28.setMaxWidth(300);
        iv28.setMaxHeight(500);

        iv29.setAdjustViewBounds(true);
        iv29.setMaxWidth(300);
        iv29.setMaxHeight(500);

        iv30.setAdjustViewBounds(true);
        iv30.setMaxWidth(300);
        iv30.setMaxHeight(500);

        iv32.setAdjustViewBounds(true);
        iv32.setMaxWidth(300);
        iv32.setMaxHeight(500);

        iv33.setAdjustViewBounds(true);
        iv33.setMaxWidth(300);
        iv33.setMaxHeight(500);

        iv34.setAdjustViewBounds(true);
        iv34.setMaxWidth(300);
        iv34.setMaxHeight(500);





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
                                URL url = new URL("http://52.79.129.64" + result.get(0).poster_image);
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
                                URL url = new URL("http://52.79.129.64" + result.get(1).poster_image);
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

                    Thread uThread3 = new Thread() {
                        @Override
                        public void run(){
                            try{
                                //서버에 올려둔 이미지 URL
                                URL url = new URL("http://52.79.129.64" + result.get(2).poster_image);
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
                    uThread3.start(); // 작업 Thread 실행
                    try{
                        uThread3.join();
                        iv3.setImageBitmap(bitmap);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    Thread uThread4 = new Thread() {
                        @Override
                        public void run(){
                            try{
                                //서버에 올려둔 이미지 URL
                                URL url = new URL("http://52.79.129.64" + result.get(3).poster_image);
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
                    uThread4.start(); // 작업 Thread 실행
                    try{
                        uThread4.join();
                        iv4.setImageBitmap(bitmap);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }

                    Thread uThread5 = new Thread() {
                        @Override
                        public void run(){
                            try{
                                //서버에 올려둔 이미지 URL
                                URL url = new URL("http://52.79.129.64" + result.get(4).poster_image);
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
                    uThread5.start(); // 작업 Thread 실행
                    try{
                        uThread5.join();
                        iv5.setImageBitmap(bitmap);
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
                                URL url = new URL("http://52.79.129.64" + result.get(0).poster_image);
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
                        iv6.setImageBitmap(bitmap);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }

                    Thread uThread2 = new Thread() {
                        @Override
                        public void run(){
                            try{
                                //서버에 올려둔 이미지 URL
                                URL url = new URL("http://52.79.129.64" + result.get(1).poster_image);
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
                        iv7.setImageBitmap(bitmap);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }

                    Thread uThread3 = new Thread() {
                        @Override
                        public void run(){
                            try{
                                //서버에 올려둔 이미지 URL
                                URL url = new URL("http://52.79.129.64" + result.get(2).poster_image);
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
                    uThread3.start(); // 작업 Thread 실행
                    try{
                        uThread3.join();
                        iv8.setImageBitmap(bitmap);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    Thread uThread4 = new Thread() {
                        @Override
                        public void run(){
                            try{
                                //서버에 올려둔 이미지 URL
                                URL url = new URL("http://52.79.129.64" + result.get(3).poster_image);
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
                    uThread4.start(); // 작업 Thread 실행
                    try{
                        uThread4.join();
                        iv9.setImageBitmap(bitmap);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }

                    Thread uThread5 = new Thread() {
                        @Override
                        public void run(){
                            try{
                                //서버에 올려둔 이미지 URL
                                URL url = new URL("http://52.79.129.64" + result.get(4).poster_image);
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
                    uThread5.start(); // 작업 Thread 실행
                    try{
                        uThread5.join();
                        iv10.setImageBitmap(bitmap);
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
                                URL url = new URL("http://52.79.129.64" + result.get(0).poster_image);
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
                        iv11.setImageBitmap(bitmap);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }

                    Thread uThread2 = new Thread() {
                        @Override
                        public void run(){
                            try{
                                //서버에 올려둔 이미지 URL
                                URL url = new URL("http://52.79.129.64" + result.get(1).poster_image);
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
                        iv12.setImageBitmap(bitmap);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }

                    Thread uThread3 = new Thread() {
                        @Override
                        public void run(){
                            try{
                                //서버에 올려둔 이미지 URL
                                URL url = new URL("http://52.79.129.64" + result.get(2).poster_image);
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
                    uThread3.start(); // 작업 Thread 실행
                    try{
                        uThread3.join();
                        iv13.setImageBitmap(bitmap);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    Thread uThread4 = new Thread() {
                        @Override
                        public void run(){
                            try{
                                //서버에 올려둔 이미지 URL
                                URL url = new URL("http://52.79.129.64" + result.get(3).poster_image);
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
                    uThread4.start(); // 작업 Thread 실행
                    try{
                        uThread4.join();
                        iv14.setImageBitmap(bitmap);
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
                                URL url = new URL("http://52.79.129.64" + result.get(0).poster_image);
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
                        iv16.setImageBitmap(bitmap);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }

                    Thread uThread2 = new Thread() {
                        @Override
                        public void run(){
                            try{
                                //서버에 올려둔 이미지 URL
                                URL url = new URL("http://52.79.129.64" + result.get(1).poster_image);
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
                        iv17.setImageBitmap(bitmap);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }

                    Thread uThread3 = new Thread() {
                        @Override
                        public void run(){
                            try{
                                //서버에 올려둔 이미지 URL
                                URL url = new URL("http://52.79.129.64" + result.get(2).poster_image);
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
                    uThread3.start(); // 작업 Thread 실행
                    try{
                        uThread3.join();
                        iv18.setImageBitmap(bitmap);
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

        Call<List<PostResultMovie>> call5 = service.getByGenre("판타지");

        call5.enqueue(new Callback<List<PostResultMovie>>() {
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
                                URL url = new URL("http://52.79.129.64" + result.get(0).poster_image);
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
                        iv20.setImageBitmap(bitmap);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }

                    Thread uThread2 = new Thread() {
                        @Override
                        public void run(){
                            try{
                                //서버에 올려둔 이미지 URL
                                URL url = new URL("http://52.79.129.64" + result.get(1).poster_image);
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
                        iv21.setImageBitmap(bitmap);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }

                    Thread uThread3 = new Thread() {
                        @Override
                        public void run(){
                            try{
                                //서버에 올려둔 이미지 URL
                                URL url = new URL("http://52.79.129.64" + result.get(2).poster_image);
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
                    uThread3.start(); // 작업 Thread 실행
                    try{
                        uThread3.join();
                        iv22.setImageBitmap(bitmap);
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

        Call<List<PostResultMovie>> call6 = service.getByGenre("드라마");

        call6.enqueue(new Callback<List<PostResultMovie>>() {
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
                                URL url = new URL("http://52.79.129.64" + result.get(0).poster_image);
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
                        iv24.setImageBitmap(bitmap);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }

                    Thread uThread2 = new Thread() {
                        @Override
                        public void run(){
                            try{
                                //서버에 올려둔 이미지 URL
                                URL url = new URL("http://52.79.129.64" + result.get(1).poster_image);
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
                        iv25.setImageBitmap(bitmap);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }

                    Thread uThread3 = new Thread() {
                        @Override
                        public void run(){
                            try{
                                //서버에 올려둔 이미지 URL
                                URL url = new URL("http://52.79.129.64" + result.get(2).poster_image);
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
                    uThread3.start(); // 작업 Thread 실행
                    try{
                        uThread3.join();
                        iv26.setImageBitmap(bitmap);
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

        Call<List<PostResultMovie>> call7 = service.getByGenre("공상과학");

        call7.enqueue(new Callback<List<PostResultMovie>>() {
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
                                URL url = new URL("http://52.79.129.64" + result.get(0).poster_image);
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
                        iv28.setImageBitmap(bitmap);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }

                    Thread uThread2 = new Thread() {
                        @Override
                        public void run(){
                            try{
                                //서버에 올려둔 이미지 URL
                                URL url = new URL("http://52.79.129.64" + result.get(1).poster_image);
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
                        iv29.setImageBitmap(bitmap);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }

                    Thread uThread3 = new Thread() {
                        @Override
                        public void run(){
                            try{
                                //서버에 올려둔 이미지 URL
                                URL url = new URL("http://52.79.129.64" + result.get(2).poster_image);
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
                    uThread3.start(); // 작업 Thread 실행
                    try{
                        uThread3.join();
                        iv30.setImageBitmap(bitmap);
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

        Call<List<PostResultMovie>> call8 = service.getByGenre("전쟁");

        call8.enqueue(new Callback<List<PostResultMovie>>() {
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
                                URL url = new URL("http://52.79.129.64" + result.get(0).poster_image);
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
                        iv32.setImageBitmap(bitmap);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }

                    Thread uThread2 = new Thread() {
                        @Override
                        public void run(){
                            try{
                                //서버에 올려둔 이미지 URL
                                URL url = new URL("http://52.79.129.64" + result.get(1).poster_image);
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
                        iv33.setImageBitmap(bitmap);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }

                    Thread uThread3 = new Thread() {
                        @Override
                        public void run(){
                            try{
                                //서버에 올려둔 이미지 URL
                                URL url = new URL("http://52.79.129.64" + result.get(2).poster_image);
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
                    uThread3.start(); // 작업 Thread 실행
                    try{
                        uThread3.join();
                        iv34.setImageBitmap(bitmap);
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




        // 영화 정보 보기
        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                bundle.putString("genre", "코미디");//번들에 넘길 값 저장
                bundle.putString("title", "시스터 액트");//번들에 넘길 값 저장

                String url = "https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=시스터 액트";
                Document doc = null;
                try {
                    doc = Jsoup.connect(url).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Elements element = doc.select("div.info_group");
                Element el = element.get(2);
                bundle.putString("star", "(" + el.text() + "★)");//번들에 넘길 값 저장

                bundle.putString("poster_image", "/image/시스터액트.jpg");//번들에 넘길 값 저장
                bundle.putString("synopsis", "리노의 카지노에서 삼류 가수로 일하는 들로리스는 우연히 암흑가의 거물인 빈스의 범죄 현장을 목격한다. 잡히기만 하면 당장 목이 비틀릴 이 꾀꼬리는 그 순간부터 쫓기는 신세가 된다. 경찰에 신고한 들로리스는 증인이 될 것을 약속하고 보호를 받는데, 경찰에서는 그 누구도 상상할 수 없는 곳, 외부와 단절된 수녀원에 들로리스를 숨긴다. 하지만 들로리스는 이렇게 답답한 곳에서 지내느니 차라리 밖에서 쫓기다 총에 맞는게 훨씬 속편한 심정이다. 결국 엄격하기 그지없는 원장 수녀의 감시 아래서 들로리스는 은신이라기 보다는 감화소에 들어온 심정으로 매일 매일을 말썽으로 채우고 있는데, 어느날 그녀의 손에 성가대의 지휘봉이 넘겨졌고, 결국 성가대와 수녀원 전체, 아니 카톨릭 전체가 뒤바뀌는 운명에 이른다!");//번들에 넘길 값 저장

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                MovieInformationActivity fragment2 = new MovieInformationActivity();//프래그먼트2 선언
                fragment2.setArguments(bundle);//번들을 프래그먼트2로 보낼 준비
                transaction.replace(R.id.container, fragment2);
                getFragmentManager().beginTransaction().replace(R.id.container, new MovieInformationActivity()).commit();
                transaction.commit();



                }

        });

        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                bundle.putString("genre", "코미디");//번들에 넘길 값 저장
                bundle.putString("title", "세 얼간이");//번들에 넘길 값 저장

                String url = "https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=세 얼간이";
                Document doc = null;
                try {
                    doc = Jsoup.connect(url).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Elements element = doc.select("div.info_group");
                Element el = element.get(2);
                bundle.putString("star", "(" + el.text() + "★)");//번들에 넘길 값 저장

                bundle.putString("poster_image", "/image/세얼간이.png");//번들에 넘길 값 저장
                bundle.putString("synopsis", "천재들만 간다는 일류 명문대 ICE, 성적과 취업만을 강요하는 학교를 발칵 뒤집어 놓은 대단한 녀석 란초! 아버지가 정해준 꿈, `공학자`가 되기 위해 정작 본인이 좋아하는 일은 포기하고 공부만하는 파파보이 파르한! 찢어지게 가난한 집, 병든 아버지와 식구들을 책임지기 위해 무조건 대기업에 취직해야만 하는 라주! 친구의 이름으로 뭉친 `세 얼간이`! 삐딱한 천재들의 진정한 꿈을 찾기 위한 세상 뒤집기 한판이 시작된다!");//번들에 넘길 값 저장

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                MovieInformationActivity fragment2 = new MovieInformationActivity();//프래그먼트2 선언
                fragment2.setArguments(bundle);//번들을 프래그먼트2로 보낼 준비
                transaction.replace(R.id.container, fragment2);
                getFragmentManager().beginTransaction().replace(R.id.container, new MovieInformationActivity()).commit();
                transaction.commit();

            }

        });

        iv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                bundle.putString("genre", "코미디");//번들에 넘길 값 저장
                bundle.putString("title", "트루먼 쇼");//번들에 넘길 값 저장

                String url = "https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=트루먼쇼";
                Document doc = null;
                try {
                    doc = Jsoup.connect(url).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Elements element = doc.select("div.info_group");
                Element el = element.get(2);
                bundle.putString("star", "(" + el.text() + "★)");//번들에 넘길 값 저장

                bundle.putString("poster_image", "/image/트루먼쇼.jpg");//번들에 넘길 값 저장
                bundle.putString("synopsis", "220개국 17억 인구가 5천 대 카메라로 지켜본 지 10909일째!\n" +
                        " 작은 섬에서 평범한 삶을 사는 30세 보험회사원 트루먼 버뱅크\n" +
                        "  아내와 홀어머니를 모시고 행복한 하루 하루를 보내던 어느 날, 하늘에서 조명이 떨어진다!\n" +
                        "  의아해하던 트루먼은 길을 걷다 죽은 아버지를 만나고 라디오 주파수를 맞추다\n" +
                        "  자신의 일거수일투족이 라디오에 생중계되는 기이한 일들을 연이어 겪게 된다.\n" +
                        "  지난 30년간 일상이라고 믿었던 모든 것들이 어딘가 수상하다고 느낀 트루먼은\n" +
                        "  모든 것이 ‘쇼’라는 말을 남기고 떠난 첫사랑 ‘실비아’를 찾아 피지 섬으로 떠나기로 결심한다.\n" +
                        "  \n" +
                        "  가족, 친구, 회사… 하나부터 열까지 모든 것이 가짜인 ‘트루먼 쇼’\n" +
                        "  과연 트루먼은 진짜 인생을 찾을 수 있을까?");//번들에 넘길 값 저장

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                MovieInformationActivity fragment2 = new MovieInformationActivity();//프래그먼트2 선언
                fragment2.setArguments(bundle);//번들을 프래그먼트2로 보낼 준비
                transaction.replace(R.id.container, fragment2);
                getFragmentManager().beginTransaction().replace(R.id.container, new MovieInformationActivity()).commit();
                transaction.commit();

            }

        });

        iv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                bundle.putString("genre", "코미디");//번들에 넘길 값 저장
                bundle.putString("title", "모던 타임즈");//번들에 넘길 값 저장

                String url = "https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=모던타임즈";
                Document doc = null;
                try {
                    doc = Jsoup.connect(url).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Elements element = doc.select("div.info_group");
                Element el = element.get(3);
                bundle.putString("star", "(" + el.text() + "★)");//번들에 넘길 값 저장

                bundle.putString("poster_image", "/image/모던타임즈.jpg");//번들에 넘길 값 저장
                bundle.putString("synopsis", "공장에서 하루 종일 나사못 조이는 일을 하는 찰리.\n" +
                        "  눈에 보이는 모든 것을 조여버리는 강박 관념에 빠지고만 그는 급기야 정신 병원에 가게 되고, 거리를 방황하다 시위 군중에 휩쓸려 감옥살이까지 하게 된다.\n" +
                        "  \n" +
                        "  몇 년 후 감옥에서 풀려난 찰리는 부모를 잃고 고아가 된 한 아름다운 소녀를 도와주게 되고,\n" +
                        "  그녀의 도움으로 카페에서 일하게 되지만 우여곡절 끝에 다시 거리로 내몰리고 만다.\n" +
                        "  \n" +
                        "  절망 속에서 피어나는 함박웃음! 찰리와 소녀의 행복을 찾아가는 아름다운 여정!\n" +
                        " ");//번들에 넘길 값 저장

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                MovieInformationActivity fragment2 = new MovieInformationActivity();//프래그먼트2 선언
                fragment2.setArguments(bundle);//번들을 프래그먼트2로 보낼 준비
                transaction.replace(R.id.container, fragment2);
                getFragmentManager().beginTransaction().replace(R.id.container, new MovieInformationActivity()).commit();
                transaction.commit();

            }

        });

        iv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                bundle.putString("genre", "코미디");//번들에 넘길 값 저장
                bundle.putString("title", "라따뚜이");//번들에 넘길 값 저장

                String url = "https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=라따뚜이";
                Document doc = null;
                try {
                    doc = Jsoup.connect(url).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Elements element = doc.select("div.info_group");
                Element el = element.get(2);
                bundle.putString("star", "(" + el.text() + "★)");//번들에 넘길 값 저장

                bundle.putString("poster_image", "/image/라따뚜이.jpg");//번들에 넘길 값 저장
                bundle.putString("synopsis", "파리에서 날아온 '니모를 찾아서' & '인크레더블' 제작진의 달콤한 상상\n" +
                        " 절대미각, 빠른 손놀림, 끓어 넘치는 열정의 소유자 ‘레미’. 프랑스 최고의 요리사를 꿈꾸는 그에게 단 한가지 약점이 있었으니, 바로 주방 퇴치대상 1호인 ‘생쥐’라는 것! 그러던 어느 날, 하수구에서 길을 잃은 레미는 운명처럼 파리의 별 다섯개짜리 최고급 레스토랑에 떨어진다. 그러나 생쥐의 신분으로 주방이란 그저 그림의 떡. 보글거리는 수프, 둑닥둑닥 도마소리, 향긋한 허브 내음에 식욕이 아닌 ‘요리욕’이 북받친 레미의 작은 심장은 콩닥콩닥 뛰기 시작하는데!\n" +
                        "   쥐면 쥐답게 쓰레기나 먹고 살라는 가족들의 핀잔에도 굴하지 않고 끝내 주방으로 들어가는 레미. 깜깜한 어둠 속에서 요리에 열중하다 재능 없는 견습생 ‘링귀니’에게 ‘딱’ 걸리고 만다. 하지만 해고위기에 처해있던 링귀니는 레미의 재능을 한눈에 알아보고 의기투합을 제안하는데. 과연 궁지에 몰린 둘은 환상적인 요리 실력을 발휘할 수 있을 것인가? 레니와 링귀니의 좌충우돌 공생공사 프로젝트가 아름다운 파리를 배경으로 이제 곧 펼쳐진다!\n" +
                        " ");//번들에 넘길 값 저장

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                MovieInformationActivity fragment2 = new MovieInformationActivity();//프래그먼트2 선언
                fragment2.setArguments(bundle);//번들을 프래그먼트2로 보낼 준비
                transaction.replace(R.id.container, fragment2);
                getFragmentManager().beginTransaction().replace(R.id.container, new MovieInformationActivity()).commit();
                transaction.commit();

            }

        });


        iv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                bundle.putString("genre", "로맨스");//번들에 넘길 값 저장
                bundle.putString("title", "어바웃타임");//번들에 넘길 값 저장

                String url = "https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=어바웃타임";
                Document doc = null;
                try {
                    doc = Jsoup.connect(url).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Elements element = doc.select("div.info_group");
                Element el = element.get(2);
                bundle.putString("star", "(" + el.text() + "★)");//번들에 넘길 값 저장

                bundle.putString("poster_image", "/image/어바웃타임.jpg");//번들에 넘길 값 저장
                bundle.putString("synopsis", "모태솔로 팀(돔놀 글리슨)은 성인이 된 날, 아버지(빌 나이)로부터 놀랄만한 가문의 비밀을 듣게 된다. 바로 시간을 되돌릴 수 있는 능력이 있다는 것! 그것이 비록 히틀러를 죽이거나 여신과 뜨거운 사랑을 할 수는 없지만, 여자친구는 만들어 줄 순 있으리.. 꿈을 위해 런던으로 간 팀은 우연히 만난 사랑스러운 여인 메리에게 첫눈에 반하게 된다. 그녀의 사랑을 얻기 위해 자신의 특별한 능력을 마음껏 발휘하는 팀. 어설픈 대시, 어색한 웃음은 리와인드! 뜨거웠던 밤은 더욱 뜨겁게 리플레이! 꿈에 그리던 그녀와 매일매일 최고의 순간을 보낸다. 하지만 그와 그녀의 사랑이 완벽해질수록 팀을 둘러싼 주변 상황들은 미묘하게 엇갈리고, 예상치 못한 사건들이 여기저기 나타나기 시작하는데… 어떠한 순간을 다시 살게 된다면, 과연 완벽한 사랑을 이룰 수 있을까?");//번들에 넘길 값 저장

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                MovieInformationActivity fragment2 = new MovieInformationActivity();//프래그먼트2 선언
                fragment2.setArguments(bundle);//번들을 프래그먼트2로 보낼 준비
                transaction.replace(R.id.container, fragment2);
                getFragmentManager().beginTransaction().replace(R.id.container, new MovieInformationActivity()).commit();
                transaction.commit();

            }

        });

        iv7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                bundle.putString("genre", "로맨스");//번들에 넘길 값 저장
                bundle.putString("title", "라라랜드");//번들에 넘길 값 저장

                String url = "https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=라라랜드";
                Document doc = null;
                try {
                    doc = Jsoup.connect(url).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Elements element = doc.select("div.info_group");
                Element el = element.get(2);
                bundle.putString("star", "(" + el.text() + "★)");//번들에 넘길 값 저장

                bundle.putString("poster_image", "/image/라라랜드.png");//번들에 넘길 값 저장
                bundle.putString("synopsis", "꿈을 꾸는 사람들을 위한 별들의 도시 ‘라라랜드’. 재즈 피아니스트 ‘세바스찬’(라이언 고슬링)과 배우 지망생 ‘미아’(엠마 스톤), 인생에서 가장 빛나는 순간 만난 두 사람은 미완성인 서로의 무대를 만들어가기 시작한다.");//번들에 넘길 값 저장

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                MovieInformationActivity fragment2 = new MovieInformationActivity();//프래그먼트2 선언
                fragment2.setArguments(bundle);//번들을 프래그먼트2로 보낼 준비
                transaction.replace(R.id.container, fragment2);
                getFragmentManager().beginTransaction().replace(R.id.container, new MovieInformationActivity()).commit();
                transaction.commit();

            }

        });

        iv8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                bundle.putString("genre", "로맨스");//번들에 넘길 값 저장
                bundle.putString("title", "이터널 선샤인");//번들에 넘길 값 저장

                String url = "https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=이터널선샤인";
                Document doc = null;
                try {
                    doc = Jsoup.connect(url).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Elements element = doc.select("div.info_group");
                Element el = element.get(2);
                bundle.putString("star", "(" + el.text() + "★)");//번들에 넘길 값 저장

                bundle.putString("poster_image", "/image/이터널선샤인.jpg");//번들에 넘길 값 저장
                bundle.putString("synopsis", "사랑은 그렇게 다시 기억된다..\n" +
                        " 조엘은 아픈 기억만을 지워준다는 라쿠나사를 찾아가 헤어진 연인 클레멘타인의 기억을 지우기로 결심한다. 기억이 사라져 갈수록 조엘은 사랑이 시작되던 순간, 행복한 기억들, 가슴 속에 각인된 추억들을 지우기 싫어지기만 하는데... 당신을 지우면 이 아픔도 사라질까요? 사랑은 그렇게 다시 기억된다.");//번들에 넘길 값 저장

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                MovieInformationActivity fragment2 = new MovieInformationActivity();//프래그먼트2 선언
                fragment2.setArguments(bundle);//번들을 프래그먼트2로 보낼 준비
                transaction.replace(R.id.container, fragment2);
                getFragmentManager().beginTransaction().replace(R.id.container, new MovieInformationActivity()).commit();
                transaction.commit();

            }

        });

        iv9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                bundle.putString("genre", "로맨스");//번들에 넘길 값 저장
                bundle.putString("title", "러브 액츄얼리");//번들에 넘길 값 저장

                String url = "https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=영화러브액츄얼리";
                Document doc = null;
                try {
                    doc = Jsoup.connect(url).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Elements element = doc.select("div.info_group");
                Element el = element.get(3);
                bundle.putString("star", "(" + el.text() + "★)");//번들에 넘길 값 저장

                bundle.putString("poster_image", "/image/러브액츄얼리.jpg");//번들에 넘길 값 저장
                bundle.putString("synopsis", "크리스마스에 모두에게 전하고 싶은 로맨틱한 고백\n" +
                        " 사랑에 상처받은 당신을 위해,\n" +
                        "  사랑하지만 말하지 못했던 당신을 위해,\n" +
                        "  사랑에 확신하지 못했던 당신을 위해,\n" +
                        "  모두의 마음을 따뜻하게 할 선물이 찾아옵니다.");//번들에 넘길 값 저장

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                MovieInformationActivity fragment2 = new MovieInformationActivity();//프래그먼트2 선언
                fragment2.setArguments(bundle);//번들을 프래그먼트2로 보낼 준비
                transaction.replace(R.id.container, fragment2);
                getFragmentManager().beginTransaction().replace(R.id.container, new MovieInformationActivity()).commit();
                transaction.commit();

            }

        });

        iv10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                bundle.putString("genre", "로맨스");//번들에 넘길 값 저장
                bundle.putString("title", "타이타닉");//번들에 넘길 값 저장

                String url = "https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=타이타닉";
                Document doc = null;
                try {
                    doc = Jsoup.connect(url).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Elements element = doc.select("div.info_group");
                Element el = element.get(2);
                bundle.putString("star", "(" + el.text() + "★)");//번들에 넘길 값 저장

                bundle.putString("poster_image", "/image/타이타닉.jpg");//번들에 넘길 값 저장
                bundle.putString("synopsis", "“내 인생의 가장 큰 행운은 도박에서 딴 티켓으로 당신을 만난 거야”\n" +
                        " 단 하나의 운명, 단 한 번의 사랑,\n" +
                        " 영원으로 기억될 세기의 러브 스토리\n" +
                        " 우연한 기회로 티켓을 구해 타이타닉호에 올라탄 자유로운 영혼을 가진 화가 잭(레오나르도 디카프리오)은\n" +
                        "  막강한 재력의 약혼자와 함께 1등실에 승선한 로즈(케이트 윈슬렛)에게 한 눈에 반한다.\n" +
                        "  진실한 사랑을 꿈꾸던 로즈 또한 생애 처음 황홀한 감정에 휩싸이고, 둘은 운명 같은 사랑에 빠지는데…");//번들에 넘길 값 저장

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                MovieInformationActivity fragment2 = new MovieInformationActivity();//프래그먼트2 선언
                fragment2.setArguments(bundle);//번들을 프래그먼트2로 보낼 준비
                transaction.replace(R.id.container, fragment2);
                getFragmentManager().beginTransaction().replace(R.id.container, new MovieInformationActivity()).commit();
                transaction.commit();

            }

        });

        iv11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                bundle.putString("genre", "액션");//번들에 넘길 값 저장
                bundle.putString("title", "분노의 질주 더 얼티메이트");//번들에 넘길 값 저장

                String url = "https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=분노의 질주";
                Document doc = null;
                try {
                    doc = Jsoup.connect(url).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Elements element = doc.select("div.info_group");
                Element el = element.get(2);
                bundle.putString("star", "(" + el.text() + "★)");//번들에 넘길 값 저장

                bundle.putString("poster_image", "/image/분노의질주더얼티메이트.jpg");//번들에 넘길 값 저장
                bundle.putString("synopsis", "도미닉(빈 디젤)은 자신과 가장 가까웠던 형제 제이콥(존 시나)이 사이퍼(샤를리즈 테론)와 연합해 전 세계를 위기로 빠트릴 위험천만한 계획을 세운다는 사실을 알게 되고, 이를 막기 위해 다시 한 번 패밀리들을 소환한다. 가장 가까운 자가 한순간, 가장 위험한 적이 된 상황 도미닉과 패밀리들은 이에 반격할 놀라운 컴백과 작전을 세우고 지상도, 상공도, 국경도 경계가 없는 불가능한 대결이 시작되는데…");//번들에 넘길 값 저장

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                MovieInformationActivity fragment2 = new MovieInformationActivity();//프래그먼트2 선언
                fragment2.setArguments(bundle);//번들을 프래그먼트2로 보낼 준비
                transaction.replace(R.id.container, fragment2);
                getFragmentManager().beginTransaction().replace(R.id.container, new MovieInformationActivity()).commit();
                transaction.commit();

            }

        });

        iv12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                bundle.putString("genre", "액션");//번들에 넘길 값 저장
                bundle.putString("title", "블랙위도우");//번들에 넘길 값 저장

                bundle.putString("star", "(평점 9.07★)");//번들에 넘길 값 저장

                bundle.putString("poster_image", "/image/블랙위도우.jpg");//번들에 넘길 값 저장
                bundle.putString("synopsis", "어벤져스의 히어로 블랙 위도우, ‘나타샤 로마노프’ (스칼렛 요한슨)는 자신의 과거와 연결된 레드룸의 거대한 음모와 실체를 깨닫게 된다. 상대의 능력을 복제하는 빌런 ‘태스크마스터’와 새로운 위도우들의 위협에 맞서 목숨을 건 반격을 시작하는 ‘나타샤’는 스파이로 활약했던 자신의 과거 뿐 아니라, 어벤져스가 되기 전 함께했던 동료들을 마주해야만 하는데… 폭발하는 리얼 액션 카타르시스! MCU의 새로운 시대를 시작할 첫 액션 블록버스터를 만끽하라!");//번들에 넘길 값 저장

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                MovieInformationActivity fragment2 = new MovieInformationActivity();//프래그먼트2 선언
                fragment2.setArguments(bundle);//번들을 프래그먼트2로 보낼 준비
                transaction.replace(R.id.container, fragment2);
                getFragmentManager().beginTransaction().replace(R.id.container, new MovieInformationActivity()).commit();
                transaction.commit();

            }

        });

        iv13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                bundle.putString("genre", "액션");//번들에 넘길 값 저장
                bundle.putString("title", "다크 나이트");//번들에 넘길 값 저장

                String url = "https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=다크나이트";
                Document doc = null;
                try {
                    doc = Jsoup.connect(url).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Elements element = doc.select("div.info_group");
                Element el = element.get(2);
                bundle.putString("star", "(" + el.text() + "★)");//번들에 넘길 값 저장

                bundle.putString("poster_image", "/image/다크나이트.jpg");//번들에 넘길 값 저장
                bundle.putString("synopsis", "어둠의 기사 배트맨 VS 절대 악 조커\n" +
                        " 운명을 건 최후의 결전이 시작된다!\n" +
                        " 정의로운 지방 검사 ‘하비 덴트’, ‘짐 고든’ 반장과 함께 범죄 소탕 작전을 펼치며\n" +
                        "  범죄와 부패로 들끓는 고담시를 지켜나가는 ‘배트맨’\n" +
                        "  \n" +
                        "  그러던 어느 날, 살아남기 위해 발버둥치던 범죄 조직은 배트맨을 제거하기 위해\n" +
                        "  광기어린 악당 ‘조커’를 끌어들이고\n" +
                        "  정체를 알 수 없는 조커의 등장에 고담시 전체가 깊은 혼돈 속으로 빠져든다.\n" +
                        "  \n" +
                        "  급기야 배트맨을 향한 강한 집착을 드러낸 조커는\n" +
                        "  그가 시민들 앞에 정체를 밝힐 때까지 매일 새로운 사람들을 죽이겠다 선포하고\n" +
                        "  배트맨은 사상 최악의 악당 조커를 막기 위해 자신의 모든 것을 내던진 마지막 대결을 준비한다.");//번들에 넘길 값 저장

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                MovieInformationActivity fragment2 = new MovieInformationActivity();//프래그먼트2 선언
                fragment2.setArguments(bundle);//번들을 프래그먼트2로 보낼 준비
                transaction.replace(R.id.container, fragment2);
                getFragmentManager().beginTransaction().replace(R.id.container, new MovieInformationActivity()).commit();
                transaction.commit();

            }

        });

        iv14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                bundle.putString("genre", "액션");//번들에 넘길 값 저장
                bundle.putString("title", "헌터 킬러");//번들에 넘길 값 저장

                String url = "https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=헌터킬러";
                Document doc = null;
                try {
                    doc = Jsoup.connect(url).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Elements element = doc.select("div.info_group");
                Element el = element.get(2);
                bundle.putString("star", "(" + el.text() + "★)");//번들에 넘길 값 저장

                bundle.putString("poster_image", "/image/헌터킬러.jpg");//번들에 넘길 값 저장
                bundle.putString("synopsis", "소리 없이 침투하여 오차 없이 공격하라!\n" +
                        " 세계 최강의 공격 잠수함 ‘헌터 킬러’가 움직인다!\n" +
                        " 미 국방부는 격침당한 잠수함의 행방을 찾기 위해\n" +
                        "  ‘헌터 킬러’를 극비리에 투입시키고\n" +
                        "  캡틴 ‘글래스’(제라드 버틀러)는 배후에 숨겨진 음모가 있음을 알게 된다.\n" +
                        "  한편, 지상에서는 VIP가 납치되어 전세계는 초긴장 상태에 놓이게 되는데…\n" +
                        "  \n" +
                        "  일촉즉발 위기상황, VIP를 구출하라!\n" +
                        "  단 한 척의 공격 잠수함 ‘헌터 킬러’와\n" +
                        "  최정예 특수부대 네이비 씰의 숨막히는 육해공 합동 작전이 펼쳐진다!");//번들에 넘길 값 저장

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                MovieInformationActivity fragment2 = new MovieInformationActivity();//프래그먼트2 선언
                fragment2.setArguments(bundle);//번들을 프래그먼트2로 보낼 준비
                transaction.replace(R.id.container, fragment2);
                getFragmentManager().beginTransaction().replace(R.id.container, new MovieInformationActivity()).commit();
                transaction.commit();

            }

        });


        iv16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                bundle.putString("genre", "공포");//번들에 넘길 값 저장
                bundle.putString("title", "애나벨 집으로");//번들에 넘길 값 저장

                String url = "https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=애나벨집으로";
                Document doc = null;
                try {
                    doc = Jsoup.connect(url).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Elements element = doc.select("div.info_group");
                Element el = element.get(2);
                bundle.putString("star", "(" + el.text() + "★)");//번들에 넘길 값 저장

                bundle.putString("poster_image", "/image/애나벨집으로.png");//번들에 넘길 값 저장
                bundle.putString("synopsis", "퇴마사 워렌 부부는 저주 받은 인형 애나벨을 발견하고 집에 있는 오컬트 뮤지엄 진열장에 격리시킨다. 또 다른 초자연적인 현상을 연구하기 위해 워렌 부부가 떠난 사이, 집에 남아있던 10살 딸 주디와 베이비시터는 ‘절대 들어가지 말라’는 경고를 어기고 마는데… 탈출한 애나벨은 모든 악령들을 깨우고, 잊을 수 없는 공포의 밤을 준비한다.");//번들에 넘길 값 저장

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                MovieInformationActivity fragment2 = new MovieInformationActivity();//프래그먼트2 선언
                fragment2.setArguments(bundle);//번들을 프래그먼트2로 보낼 준비
                transaction.replace(R.id.container, fragment2);
                getFragmentManager().beginTransaction().replace(R.id.container, new MovieInformationActivity()).commit();
                transaction.commit();

            }

        });

        iv17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                bundle.putString("genre", "공포");//번들에 넘길 값 저장
                bundle.putString("title", "유전");//번들에 넘길 값 저장

                String url = "https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=유전";
                Document doc = null;
                try {
                    doc = Jsoup.connect(url).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Elements element = doc.select("div.info_group");
                Element el = element.get(2);
                bundle.putString("star", "(" + el.text() + "★)");//번들에 넘길 값 저장

                bundle.putString("poster_image", "/image/유전.png");//번들에 넘길 값 저장
                bundle.putString("synopsis", "‘애니’는 일주일 전 돌아가신 엄마의 유령이 집에 나타나는 것을 느낀다. 애니가 엄마와 닮았다며 접근한 수상한 이웃 ‘조안’을 통해 엄마의 비밀을 발견하고, 자신이 엄마와 똑같은 일을 저질렀음을 알게 된다. 그리고 마침내 애니의 엄마로부터 시작돼 아들 ‘피터’와 딸 ‘찰리’에게까지 이어진 저주의 실체가 정체를 드러내는데…");//번들에 넘길 값 저장

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                MovieInformationActivity fragment2 = new MovieInformationActivity();//프래그먼트2 선언
                fragment2.setArguments(bundle);//번들을 프래그먼트2로 보낼 준비
                transaction.replace(R.id.container, fragment2);
                getFragmentManager().beginTransaction().replace(R.id.container, new MovieInformationActivity()).commit();
                transaction.commit();

            }

        });

        iv18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                bundle.putString("genre", "공포");//번들에 넘길 값 저장
                bundle.putString("title", "컨저링");//번들에 넘길 값 저장

                String url = "https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=컨저링1";
                Document doc = null;
                try {
                    doc = Jsoup.connect(url).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Elements element = doc.select("div.info_group");
                Element el = element.get(2);
                bundle.putString("star", "(" + el.text() + "★)");//번들에 넘길 값 저장

                bundle.putString("poster_image", "/image/컨저링.jpg");//번들에 넘길 값 저장
                bundle.putString("synopsis", "믿을 수 없겠지만… 이것은 실화다!\n" +
                        " 1971년 로드 아일랜드, 해리스빌. 페론 가족은 꿈에 그리던 새 집으로 이사를 간다.\n" +
                        "  물론 1863년에 그 집에서 일어난 끔찍한 살인 사건을 전혀 몰랐다.\n" +
                        "  또한 그 이후에 일어난 많은 무서운 사건에 대해서도 알지 못했다.\n" +
                        "  이 가족은 그 집에서 겪은 일이 너무 무서워서 한 마디라도 외부에 언급하는 것을 거절했었다.\n" +
                        "  지금까지는…");//번들에 넘길 값 저장

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                MovieInformationActivity fragment2 = new MovieInformationActivity();//프래그먼트2 선언
                fragment2.setArguments(bundle);//번들을 프래그먼트2로 보낼 준비
                transaction.replace(R.id.container, fragment2);
                getFragmentManager().beginTransaction().replace(R.id.container, new MovieInformationActivity()).commit();
                transaction.commit();

            }

        });


        iv20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                bundle.putString("genre", "판타지");//번들에 넘길 값 저장
                bundle.putString("title", "찰리와 초콜릿 공장");//번들에 넘길 값 저장

                String url = "https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=찰리와초콜릿공장";
                Document doc = null;
                try {
                    doc = Jsoup.connect(url).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Elements element = doc.select("div.info_group");
                Element el = element.get(2);
                bundle.putString("star", "(" + el.text() + "★)");//번들에 넘길 값 저장

                bundle.putString("poster_image", "/image/찰리와초콜릿공장.jpg");//번들에 넘길 값 저장
                bundle.putString("synopsis", "팀 버튼과 조니 뎁이 초대하는 달콤한 환상의 세계!\n" +
                        " 전 세계 누구에게나 사랑 받는 세계 최고의 초콜릿 공장인 '윌리 웡카 초콜릿 공장'. 매일 엄청난 양의 초콜릿을 생산해 세계 각국으로 운반하고 있지만 그 누구도 공장을 드나 드는 사람을 본 적이 없는 비밀의 공간이다. 공장보다 더 신비로운 수수께끼는 초콜릿 공장의 공장장인 윌리 웡카(조니 뎁)라는 인물. 들리는 소문에 의하면 웡카는 몇 년 동안 공장 밖으로 나가본 적도 없다고 한다. 그가 어떤 사람이고, 왜 초콜릿 만드는 일에 모든 것을 걸고 있는지 모두들 궁금해 할 뿐이다. 어느 날, 윌리 웡카가 5개의 웡카 초콜릿에 감춰진 행운의 '황금티켓'을 찾은 어린이 다섯 명에게 자신의 공장을 공개하고 그 모든 제작과정의 비밀을 보여주겠다는 선언을 한다. 이제 전 세계 어린이들은 황금티켓을 찾기 위한 노력을 시작한다.\n" +
                        "   엄마, 아빠, 할머니, 할아버지들과 함께 초콜릿 공장 바로 옆, 다 쓰러져 갈듯한 작은 오두막집에서 살고 있는 찰리(프레디 하이모어) 역시 초콜릿 공장에 가고 싶은 건 마찬가지. 찰리는 매일 밤 잠들기 전 공장 안이 어떻게 생겼을 지를 상상하며 잠이 들곤 했다. 하지만 찰리는 1년에 단 한번, 자신의 생일에 딱 한 개의 웡카 초콜릿을 먹을 수 있기 때문에 당첨될 확률은 거의 희박했다. 한편, 세계 각국에서 행운의 당첨자들이 속속 나타나기 시작했다. 첫 번째 당첨자는 독일의 먹보 소년 아우구스투스. 언제나 초콜릿을 입에 달고 사는 소년이다. 두 번째 행운은 뭐든지 원하는 건 손에 넣어야 직성이 풀리는 부잣집 딸 버루카에게, 세 번째는 껌 씹기 대회 챔피언인 바이올렛에게 돌아간다. 네 번째 주인공인 마이크는 자신이 얼마나 똑똑한지를 세상에 과시하기 위해 도전에 응해 목적을 달성한 집념의 소유자다. 그리고 마지막!! 눈 쌓인 거리에서 우연히 돈을 주워 웡카 초콜릿을 산 찰리가 다섯 번째 황금 티켓을 발견한 주인공이 되었다!!\n" +
                        "   웡카의 초콜릿 공장에 들어간 찰리는 눈앞에 펼쳐지는 놀라운 광경들에 입을 다물지 못한다. 한쪽엔 초콜릿 폭포가 흐르고 그 옆에선 쾌활한 움파 룸파 족들이 거대한 초콜릿 과자 산에 삽질을 하거나, 용머리 모양을 한 설탕 보트를 타고 초콜릿 강을 건너간다. 초콜릿 강가에는 꽈배기 사탕이 열리는 나무와 민트 설탕 풀이 자라고 있고 덤불 속에선 머쉬멜로우 체리크림이 익어간다. 한편, 찰리를 제외한 다른 네 명은 웡카의 놀라운 발명품들에는 관심도 없고 한결같이 욕심과 이기심, 승부욕과 과시욕에 눈이 멀어 자꾸만 문제를 일으키는데...");//번들에 넘길 값 저장

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                MovieInformationActivity fragment2 = new MovieInformationActivity();//프래그먼트2 선언
                fragment2.setArguments(bundle);//번들을 프래그먼트2로 보낼 준비
                transaction.replace(R.id.container, fragment2);
                getFragmentManager().beginTransaction().replace(R.id.container, new MovieInformationActivity()).commit();
                transaction.commit();

            }

        });

        iv21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                bundle.putString("genre", "판타지");//번들에 넘길 값 저장
                bundle.putString("title", "미녀와 야수");//번들에 넘길 값 저장

                String url = "https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=미녀와야수";
                Document doc = null;
                try {
                    doc = Jsoup.connect(url).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Elements element = doc.select("div.info_group");
                Element el = element.get(2);
                bundle.putString("star", "(" + el.text() + "★)");//번들에 넘길 값 저장

                bundle.putString("poster_image", "/image/미녀와야수.jpg");//번들에 넘길 값 저장
                bundle.putString("synopsis", "스크린에 재탄생한 세기의 걸작!\n" +
                        " 전 세계가 기다려온 가장 아름다운 이야기!\n" +
                        " \n" +
                        " 똑똑하고 아름다운 ‘벨(엠마 왓슨)’은 아버지와 살고 있는 작은 마을에서 벗어나 운명적인 사랑과 모험을 꿈꾼다.\n" +
                        "  어느 날 행방불명된 아버지를 찾아 폐허가 된 성에 도착한 벨은 저주에 걸린 ‘야수’(댄 스티븐스)를 만나\n" +
                        "  아버지 대신 성에 갇히고, 야수 뿐 아니라 성 안의 모든 이들이 신비로운 장미의 마지막 꽃잎이 떨어지기 전에\n" +
                        "  저주를 풀지 못하면 영원히 인간으로 돌아올 수 없는 운명임을 알게 된다.\n" +
                        "  \n" +
                        "  성에서 도망치려던 벨은 위험한 상황에서 자신을 보호해 준 야수의 진심을 알게 되면서\n" +
                        "  차츰 마음을 열어가기 시작하는데…");//번들에 넘길 값 저장

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                MovieInformationActivity fragment2 = new MovieInformationActivity();//프래그먼트2 선언
                fragment2.setArguments(bundle);//번들을 프래그먼트2로 보낼 준비
                transaction.replace(R.id.container, fragment2);
                getFragmentManager().beginTransaction().replace(R.id.container, new MovieInformationActivity()).commit();
                transaction.commit();

            }

        });

        iv22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                bundle.putString("genre", "판타지");//번들에 넘길 값 저장
                bundle.putString("title", "신비한 동물사전");//번들에 넘길 값 저장

                String url = "https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=신비한동물사전1";
                Document doc = null;
                try {
                    doc = Jsoup.connect(url).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Elements element = doc.select("div.info_group");
                Element el = element.get(2);
                bundle.putString("star", "(" + el.text() + "★)");//번들에 넘길 값 저장

                bundle.putString("poster_image", "/image/신비한동물사전.jpg");//번들에 넘길 값 저장
                bundle.putString("synopsis", "‘해리 포터’ 마법의 시작!\n" +
                        " 신비한 동물들 탈출, 뉴욕 최대의 위기! 새로운 마법의 시대가 열린다!\n" +
                        " 1926년 뉴욕, ‘검은 존재’가 거리를 쑥대밭으로 만들고 미국의 마법의회 MACUSA의 대통령과 어둠의 마법사를 체포하는 오러의 수장 그레이브스가 이를 추적하는 혼돈 속에 영국의 마법사 뉴트 스캐맨더가 이 곳을 찾는다. 그의 목적은 세계 곳곳에 숨어있는 신비한 동물들을 찾기 위한 것. 여행을 하면서 다양한 크기의 신비한 동물을 구조해 안에 마법의 공간이 있는 가방에 넣어 다니며 보살핀다. 하지만 은행을 지나던 중 금은보화를 좋아하는 신비한 동물인 니플러가 가방 안에서 탈출을 하고 이 일로 전직 오러였던 티나와 노마지 제이콥과 엮이게 된다. 이 사고로 뉴트와 제이콥의 가방이 바뀌면서 신비한 동물들이 대거 탈출을 하고 그들은 동물들을 찾기 위해 뉴욕 곳곳을 누빈다. 한편, ‘검은 존재’의 횡포는 더욱 거세져 결국 인간 사회와 마법 사회를 발칵 뒤집는 사건이 발생하고, 이 모든 것이 뉴트의 소행이라는 오해를 받게 되는데…");//번들에 넘길 값 저장

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                MovieInformationActivity fragment2 = new MovieInformationActivity();//프래그먼트2 선언
                fragment2.setArguments(bundle);//번들을 프래그먼트2로 보낼 준비
                transaction.replace(R.id.container, fragment2);
                getFragmentManager().beginTransaction().replace(R.id.container, new MovieInformationActivity()).commit();
                transaction.commit();

            }

        });


        iv24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                bundle.putString("genre", "드라마");//번들에 넘길 값 저장
                bundle.putString("title", "보헤미안 랩소디");//번들에 넘길 값 저장

                String url = "https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=영화보헤미안랩소디";
                Document doc = null;
                try {
                    doc = Jsoup.connect(url).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Elements element = doc.select("div.info_group");
                Element el = element.get(2);
                bundle.putString("star", "(" + el.text() + "★)");//번들에 넘길 값 저장

                bundle.putString("poster_image", "/image/보헤미안랩소디.jpg");//번들에 넘길 값 저장
                bundle.putString("synopsis", "“나는 스타가 되지 않을 것이다, 전설이 될 것이다”\n" +
                        " 공항에서 수하물 노동자로 일하며 음악의 꿈을 키우던 이민자 출신의 아웃사이더 ‘파록버사라’\n" +
                        "  보컬을 구하던 로컬 밴드에 들어가게 되면서 ‘프레디 머큐리’라는 이름으로 밴드 ‘퀸’을 이끌게 된다.\n" +
                        "  \n" +
                        "  시대를 앞서가는 독창적인 음악과 화려한 퍼포먼스로 관중들을 사로잡으며 성장하던 ‘퀸’은\n" +
                        "  라디오와 방송에서 외면을 받을 것이라는 음반사의 반대에도 불구하고\n" +
                        "  무려 6분 동안 이어지는 실험적인 곡 ‘보헤미안 랩소디’로 대성공을 거두며 월드스타 반열에 오른다.\n" +
                        "  \n" +
                        "  그러나 독보적인 존재감을 뿜어내던 ‘프레디 머큐리’는 솔로 데뷔라는 유혹에 흔들리게 되고\n" +
                        "  결국 오랜 시간 함께 해왔던 멤버들과 결별을 선언하게 되는데…\n" +
                        "  \n" +
                        "  세상에서 소외된 아웃사이더에서 전설의 록밴드 ‘퀸’이 되기까지,\n" +
                        "  우리가 몰랐던 그들의 진짜 이야기가 시작된다!");//번들에 넘길 값 저장

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                MovieInformationActivity fragment2 = new MovieInformationActivity();//프래그먼트2 선언
                fragment2.setArguments(bundle);//번들을 프래그먼트2로 보낼 준비
                transaction.replace(R.id.container, fragment2);
                getFragmentManager().beginTransaction().replace(R.id.container, new MovieInformationActivity()).commit();
                transaction.commit();

            }

        });

        iv25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                bundle.putString("genre", "드라마");//번들에 넘길 값 저장
                bundle.putString("title", "위대한 쇼맨");//번들에 넘길 값 저장

                String url = "https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=위대한쇼맨";
                Document doc = null;
                try {
                    doc = Jsoup.connect(url).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Elements element = doc.select("div.info_group");
                Element el = element.get(2);
                bundle.putString("star", "(" + el.text() + "★)");//번들에 넘길 값 저장

                bundle.putString("poster_image", "/image/위대한쇼맨.jpg");//번들에 넘길 값 저장
                bundle.putString("synopsis", "불가능한 꿈, 그 이상의 쇼!\n" +
                        " 쇼 비즈니스의 창시자이자, 꿈의 무대로 전세계를 매료시킨 남자\n" +
                        "  ‘바넘’의 이야기에서 영감을 받아 탄생한 오리지널 뮤지컬 영화 <위대한 쇼맨>.\n" +
                        "  <레미제라블> 이후 다시 뮤지컬 영화로 돌아온 휴 잭맨부터 잭 에프론, 미셸 윌리엄스, 레베카 퍼거슨, 젠다야까지\n" +
                        "  할리우드 최고의 배우들이 합류해 환상적인 앙상블을 선보인다.\n" +
                        "  여기에 <미녀와 야수> 제작진과 <라라랜드> 작사팀의 합류로\n" +
                        "  더욱 풍성해진 비주얼과 스토리, 음악까지 선보일 <위대한 쇼맨>은\n" +
                        "  ‘우리는 누구나 특별하다’는 메시지로 관객들에게 재미는 물론, 감동까지 선사할 것이다.\n" +
                        "  \n" +
                        "  THIS IS ME! 우리는 누구나 특별하다!");//번들에 넘길 값 저장

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                MovieInformationActivity fragment2 = new MovieInformationActivity();//프래그먼트2 선언
                fragment2.setArguments(bundle);//번들을 프래그먼트2로 보낼 준비
                transaction.replace(R.id.container, fragment2);
                getFragmentManager().beginTransaction().replace(R.id.container, new MovieInformationActivity()).commit();
                transaction.commit();

            }

        });

        iv26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                bundle.putString("genre", "드라마");//번들에 넘길 값 저장
                bundle.putString("title", "크루엘라");//번들에 넘길 값 저장

                String url = "https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=크루엘라";
                Document doc = null;
                try {
                    doc = Jsoup.connect(url).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Elements element = doc.select("div.info_group");
                Element el = element.get(2);
                bundle.putString("star", "(" + el.text() + "★)");//번들에 넘길 값 저장

                bundle.putString("poster_image", "/image/크루엘라.jpg");//번들에 넘길 값 저장
                bundle.putString("synopsis", "처음부터 난 알았어. 내가 특별하단 걸\n" +
                        "  그게 불편한 인간들도 있겠지만 모두의 비위를 맞출 수는 없잖아?\n" +
                        "  그러다 보니 결국, 학교를 계속 다닐 수가 없었지\n" +
                        "  \n" +
                        "  우여곡절 런던에 오게 된 나, 에스텔라는 재스퍼와 호레이스를 운명처럼 만났고\n" +
                        "  나의 뛰어난 패션 감각을 이용해 완벽한 변장과 빠른 손놀림으로 런던 거리를 싹쓸이 했어\n" +
                        "  \n" +
                        "  도둑질이 지겹게 느껴질 때쯤, 꿈에 그리던 리버티 백화점에 낙하산(?)으로 들어가게 됐어\n" +
                        "  거리를 떠돌았지만 패션을 향한 나의 열정만큼은 언제나 진심이었거든\n" +
                        "  \n" +
                        "  근데 이게 뭐야, 옷에는 손도 못 대보고 하루 종일 바닥 청소라니\n" +
                        "  인내심에 한계를 느끼고 있을 때, 런던 패션계를 꽉 쥐고 있는 남작 부인이 나타났어\n" +
                        "  천재는 천재를 알아보는 법! 난 남작 부인의 브랜드 디자이너로 들어가게 되었지\n" +
                        "  \n" +
                        "  꿈을 이룰 것 같았던 순간도 잠시, 세상에 남작 부인이 ‘그런 사람’이었을 줄이야…\n" +
                        "  \n" +
                        "  그래서 난 내가 누군지 보여주기로 했어\n" +
                        "  잘가, 에스텔라\n" +
                        "  \n" +
                        "  난 이제 크루엘라야!");//번들에 넘길 값 저장

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                MovieInformationActivity fragment2 = new MovieInformationActivity();//프래그먼트2 선언
                fragment2.setArguments(bundle);//번들을 프래그먼트2로 보낼 준비
                transaction.replace(R.id.container, fragment2);
                getFragmentManager().beginTransaction().replace(R.id.container, new MovieInformationActivity()).commit();
                transaction.commit();

            }

        });



        iv28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                bundle.putString("genre", "공상과학");//번들에 넘길 값 저장
                bundle.putString("title", "매트릭스");//번들에 넘길 값 저장

                String url = "https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=영화매트릭스";
                Document doc = null;
                try {
                    doc = Jsoup.connect(url).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Elements element = doc.select("div.info_group");
                Element el = element.get(2);
                bundle.putString("star", "(" + el.text() + "★)");//번들에 넘길 값 저장

                bundle.putString("poster_image", "/image/매트릭스.jpg");//번들에 넘길 값 저장
                bundle.putString("synopsis", "서기 2199년,\n" +
                        " 인공지능 AI에 의해 인류가 재배되고 있다!\n" +
                        " 인간의 기억마저 AI에 의해 입력되고 삭제 되는 세상.진짜보다 더 진짜 같은 가상 현실 ‘매트릭스’\n" +
                        "  그 속에서 진정한 현실을 인식할 수 없게 재배되는 인간들.\n" +
                        "  그 ‘매트릭스’를 빠져 나오면서 AI에게 가장 위험한 인물이 된\n" +
                        "  '모피어스’는 자신과 함께 인류를 구할 마지막 영웅 ‘그’를 찾아 헤맨다.\n" +
                        "  \n" +
                        "  마침내 ‘모피어스’는 낮에는 평범한 회사원으로, 밤에는 해커로 활동하는 청년 ‘네오’를 ‘그’로 지목하는데…\n" +
                        "  \n" +
                        "  꿈에서 깨어난 자들,\n" +
                        "  이제 그들이 만드는 새로운 세상이 열린다!");//번들에 넘길 값 저장

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                MovieInformationActivity fragment2 = new MovieInformationActivity();//프래그먼트2 선언
                fragment2.setArguments(bundle);//번들을 프래그먼트2로 보낼 준비
                transaction.replace(R.id.container, fragment2);
                getFragmentManager().beginTransaction().replace(R.id.container, new MovieInformationActivity()).commit();
                transaction.commit();

            }

        });

        iv29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                bundle.putString("genre", "공상과학");//번들에 넘길 값 저장
                bundle.putString("title", "인터스텔라");//번들에 넘길 값 저장

                String url = "https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=인터스텔라";
                Document doc = null;
                try {
                    doc = Jsoup.connect(url).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Elements element = doc.select("div.info_group");
                Element el = element.get(2);
                bundle.putString("star", "(" + el.text() + "★)");//번들에 넘길 값 저장

                bundle.putString("poster_image", "/image/인터스텔라.jpg");//번들에 넘길 값 저장
                bundle.putString("synopsis", "“우린 답을 찾을 거야, 늘 그랬듯이”\n" +
                        " 세계 각국의 정부와 경제가 완전히 붕괴된 미래가 다가온다.\n" +
                        "  지난 20세기에 범한 잘못이 전 세계적인 식량 부족을 불러왔고, NASA도 해체되었다.\n" +
                        "  이때 시공간에 불가사의한 틈이 열리고, 남은 자들에게는 이 곳을 탐험해 인류를 구해야 하는 임무가 지워진다.\n" +
                        "  사랑하는 가족들을 뒤로 한 채 인류라는 더 큰 가족을 위해, 그들은 이제 희망을 찾아 우주로 간다.\n" +
                        "  그리고 우린 답을 찾을 것이다. 늘 그랬듯이…");//번들에 넘길 값 저장

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                MovieInformationActivity fragment2 = new MovieInformationActivity();//프래그먼트2 선언
                fragment2.setArguments(bundle);//번들을 프래그먼트2로 보낼 준비
                transaction.replace(R.id.container, fragment2);
                getFragmentManager().beginTransaction().replace(R.id.container, new MovieInformationActivity()).commit();
                transaction.commit();

            }

        });

        iv30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                bundle.putString("genre", "공상과학");//번들에 넘길 값 저장
                bundle.putString("title", "아이언맨");//번들에 넘길 값 저장

                String url = "https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=아이언맨";
                Document doc = null;
                try {
                    doc = Jsoup.connect(url).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Elements element = doc.select("div.info_group");
                Element el = element.get(2);
                bundle.putString("star", "(" + el.text() + "★)");//번들에 넘길 값 저장

                bundle.putString("poster_image", "/image/아이언맨.jpg");//번들에 넘길 값 저장
                bundle.putString("synopsis", "하이테크 슈퍼 히어로의 탄생 | 이제 업그레이드는 끝났다\n" +
                        " 천재적인 두뇌와 재능으로 세계 최강의 무기업체를 이끄는 CEO이자, 타고난 매력으로 셀러브리티 못지않은 화려한 삶을 살아가던 억만장자 토니 스타크. 아프가니스탄에서 자신이 개발한 신무기 발표를 성공리에 마치고 돌아가던 그는 게릴라군의 갑작스런 공격에 의해 가슴에 치명적인 부상을 입고 게릴라군에게 납치된다. 가까스로 목숨을 건진 그에게 게릴라군은 자신들을 위한 강력한 무기를 개발하라며 그를 위협한다. 그러나 그는 게릴라군을 위한 무기 대신, 탈출을 위한 무기가 장착된 철갑수트를 몰래 만드는 데 성공하고, 그의 첫 수트인 ‘Mark1’를 입고 탈출에 성공한다.\n" +
                        "   미국으로 돌아온 토니 스타크는 자신이 만든 무기가 많은 사람들의 생명을 위협하고, 세상을 엄청난 위험에 몰아넣고 있다는 사실을 깨닫고 무기사업에서 손 뗄 것을 선언한다. 그리고, Mark1을 토대로 최강의 하이테크 수트를 개발하는 데 자신의 천재적인 재능과 노력을 쏟아 붓기 시작한다. 탈출하는 당시 부서져버린 Mark1를 바탕으로 보다 업그레이드 된 수트 Mark2를 만들어낸 토니 스타크. 거기에 만족하지 않고, 숱한 시행착오와 실패 끝에 자신의 모든 능력과 현실에서 가능한 최강의 최첨단 과학 기술이 집적된 하이테크 수트 Mark3를 마침내 완성, 최강의 슈퍼히어로 ‘아이언맨’으로 거듭난다.\n" +
                        "   토니 스타크가 탈출하는 과정을 통해 Mark1의 가공할 위력을 확인한 게릴라 군은 토니 스타크가 미처 회수하지 못한 Mark1의 잔해와 설계도를 찾아낸다. Mark1을 재조립하여 그들의 목적을 이루기 위한 거대하고 강력한 철갑수트를 제작하려는 음모를 꾸미는 게릴라군. 토니 스타크가 갖고 있던 에너지원을 훔쳐 ‘아이언맨’을 능가하는 거대하고 강력한 ‘아이언 몽거’를 완성한 그들은 세계 평화를 위협하고, 토니 스타크는 그들의 음모과 배후세력이 누구인지를 알게 되는데...!");//번들에 넘길 값 저장

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                MovieInformationActivity fragment2 = new MovieInformationActivity();//프래그먼트2 선언
                fragment2.setArguments(bundle);//번들을 프래그먼트2로 보낼 준비
                transaction.replace(R.id.container, fragment2);
                getFragmentManager().beginTransaction().replace(R.id.container, new MovieInformationActivity()).commit();
                transaction.commit();

            }

        });


        iv32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                bundle.putString("genre", "전쟁");//번들에 넘길 값 저장
                bundle.putString("title", "인천상륙작전");//번들에 넘길 값 저장

                String url = "https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=인천상륙작전";
                Document doc = null;
                try {
                    doc = Jsoup.connect(url).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Elements element = doc.select("div.info_group");
                Element el = element.get(2);
                bundle.putString("star", "(" + el.text() + "★)");//번들에 넘길 값 저장

                bundle.putString("poster_image", "/image/인천상륙작전.jpg");//번들에 넘길 값 저장
                bundle.putString("synopsis", "1950년 6월 25일 북한의 기습 남침으로 불과 사흘 만에 서울 함락,\n" +
                        "  한 달 만에 낙동강 지역을 제외한 한반도 전 지역을 빼앗기게 된 대한민국.\n" +
                        "  국제연합군 최고사령관 ‘더글라스 맥아더’(리암 니슨)는 모두의 반대 속 인천상륙작전을 계획한다.\n" +
                        "  성공확률 5000:1, 불가능에 가까운 작전.\n" +
                        "  이를 가능케 하는 것은 단 하나, 인천으로 가는 길이 확보되어야 하는 것뿐이다.\n" +
                        "  \n" +
                        "  맥아더의 지시로 대북 첩보작전 ‘X-RAY’에 투입된 해군 첩보부대 대위 ‘장학수’(이정재)는\n" +
                        "  북한군으로 위장 잠입해 인천 내 동태를 살피며 정보를 수집하기 시작한다.\n" +
                        "  하지만 인천 방어사령관 ‘림계진’(이범수)에 의해 정체가 발각되는 위기에 놓인 가운데\n" +
                        "  ‘장학수’와 그의 부대원들은 전세를 바꿀 단 한번의 기회, 단 하루의 작전을 위해\n" +
                        "  인천상륙 함대를 유도하는 위험천만한 임무에 나서는데...\n" +
                        "  \n" +
                        "  역사를 바꾼 비밀 연합작전\n" +
                        "  그 시작은 바로 그들이었다!");//번들에 넘길 값 저장

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                MovieInformationActivity fragment2 = new MovieInformationActivity();//프래그먼트2 선언
                fragment2.setArguments(bundle);//번들을 프래그먼트2로 보낼 준비
                transaction.replace(R.id.container, fragment2);
                getFragmentManager().beginTransaction().replace(R.id.container, new MovieInformationActivity()).commit();
                transaction.commit();

            }

        });

        iv33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                bundle.putString("genre", "전쟁");//번들에 넘길 값 저장
                bundle.putString("title", "라이언 일병 구하기");//번들에 넘길 값 저장

                String url = "https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=라이언일병구하기";
                Document doc = null;
                try {
                    doc = Jsoup.connect(url).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Elements element = doc.select("div.info_group");
                Element el = element.get(2);
                bundle.putString("star", "(" + el.text() + "★)");//번들에 넘길 값 저장

                bundle.putString("poster_image", "/image/라이언일병구하기.jpg");//번들에 넘길 값 저장
                bundle.putString("synopsis", "임무는 단 한 사람(The Mission is a Man)\n" +
                        " 1944년 6월 6일 노르망디 상륙 작전. 오마하 해변에 대기하고 있던 병사들은 한치 앞도 내다볼 수 없는 긴장된 상황과 두려움에 무기력함을 감출 수 없었다. 노르망디 해변을 응시하는 밀러 대위(Captain Miller: 톰 행크스 분) 그리고 전쟁 중 가장 어려운 임무를 수행해야할 두려움에 지친 그의 대원들. 지옥을 방불케하는 치열한 총격전이 벌어지고 수 많은 병사들이 총 한번 제대로 쏘지 못하고 쓰러져간다. 마침내 밀러 대위를 위시한 그들은 몇번의 죽을 고비를 넘기고 맡은 바 임무를 완수한다.\n" +
                        "   같은 시각, 2차 대전이 종전으로 치닫는 치열한 전황 속에서 미 행정부는 전사자 통보 업무를 진행하던 중 충격적인 사실을 발견하게 된다. 4형제 모두 이 전쟁에 참전한 라이언 가에서 며칠간의 시차를 두고 3형제가 이미 전사하고 막내 제임스 라이언 일병(Private Ryan: 맷 데몬 분)만이 프랑스 전선에 생존해 있음을 알게 된 것이다. 네명의 아들 가운데 이미 셋을 잃은 라이언 부인을 위해 미 행정부는 막내 제임스를 구하기 위한 매우 특별한 작전을 지시한다. 결국 사령부에서 막내를 찾아 집으로 보낼 임무는 밀러에게 부여되고, 이것은 이제껏 수행했던 임무보다 훨씬 어렵고 힘든 것이었다.\n" +
                        "   밀러는 여섯 명의 대원들과 통역병 업햄(Corporal Timothy Upham: 제레미 데이비스 분) 등 새로운 팀을 구성, 작전에 투입된다. 라이언의 행방을 찾아 최전선에 투입된 밀러와 대원들은 미군에게 접수된 마을을 지나던 중 의외로 쉽게 그를 찾아낸다. 하지만 임무 완수의 기쁨도 잠시. 그는 제임스 라이언과 성만 같은 다른 인물로 밝혀진다. 다음 날, 밀러 일행은 우연히 한 부상병을 통해 제임스 라이언이 라멜 지역의 다리를 사수하기 위해 작전에 투입됐고, 현재는 독일군 사이에 고립돼 있다는 사실을 듣게 된다.\n" +
                        "   단 한명의 목숨을 구하기 위해 여덟 명이 위험을 감수해야할 상황에서 대원들은 과연 ‘라이언 일병 한 명의 생명이 그들 여덟 명의 생명보다 더 가치가 있는 것인가?’라는 끊임없는 혼란에 빠진다. 하지만 지휘관으로서 작전을 끝까지 책임지고 성공적으로 완수해야 할 밀러는 부하들을 설득해 다시 라이언 일병이 있다는 곳으로 향한다. 도중에 독일군과의 간헐적인 전투를 치르면서 결국 밀러 일행은 라멜 외곽지역에서 극적으로 라이언 일병을 찾아낸다. 하지만 라이언은 다리를 사수해야할 동료들을 사지에 남겨두고 혼자 돌아가는 것을 거부하는데.");//번들에 넘길 값 저장

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                MovieInformationActivity fragment2 = new MovieInformationActivity();//프래그먼트2 선언
                fragment2.setArguments(bundle);//번들을 프래그먼트2로 보낼 준비
                transaction.replace(R.id.container, fragment2);
                getFragmentManager().beginTransaction().replace(R.id.container, new MovieInformationActivity()).commit();
                transaction.commit();

            }

        });

        iv34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                bundle.putString("genre", "전쟁");//번들에 넘길 값 저장
                bundle.putString("title", "블랙 호크 다운");//번들에 넘길 값 저장

                String url = "https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=블랙호크다운";
                Document doc = null;
                try {
                    doc = Jsoup.connect(url).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Elements element = doc.select("div.info_group");
                Element el = element.get(2);
                bundle.putString("star", "(" + el.text() + "★)");//번들에 넘길 값 저장

                bundle.putString("poster_image", "/image/블랙호크다운.jpg");//번들에 넘길 값 저장
                bundle.putString("synopsis", "전세계 최강의 블록버스터 드림팀! 제리 부룩하이머 제작, 리들리 스콧 감독\n" +
                        "   {1992년 동아프리카 소말리아. 몇년 동안의 부족간 전쟁은 대기근을 가져왔고, 그로인해 30만여 명이 굶어죽었다. 수도 모가디슈(Mogadishu)의 통치자인 강력한 군벌 모하메드 파라 아이디드(Mohamed Farrah Aidid)는 각국에서 보내온 구호물자들을 빼앗았고, 굶주림은 그의 무기였다. 세계가 이에 대응하여 미해병대 2만여 명의 병력을 투입시키자 식량은 제대로 전달되고, 상황이 호전되는 듯 했다. 1993년 4월, 미해병대가 철수하자 아이디드는 곧바로 남아있던 UN 평화유지군에게 전쟁을 선포한다. 6월 아이디드의 민병대는 24명의 파키스탄군 병사들을 사살하고, 미군에게도 공격을 시작했다. 늦은 8월, 미국의 특수부대인 델타포스(Delta Force), 레인저(Army Rangers), 그리고 160 특수비행단(160th SOAR) 등이 아이디드를 체포하고 평화를 되찾는 임무를 받고 모가디슈로 보내진다. 원래 3주예정이었던 임무가 6주를 넘어서자 워싱턴에선 조바심이 나게 된다.}\n" +
                        "   1993년 10월, 최정상의 미군부대가 UN 평화유지작전의 일환으로 소말리아의 수도 모가디슈로 파견된다. 그들의 임무는 소말리아를 황폐화시키고 있는 내란과 기근을 진압하기 위한 작전으로서 소말리아의 악독한 민군대장인 모하메드 파라 에이디드의 두 최고 부관을 납치하는 일이다. 동 아프리카 전역에 걸친 기아는 UN에 의해 제공되는 구호 식량을 착취하는 에이디드와 같은 민병대장으로 인해 30만 명이라는 대량 사상자를 내었다. 죽이기 위한 것이 아닌 다수의 생명을 살리려는 의지를 품고 소말리아에 도착한 미국의 정예부대. 육군 중사 맷 에버스만(조쉬 하트넷 분)은 이상적인 젊은 유격군으로서 목표한 건물을 지키기 위해 할당받은 4지점 중 하나를 지휘하면서 자신의 용기와 의지를 시험받게 된다. 군사 서기관이지만 항상 모험을 동경해왔던 유격군 특수병 그림스(이완 맥그리거 분)는 이번 전투를 통해 그의 오랜 희망이기도 했던 실제 전투상황을 경험하게 된다.\n" +
                        "   작전은 10월 3일 오후 3시 42분에 시작하여 1시간 가량 소요될 예정이었으나, 20분 간격으로 무적의 전투 헬리콥터인 '블랙 호크' 슈퍼 61과 슈퍼 64가 차례로 격추되면서 임무는 '공격'에서 '구출'과 '생존'으로 바뀌면서 절박한 국면을 맞이하게 된다. 고립된 젊은 유격부대와 베테랑 델타 부대원들은 구조 호위대가 그들을 찾으러 올 때까지 18시간동안 폐허가 된 모가디슈의 한 구역에서 부상당한 채 갇혀있어야 했다. 도시 전체는 미군이 완전무장된 소말리아 민병대로부터 공격을 받으면서 완전 쑥대밭이 된다. 극도의 팽팽한 긴장감과 전우를 잃어버린 허탈감 속에서도 군인들 사이에선 서로간의 협조가 이루어지고 전우애와 인간애의 진정한 면모를 배우게 되는데.");//번들에 넘길 값 저장

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                MovieInformationActivity fragment2 = new MovieInformationActivity();//프래그먼트2 선언
                fragment2.setArguments(bundle);//번들을 프래그먼트2로 보낼 준비
                transaction.replace(R.id.container, fragment2);
                getFragmentManager().beginTransaction().replace(R.id.container, new MovieInformationActivity()).commit();
                transaction.commit();

            }

        });


        return view;

    }
}