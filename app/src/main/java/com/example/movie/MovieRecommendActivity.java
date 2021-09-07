package com.example.movie;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

import androidx.appcompat.app.AppCompatActivity;
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
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieRecommendActivity extends Fragment {
    TextView tvgenre, tvtitle, tvexplain;
    int num;
    ImageView imgposter;
    String genre, title, poster_image, explain , id;
    String TAG = "Retrofit moviereco";

    String base = "http://3.36.121.174";
    Bitmap bitmap;
    Button btnback;

    //Retrofit 인스턴스 생성
    retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
            .baseUrl("http://3.36.121.174:8081/")    // baseUrl 등록
            .addConverterFactory(GsonConverterFactory.create())  // Gson 변환기 등록
            .build();

    // 레트로핏 인터페이스 객체 구현
    RetrofitService service = retrofit.create(RetrofitService.class);


    public MovieRecommendActivity() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.movie_recommend, container, false);

        tvgenre = (TextView) view.findViewById(R.id.tvgenre);
        tvtitle = (TextView) view.findViewById(R.id.tvtitle);
        tvexplain = (TextView) view.findViewById(R.id.tvexplain);
        imgposter = (ImageView) view.findViewById(R.id.imgposter);
        btnback = (Button) view.findViewById(R.id.btnback);

        tvexplain.setMovementMethod(new ScrollingMovementMethod());


        Bundle extra = this.getArguments();
        if(extra != null) {
            extra = getArguments();
            genre = extra.getString("genre");
            title = extra.getString("title");
            poster_image = extra.getString("poster_image");
            explain = extra.getString("explain");
            num=extra.getInt("num");

            tvgenre.setText("[" + genre + "] ");
            tvtitle.setText(title);
            tvexplain.setText(explain);

            Thread uThread = new Thread() {
                @Override
                public void run(){
                    try{
                        //서버에 올려둔 이미지 URL
                        URL url = new URL(base + poster_image);
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

            btnback.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {

                    startActivity(new Intent(getActivity(), MenuMainActivity.class));

                    PostResultDiary postresultdiary = new PostResultDiary("test1",num, title, "", poster_image);

                    Call<PostResultDiary> call = service.AddDiary(postresultdiary);

                    System.out.println("num="+num);
                    System.out.println("title="+title);
                    System.out.println("poster_image="+poster_image);

                    call.enqueue(new Callback<PostResultDiary>() {
                        @Override
                        public void onResponse(Call<PostResultDiary> call, Response<PostResultDiary> response) {
                            Log.e(TAG, "onResponse");
                            if(response.isSuccessful()){
                                Log.e(TAG, "onResponse success");
                                Toast.makeText(getActivity(), "다이어리에 추가되었습니다", Toast.LENGTH_SHORT).show();

                            }
                            else{
                                // 실패
                                Log.e(TAG, "onResponse fail");
                            }
                        }

                        @Override
                        public void onFailure(Call<PostResultDiary> call, Throwable t) {
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