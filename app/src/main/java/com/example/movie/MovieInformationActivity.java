package com.example.movie;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieInformationActivity extends Fragment {
    String genre, title, synopsis, star, poster_image;
    TextView tvgenre, tvtitle, tvsynopsis, tvstar;
    ImageView imgposter;
    Bitmap bitmap;
    Button btadd;
    String TAG = "Retrofit movieinfo";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.movie_information, container, false);

        //Retrofit 인스턴스 생성
        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl("http://52.79.129.64:8081/")    // baseUrl 등록
                .addConverterFactory(GsonConverterFactory.create())  // Gson 변환기 등록
                .build();

        // 레트로핏 인터페이스 객체 구현
        RetrofitService service = retrofit.create(RetrofitService.class);

        tvgenre = (TextView) view.findViewById(R.id.tvgenre);
        tvtitle = (TextView) view.findViewById(R.id.tvtitle);
        tvsynopsis = (TextView) view.findViewById(R.id.tvcontent);
        tvstar = (TextView) view.findViewById(R.id.tvstar);
        imgposter = (ImageView) view.findViewById(R.id.imgposter);
        btadd = (Button) view.findViewById(R.id.btadd);

        tvsynopsis.setMovementMethod(new ScrollingMovementMethod());


        if (getArguments() != null)
        {
            genre = getArguments().getString("genre"); // 프래그먼트1에서 받아온 값 넣기
            title = getArguments().getString("title"); // 프래그먼트1에서 받아온 값 넣기
            synopsis = getArguments().getString("synopsis"); // 프래그먼트1에서 받아온 값 넣기
            star = getArguments().getString("star"); // 프래그먼트1에서 받아온 값 넣기
            poster_image = getArguments().getString("poster_image"); // 프래그먼트1에서 받아온 값 넣기

            System.out.println(genre);

            tvgenre.setText("[" + genre + "] ");
            tvtitle.setText(title);
            tvsynopsis.setText(synopsis);
            tvstar.setText(star);

            Thread uThread = new Thread() {
                @Override
                public void run(){
                    try{
                        //서버에 올려둔 이미지 URL
                        URL url = new URL("http://52.79.129.64" + poster_image);
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
                imgposter.setImageBitmap(bitmap);
            }catch (InterruptedException e){
                e.printStackTrace();
            }


            btadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    PostResultWishlist postresultwishlist = new PostResultWishlist(title, synopsis, poster_image, genre);

                    Call<PostResultWishlist> call = service.AddWishlist(postresultwishlist);

                    call.enqueue(new Callback<PostResultWishlist>() {
                        @Override
                        public void onResponse(Call<PostResultWishlist> call, Response<PostResultWishlist> response) {
                            Log.e(TAG, "onResponse");
                            if(response.isSuccessful()){
                                Log.e(TAG, "onResponse success");
                                Toast.makeText(getActivity(), "위시리스트에 추가되었습니다", Toast.LENGTH_SHORT).show();
                                getFragmentManager().beginTransaction().replace(R.id.container, new aMovieActivity()).commit();

                            }
                            else{
                                // 실패
                                Log.e(TAG, "onResponse fail");
                            }
                        }

                        @Override
                        public void onFailure(Call<PostResultWishlist> call, Throwable t) {
                            // 통신 실패
                            Log.e(TAG, "onFailure: " + t.getMessage());
                        }
                    });


                }

            }) ;


        }







        return view;

    }


}
