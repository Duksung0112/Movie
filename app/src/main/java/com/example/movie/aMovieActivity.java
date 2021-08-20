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
                        iv3.setImageBitmap(bitmap);
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
                        iv5.setImageBitmap(bitmap);
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
                        iv7.setImageBitmap(bitmap);
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

        iv4.setOnClickListener(new View.OnClickListener() {
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

        iv5.setOnClickListener(new View.OnClickListener() {
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

        iv6.setOnClickListener(new View.OnClickListener() {
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

        iv7.setOnClickListener(new View.OnClickListener() {
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

        iv8.setOnClickListener(new View.OnClickListener() {
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


        return view;

    }
}