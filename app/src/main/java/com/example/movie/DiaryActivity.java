package com.example.movie;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

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

public class DiaryActivity extends Fragment {

    String TAG = "Retrofit diaryactivity";
    String base = "http://3.36.121.174";
    Bitmap bitmap;
    Bundle bundle = new Bundle(); // 번들을 통해 값 전달


    public DiaryActivity(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_diary, container, false);

        //Retrofit 인스턴스 생성
        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl("http://3.36.121.174:8081/")    // baseUrl 등록
                .addConverterFactory(GsonConverterFactory.create())  // Gson 변환기 등록
                .build();

        // 레트로핏 인터페이스 객체 구현
        RetrofitService service = retrofit.create(RetrofitService.class);

        ListView listView = (ListView)view.findViewById(R.id.diarylist);

        MyAdapter2 mMyAdapter = new MyAdapter2();

        if (android.os.Build.VERSION.SDK_INT > 9) { StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); StrictMode.setThreadPolicy(policy); }

        mMyAdapter.clearItem();
        // listview 갱신
        mMyAdapter.notifyDataSetChanged();

        Call<List<PostResultDiary>> call = service.getDiaryList();

        call.enqueue(new Callback<List<PostResultDiary>>() {
            @Override
            public void onResponse(Call<List<PostResultDiary>> call, Response<List<PostResultDiary>> response) {
                Log.e(TAG, "call onResponse");
                if (response.isSuccessful()) {
                    Log.e(TAG, "call onResponse success");
                    List<PostResultDiary> result = response.body();

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                            bundle.putString("title", result.get(0).title);//번들에 넘길 값 저장
                            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                            DiaryMemoActivity fragment3 = new DiaryMemoActivity();//프래그먼트3 선언
                            fragment3.setArguments(bundle);//번들을 프래그먼트3로 보낼 준비
                            transaction.replace(R.id.container, fragment3);
                            getFragmentManager().beginTransaction().replace(R.id.container, new DiaryMemoActivity()).commit();
                            transaction.commit();
                        }
                    });

                    for (PostResultDiary item : result) {
                        Thread uThread = new Thread() {
                            @Override
                            public void run(){
                                try{
                                    //서버에 올려둔 이미지 URL
                                    URL url = new URL(base + item.poster_image);
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
                            mMyAdapter.addItem(bitmap, item.title);

                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }

                    listView.setAdapter(mMyAdapter);


                } else {
                    // 실패
                    Log.e(TAG, "call onResponse fail");
                }
            }
            @Override
            public void onFailure(Call<List<PostResultDiary>> call, Throwable t) {
                // 통신 실패
                Log.e(TAG, "call onFailure: " + t.getMessage());
            }

        });





        return view;

    }



}
