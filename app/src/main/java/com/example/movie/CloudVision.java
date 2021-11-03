package com.example.movie;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.vision.v1.Vision;
import com.google.api.services.vision.v1.VisionRequest;
import com.google.api.services.vision.v1.VisionRequestInitializer;
import com.google.api.services.vision.v1.model.AnnotateImageRequest;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesRequest;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesResponse;
import com.google.api.services.vision.v1.model.FaceAnnotation;
import com.google.api.services.vision.v1.model.Feature;
import com.google.api.services.vision.v1.model.Image;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;


public class CloudVision extends AppCompatActivity {
    private static final String CLOUD_VISION_API_KEY = BuildConfig.API_KEY;
    public static final String FILE_NAME = "temp.jpg";
    private static final String ANDROID_CERT_HEADER = "X-Android-Cert";
    private static final String ANDROID_PACKAGE_HEADER = "X-Android-Package";
    private static final int MAX_LABEL_RESULTS = 10;
    private static final int MAX_DIMENSION = 1200;

    private static final String TAG = CloudVision.class.getSimpleName();
    private static final int GALLERY_PERMISSIONS_REQUEST = 0;
    private static final int GALLERY_IMAGE_REQUEST = 1;
    public static final int CAMERA_PERMISSIONS_REQUEST = 2;
    public static final int CAMERA_IMAGE_REQUEST = 3;

    private static String angerstar, joystar, sorrowstar, surprisestar;

    private TextView mImageDetails;
    private ImageView mMainImage;

    private static String base = "http://3.36.121.174";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cloud_vision);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        mImageDetails = findViewById(R.id.image_details);
        mMainImage = findViewById(R.id.main_image);

        //Retrofit 인스턴스 생성
        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl("http://3.36.121.174:8081/")    // baseUrl 등록
                .addConverterFactory(GsonConverterFactory.create())  // Gson 변환기 등록
                .build();

        RetrofitService service = retrofit.create(RetrofitService.class);;

        ChooseDialog();

        Button fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                if (angerstar.equals("★★★★★")) {

                    Random rand = new Random();
                    int j = rand.nextInt(2);

                    if(j == 0) {

                        Call<List<PostResultWishlist>> call = service.getByWishlistGenre("액션");

                        call.enqueue(new Callback<List<PostResultWishlist>>() {
                            @Override
                            public void onResponse(Call<List<PostResultWishlist>> call, Response<List<PostResultWishlist>> response) {
                                Log.e(TAG, "call onResponse");
                                if (response.isSuccessful()) {
                                    Log.e(TAG, "call onResponse success");
                                    List<PostResultWishlist> result = response.body();

                                    Random rand = new Random();
                                    int i = rand.nextInt(result.size());

                                    MovieRecommendActivity fragment = new MovieRecommendActivity();
                                    Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                                    bundle.putString("genre", result.get(i).genre);//번들에 넘길 값 저장
                                    bundle.putString("title", result.get(i).title);//번들에 넘길 값 저장
                                    bundle.putInt("num", result.get(i).num);//번들에 넘길 값 저장
                                    bundle.putString("poster_image", result.get(i).poster_image);//번들에 넘길 값 저장
                                    bundle.putString("explain", "감정 분석 결과, ANGRY 수치가 높은 것으로 나타나 "
                                            + result.get(i).genre + " 장르의 영화 " + result.get(i).title + "을(를) 추천드립니다.");//번들에 넘길 값 저장


                                    FragmentManager fm = getSupportFragmentManager();
                                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                                    fragment.setArguments(bundle);

                                    fragmentTransaction.replace(R.id.cloud, fragment);
                                    fragmentTransaction.commit();

                                } else {
                                    // 실패
                                    Log.e(TAG, "call onResponse fail");
                                }
                            }

                            @Override
                            public void onFailure(Call<List<PostResultWishlist>> call, Throwable t) {
                                // 통신 실패
                                Log.e(TAG, "call onFailure: " + t.getMessage());
                            }

                        });
                    }

                    else if(j == 1) {

                        Call<List<PostResultWishlist>> call = service.getByWishlistGenre("전쟁");

                        call.enqueue(new Callback<List<PostResultWishlist>>() {
                            @Override
                            public void onResponse(Call<List<PostResultWishlist>> call, Response<List<PostResultWishlist>> response) {
                                Log.e(TAG, "call onResponse");
                                if (response.isSuccessful()) {
                                    Log.e(TAG, "call onResponse success");
                                    List<PostResultWishlist> result = response.body();

                                    Random rand = new Random();
                                    int i = rand.nextInt(result.size());

                                    MovieRecommendActivity fragment = new MovieRecommendActivity();
                                    Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                                    bundle.putString("genre", result.get(i).genre);//번들에 넘길 값 저장
                                    bundle.putString("title", result.get(i).title);//번들에 넘길 값 저장
                                    bundle.putInt("num", result.get(i).num);//번들에 넘길 값 저장
                                    bundle.putString("poster_image", result.get(i).poster_image);//번들에 넘길 값 저장
                                    bundle.putString("explain", "감정 분석 결과, ANGRY 수치가 높은 것으로 나타나 "
                                            + result.get(i).genre + " 장르의 영화 " + result.get(i).title + "을(를) 추천드립니다.");//번들에 넘길 값 저장


                                    FragmentManager fm = getSupportFragmentManager();
                                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                                    fragment.setArguments(bundle);

                                    fragmentTransaction.replace(R.id.cloud, fragment);
                                    fragmentTransaction.commit();

                                } else {
                                    // 실패
                                    Log.e(TAG, "call onResponse fail");
                                }
                            }

                            @Override
                            public void onFailure(Call<List<PostResultWishlist>> call, Throwable t) {
                                // 통신 실패
                                Log.e(TAG, "call onFailure: " + t.getMessage());
                            }

                        });
                    }
                }

                else if (joystar.equals("★★★★★")) {

                    Random rand = new Random();
                    int j = rand.nextInt(2);

                    if(j == 0) {

                        Call<List<PostResultWishlist>> call = service.getByWishlistGenre("로맨스");

                        call.enqueue(new Callback<List<PostResultWishlist>>() {
                            @Override
                            public void onResponse(Call<List<PostResultWishlist>> call, Response<List<PostResultWishlist>> response) {
                                Log.e(TAG, "call onResponse");
                                if (response.isSuccessful()) {
                                    Log.e(TAG, "call onResponse success");
                                    List<PostResultWishlist> result = response.body();

                                    Random rand = new Random();
                                    int i = rand.nextInt(result.size());

                                    MovieRecommendActivity fragment = new MovieRecommendActivity();
                                    Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                                    bundle.putString("genre", result.get(i).genre);//번들에 넘길 값 저장
                                    bundle.putString("title", result.get(i).title);//번들에 넘길 값 저장
                                    bundle.putInt("num", result.get(i).num);//번들에 넘길 값 저장
                                    bundle.putString("poster_image", result.get(i).poster_image);//번들에 넘길 값 저장
                                    bundle.putString("explain", "감정 분석 결과, JOY 수치가 높은 것으로 나타나 "
                                            + result.get(i).genre + " 장르의 영화 " + result.get(i).title + "을(를) 추천드립니다.");//번들에 넘길 값 저장


                                    FragmentManager fm = getSupportFragmentManager();
                                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                                    fragment.setArguments(bundle);

                                    fragmentTransaction.replace(R.id.cloud, fragment);
                                    fragmentTransaction.commit();

                                } else {
                                    // 실패
                                    Log.e(TAG, "call onResponse fail");
                                }
                            }

                            @Override
                            public void onFailure(Call<List<PostResultWishlist>> call, Throwable t) {
                                // 통신 실패
                                Log.e(TAG, "call onFailure: " + t.getMessage());
                            }

                        });
                    }

                    else if(j == 1) {

                        Call<List<PostResultWishlist>> call = service.getByWishlistGenre("판타지");

                        call.enqueue(new Callback<List<PostResultWishlist>>() {
                            @Override
                            public void onResponse(Call<List<PostResultWishlist>> call, Response<List<PostResultWishlist>> response) {
                                Log.e(TAG, "call onResponse");
                                if (response.isSuccessful()) {
                                    Log.e(TAG, "call onResponse success");
                                    List<PostResultWishlist> result = response.body();

                                    Random rand = new Random();
                                    int i = rand.nextInt(result.size());

                                    MovieRecommendActivity fragment = new MovieRecommendActivity();
                                    Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                                    bundle.putString("genre", result.get(i).genre);//번들에 넘길 값 저장
                                    bundle.putString("title", result.get(i).title);//번들에 넘길 값 저장
                                    bundle.putInt("num", result.get(i).num);//번들에 넘길 값 저장
                                    bundle.putString("poster_image", result.get(i).poster_image);//번들에 넘길 값 저장
                                    bundle.putString("explain", "감정 분석 결과, JOY 수치가 높은 것으로 나타나 "
                                            + result.get(i).genre + " 장르의 영화 " + result.get(i).title + "을(를) 추천드립니다.");//번들에 넘길 값 저장


                                    FragmentManager fm = getSupportFragmentManager();
                                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                                    fragment.setArguments(bundle);

                                    fragmentTransaction.replace(R.id.cloud, fragment);
                                    fragmentTransaction.commit();

                                } else {
                                    // 실패
                                    Log.e(TAG, "call onResponse fail");
                                }
                            }

                            @Override
                            public void onFailure(Call<List<PostResultWishlist>> call, Throwable t) {
                                // 통신 실패
                                Log.e(TAG, "call onFailure: " + t.getMessage());
                            }

                        });
                    }
                }

                else if (sorrowstar.equals("★★★★★")) {

                    Random rand = new Random();
                    int j = rand.nextInt(2);

                    if(j == 0) {

                        Call<List<PostResultWishlist>> call = service.getByWishlistGenre("코미디");

                        call.enqueue(new Callback<List<PostResultWishlist>>() {
                            @Override
                            public void onResponse(Call<List<PostResultWishlist>> call, Response<List<PostResultWishlist>> response) {
                                Log.e(TAG, "call onResponse");
                                if (response.isSuccessful()) {
                                    Log.e(TAG, "call onResponse success");
                                    List<PostResultWishlist> result = response.body();

                                    Random rand = new Random();
                                    int i = rand.nextInt(result.size());

                                    MovieRecommendActivity fragment = new MovieRecommendActivity();
                                    Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                                    bundle.putString("genre", result.get(i).genre);//번들에 넘길 값 저장
                                    bundle.putString("title", result.get(i).title);//번들에 넘길 값 저장
                                    bundle.putInt("num", result.get(i).num);//번들에 넘길 값 저장
                                    bundle.putString("poster_image", result.get(i).poster_image);//번들에 넘길 값 저장
                                    bundle.putString("explain", "감정 분석 결과, SORROW 수치가 높은 것으로 나타나 "
                                            + result.get(i).genre + " 장르의 영화 " + result.get(i).title + "을(를) 추천드립니다.");//번들에 넘길 값 저장


                                    FragmentManager fm = getSupportFragmentManager();
                                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                                    fragment.setArguments(bundle);

                                    fragmentTransaction.replace(R.id.cloud, fragment);
                                    fragmentTransaction.commit();

                                } else {
                                    // 실패
                                    Log.e(TAG, "call onResponse fail");
                                }
                            }

                            @Override
                            public void onFailure(Call<List<PostResultWishlist>> call, Throwable t) {
                                // 통신 실패
                                Log.e(TAG, "call onFailure: " + t.getMessage());
                            }

                        });
                    }

                    else if(j == 1) {

                        Call<List<PostResultWishlist>> call = service.getByWishlistGenre("드라마");

                        call.enqueue(new Callback<List<PostResultWishlist>>() {
                            @Override
                            public void onResponse(Call<List<PostResultWishlist>> call, Response<List<PostResultWishlist>> response) {
                                Log.e(TAG, "call onResponse");
                                if (response.isSuccessful()) {
                                    Log.e(TAG, "call onResponse success");
                                    List<PostResultWishlist> result = response.body();

                                    Random rand = new Random();
                                    int i = rand.nextInt(result.size());

                                    MovieRecommendActivity fragment = new MovieRecommendActivity();
                                    Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                                    bundle.putString("genre", result.get(i).genre);//번들에 넘길 값 저장
                                    bundle.putString("title", result.get(i).title);//번들에 넘길 값 저장
                                    bundle.putInt("num", result.get(i).num);//번들에 넘길 값 저장
                                    bundle.putString("poster_image", result.get(i).poster_image);//번들에 넘길 값 저장
                                    bundle.putString("explain", "감정 분석 결과, SORROW 수치가 높은 것으로 나타나 "
                                            + result.get(i).genre + " 장르의 영화 " + result.get(i).title + "을(를) 추천드립니다.");//번들에 넘길 값 저장


                                    FragmentManager fm = getSupportFragmentManager();
                                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                                    fragment.setArguments(bundle);

                                    fragmentTransaction.replace(R.id.cloud, fragment);
                                    fragmentTransaction.commit();

                                } else {
                                    // 실패
                                    Log.e(TAG, "call onResponse fail");
                                }
                            }

                            @Override
                            public void onFailure(Call<List<PostResultWishlist>> call, Throwable t) {
                                // 통신 실패
                                Log.e(TAG, "call onFailure: " + t.getMessage());
                            }

                        });
                    }
                }

                else if (surprisestar.equals("★★★★★")) {

                    Random rand = new Random();
                    int j = rand.nextInt(2);

                    if(j == 0) {

                        Call<List<PostResultWishlist>> call = service.getByWishlistGenre("액션");

                        call.enqueue(new Callback<List<PostResultWishlist>>() {
                            @Override
                            public void onResponse(Call<List<PostResultWishlist>> call, Response<List<PostResultWishlist>> response) {
                                Log.e(TAG, "call onResponse");
                                if (response.isSuccessful()) {
                                    Log.e(TAG, "call onResponse success");
                                    List<PostResultWishlist> result = response.body();

                                    Random rand = new Random();
                                    int i = rand.nextInt(result.size());

                                    MovieRecommendActivity fragment = new MovieRecommendActivity();
                                    Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                                    bundle.putString("genre", result.get(i).genre);//번들에 넘길 값 저장
                                    bundle.putString("title", result.get(i).title);//번들에 넘길 값 저장
                                    bundle.putInt("num", result.get(i).num);//번들에 넘길 값 저장
                                    bundle.putString("poster_image", result.get(i).poster_image);//번들에 넘길 값 저장
                                    bundle.putString("explain", "감정 분석 결과, SURPRISE 수치가 높은 것으로 나타나 "
                                            + result.get(i).genre + " 장르의 영화 " + result.get(i).title + "을(를) 추천드립니다.");//번들에 넘길 값 저장


                                    FragmentManager fm = getSupportFragmentManager();
                                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                                    fragment.setArguments(bundle);

                                    fragmentTransaction.replace(R.id.cloud, fragment);
                                    fragmentTransaction.commit();

                                } else {
                                    // 실패
                                    Log.e(TAG, "call onResponse fail");
                                }
                            }

                            @Override
                            public void onFailure(Call<List<PostResultWishlist>> call, Throwable t) {
                                // 통신 실패
                                Log.e(TAG, "call onFailure: " + t.getMessage());
                            }

                        });
                    }

                    else if(j == 1) {

                        Call<List<PostResultWishlist>> call = service.getByWishlistGenre("공상과학");

                        call.enqueue(new Callback<List<PostResultWishlist>>() {
                            @Override
                            public void onResponse(Call<List<PostResultWishlist>> call, Response<List<PostResultWishlist>> response) {
                                Log.e(TAG, "call onResponse");
                                if (response.isSuccessful()) {
                                    Log.e(TAG, "call onResponse success");
                                    List<PostResultWishlist> result = response.body();

                                    Random rand = new Random();
                                    int i = rand.nextInt(result.size());

                                    MovieRecommendActivity fragment = new MovieRecommendActivity();
                                    Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                                    bundle.putString("genre", result.get(i).genre);//번들에 넘길 값 저장
                                    bundle.putString("title", result.get(i).title);//번들에 넘길 값 저장
                                    bundle.putInt("num", result.get(i).num);//번들에 넘길 값 저장
                                    bundle.putString("poster_image", result.get(i).poster_image);//번들에 넘길 값 저장
                                    bundle.putString("explain", "감정 분석 결과, SURPRISE 수치가 높은 것으로 나타나 "
                                            + result.get(i).genre + " 장르의 영화 " + result.get(i).title + "을(를) 추천드립니다.");//번들에 넘길 값 저장


                                    FragmentManager fm = getSupportFragmentManager();
                                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                                    fragment.setArguments(bundle);

                                    fragmentTransaction.replace(R.id.cloud, fragment);
                                    fragmentTransaction.commit();

                                } else {
                                    // 실패
                                    Log.e(TAG, "call onResponse fail");
                                }
                            }

                            @Override
                            public void onFailure(Call<List<PostResultWishlist>> call, Throwable t) {
                                // 통신 실패
                                Log.e(TAG, "call onFailure: " + t.getMessage());
                            }

                        });
                    }
                }
                else if (angerstar.equals("★★★★")) {

                    Random rand = new Random();
                    int j = rand.nextInt(2);

                    if(j == 0) {

                        Call<List<PostResultWishlist>> call = service.getByWishlistGenre("액션");

                        call.enqueue(new Callback<List<PostResultWishlist>>() {
                            @Override
                            public void onResponse(Call<List<PostResultWishlist>> call, Response<List<PostResultWishlist>> response) {
                                Log.e(TAG, "call onResponse");
                                if (response.isSuccessful()) {
                                    Log.e(TAG, "call onResponse success");
                                    List<PostResultWishlist> result = response.body();

                                    Random rand = new Random();
                                    int i = rand.nextInt(result.size());

                                    MovieRecommendActivity fragment = new MovieRecommendActivity();
                                    Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                                    bundle.putString("genre", result.get(i).genre);//번들에 넘길 값 저장
                                    bundle.putString("title", result.get(i).title);//번들에 넘길 값 저장
                                    bundle.putInt("num", result.get(i).num);//번들에 넘길 값 저장
                                    bundle.putString("poster_image", result.get(i).poster_image);//번들에 넘길 값 저장
                                    bundle.putString("explain", "감정 분석 결과, ANGRY 수치가 높은 것으로 나타나 "
                                            + result.get(i).genre + " 장르의 영화 " + result.get(i).title + "을(를) 추천드립니다.");//번들에 넘길 값 저장


                                    FragmentManager fm = getSupportFragmentManager();
                                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                                    fragment.setArguments(bundle);

                                    fragmentTransaction.replace(R.id.cloud, fragment);
                                    fragmentTransaction.commit();

                                } else {
                                    // 실패
                                    Log.e(TAG, "call onResponse fail");
                                }
                            }

                            @Override
                            public void onFailure(Call<List<PostResultWishlist>> call, Throwable t) {
                                // 통신 실패
                                Log.e(TAG, "call onFailure: " + t.getMessage());
                            }

                        });
                    }

                    else if(j == 1) {

                        Call<List<PostResultWishlist>> call = service.getByWishlistGenre("전쟁");

                        call.enqueue(new Callback<List<PostResultWishlist>>() {
                            @Override
                            public void onResponse(Call<List<PostResultWishlist>> call, Response<List<PostResultWishlist>> response) {
                                Log.e(TAG, "call onResponse");
                                if (response.isSuccessful()) {
                                    Log.e(TAG, "call onResponse success");
                                    List<PostResultWishlist> result = response.body();

                                    Random rand = new Random();
                                    int i = rand.nextInt(result.size());

                                    MovieRecommendActivity fragment = new MovieRecommendActivity();
                                    Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                                    bundle.putString("genre", result.get(i).genre);//번들에 넘길 값 저장
                                    bundle.putString("title", result.get(i).title);//번들에 넘길 값 저장
                                    bundle.putInt("num", result.get(i).num);//번들에 넘길 값 저장
                                    bundle.putString("poster_image", result.get(i).poster_image);//번들에 넘길 값 저장
                                    bundle.putString("explain", "감정 분석 결과, ANGRY 수치가 높은 것으로 나타나 "
                                            + result.get(i).genre + " 장르의 영화 " + result.get(i).title + "을(를) 추천드립니다.");//번들에 넘길 값 저장


                                    FragmentManager fm = getSupportFragmentManager();
                                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                                    fragment.setArguments(bundle);

                                    fragmentTransaction.replace(R.id.cloud, fragment);
                                    fragmentTransaction.commit();

                                } else {
                                    // 실패
                                    Log.e(TAG, "call onResponse fail");
                                }
                            }

                            @Override
                            public void onFailure(Call<List<PostResultWishlist>> call, Throwable t) {
                                // 통신 실패
                                Log.e(TAG, "call onFailure: " + t.getMessage());
                            }

                        });
                    }
                }

                else if (joystar.equals("★★★★")) {

                    Random rand = new Random();
                    int j = rand.nextInt(2);

                    if(j == 0) {

                        Call<List<PostResultWishlist>> call = service.getByWishlistGenre("로맨스");

                        call.enqueue(new Callback<List<PostResultWishlist>>() {
                            @Override
                            public void onResponse(Call<List<PostResultWishlist>> call, Response<List<PostResultWishlist>> response) {
                                Log.e(TAG, "call onResponse");
                                if (response.isSuccessful()) {
                                    Log.e(TAG, "call onResponse success");
                                    List<PostResultWishlist> result = response.body();

                                    Random rand = new Random();
                                    int i = rand.nextInt(result.size());

                                    MovieRecommendActivity fragment = new MovieRecommendActivity();
                                    Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                                    bundle.putString("genre", result.get(i).genre);//번들에 넘길 값 저장
                                    bundle.putString("title", result.get(i).title);//번들에 넘길 값 저장
                                    bundle.putInt("num", result.get(i).num);//번들에 넘길 값 저장
                                    bundle.putString("poster_image", result.get(i).poster_image);//번들에 넘길 값 저장
                                    bundle.putString("explain", "감정 분석 결과, JOY 수치가 높은 것으로 나타나 "
                                            + result.get(i).genre + " 장르의 영화 " + result.get(i).title + "을(를) 추천드립니다.");//번들에 넘길 값 저장


                                    FragmentManager fm = getSupportFragmentManager();
                                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                                    fragment.setArguments(bundle);

                                    fragmentTransaction.replace(R.id.cloud, fragment);
                                    fragmentTransaction.commit();

                                } else {
                                    // 실패
                                    Log.e(TAG, "call onResponse fail");
                                }
                            }

                            @Override
                            public void onFailure(Call<List<PostResultWishlist>> call, Throwable t) {
                                // 통신 실패
                                Log.e(TAG, "call onFailure: " + t.getMessage());
                            }

                        });
                    }

                    else if(j == 1) {

                        Call<List<PostResultWishlist>> call = service.getByWishlistGenre("판타지");

                        call.enqueue(new Callback<List<PostResultWishlist>>() {
                            @Override
                            public void onResponse(Call<List<PostResultWishlist>> call, Response<List<PostResultWishlist>> response) {
                                Log.e(TAG, "call onResponse");
                                if (response.isSuccessful()) {
                                    Log.e(TAG, "call onResponse success");
                                    List<PostResultWishlist> result = response.body();

                                    Random rand = new Random();
                                    int i = rand.nextInt(result.size());

                                    MovieRecommendActivity fragment = new MovieRecommendActivity();
                                    Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                                    bundle.putString("genre", result.get(i).genre);//번들에 넘길 값 저장
                                    bundle.putString("title", result.get(i).title);//번들에 넘길 값 저장
                                    bundle.putInt("num", result.get(i).num);//번들에 넘길 값 저장
                                    bundle.putString("poster_image", result.get(i).poster_image);//번들에 넘길 값 저장
                                    bundle.putString("explain", "감정 분석 결과, JOY 수치가 높은 것으로 나타나 "
                                            + result.get(i).genre + " 장르의 영화 " + result.get(i).title + "을(를) 추천드립니다.");//번들에 넘길 값 저장


                                    FragmentManager fm = getSupportFragmentManager();
                                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                                    fragment.setArguments(bundle);

                                    fragmentTransaction.replace(R.id.cloud, fragment);
                                    fragmentTransaction.commit();

                                } else {
                                    // 실패
                                    Log.e(TAG, "call onResponse fail");
                                }
                            }

                            @Override
                            public void onFailure(Call<List<PostResultWishlist>> call, Throwable t) {
                                // 통신 실패
                                Log.e(TAG, "call onFailure: " + t.getMessage());
                            }

                        });
                    }
                }

                else if (sorrowstar.equals("★★★★")) {

                    Random rand = new Random();
                    int j = rand.nextInt(2);

                    if(j == 0) {

                        Call<List<PostResultWishlist>> call = service.getByWishlistGenre("코미디");

                        call.enqueue(new Callback<List<PostResultWishlist>>() {
                            @Override
                            public void onResponse(Call<List<PostResultWishlist>> call, Response<List<PostResultWishlist>> response) {
                                Log.e(TAG, "call onResponse");
                                if (response.isSuccessful()) {
                                    Log.e(TAG, "call onResponse success");
                                    List<PostResultWishlist> result = response.body();

                                    Random rand = new Random();
                                    int i = rand.nextInt(result.size());

                                    MovieRecommendActivity fragment = new MovieRecommendActivity();
                                    Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                                    bundle.putString("genre", result.get(i).genre);//번들에 넘길 값 저장
                                    bundle.putString("title", result.get(i).title);//번들에 넘길 값 저장
                                    bundle.putInt("num", result.get(i).num);//번들에 넘길 값 저장
                                    bundle.putString("poster_image", result.get(i).poster_image);//번들에 넘길 값 저장
                                    bundle.putString("explain", "감정 분석 결과, SORROW 수치가 높은 것으로 나타나 "
                                            + result.get(i).genre + " 장르의 영화 " + result.get(i).title + "을(를) 추천드립니다.");//번들에 넘길 값 저장


                                    FragmentManager fm = getSupportFragmentManager();
                                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                                    fragment.setArguments(bundle);

                                    fragmentTransaction.replace(R.id.cloud, fragment);
                                    fragmentTransaction.commit();

                                } else {
                                    // 실패
                                    Log.e(TAG, "call onResponse fail");
                                }
                            }

                            @Override
                            public void onFailure(Call<List<PostResultWishlist>> call, Throwable t) {
                                // 통신 실패
                                Log.e(TAG, "call onFailure: " + t.getMessage());
                            }

                        });
                    }

                    else if(j == 1) {

                        Call<List<PostResultWishlist>> call = service.getByWishlistGenre("드라마");

                        call.enqueue(new Callback<List<PostResultWishlist>>() {
                            @Override
                            public void onResponse(Call<List<PostResultWishlist>> call, Response<List<PostResultWishlist>> response) {
                                Log.e(TAG, "call onResponse");
                                if (response.isSuccessful()) {
                                    Log.e(TAG, "call onResponse success");
                                    List<PostResultWishlist> result = response.body();

                                    Random rand = new Random();
                                    int i = rand.nextInt(result.size());

                                    MovieRecommendActivity fragment = new MovieRecommendActivity();
                                    Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                                    bundle.putString("genre", result.get(i).genre);//번들에 넘길 값 저장
                                    bundle.putString("title", result.get(i).title);//번들에 넘길 값 저장
                                    bundle.putInt("num", result.get(i).num);//번들에 넘길 값 저장
                                    bundle.putString("poster_image", result.get(i).poster_image);//번들에 넘길 값 저장
                                    bundle.putString("explain", "감정 분석 결과, SORROW 수치가 높은 것으로 나타나 "
                                            + result.get(i).genre + " 장르의 영화 " + result.get(i).title + "을(를) 추천드립니다.");//번들에 넘길 값 저장


                                    FragmentManager fm = getSupportFragmentManager();
                                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                                    fragment.setArguments(bundle);

                                    fragmentTransaction.replace(R.id.cloud, fragment);
                                    fragmentTransaction.commit();

                                } else {
                                    // 실패
                                    Log.e(TAG, "call onResponse fail");
                                }
                            }

                            @Override
                            public void onFailure(Call<List<PostResultWishlist>> call, Throwable t) {
                                // 통신 실패
                                Log.e(TAG, "call onFailure: " + t.getMessage());
                            }

                        });
                    }
                }

                else if (surprisestar.equals("★★★★")) {

                    Random rand = new Random();
                    int j = rand.nextInt(2);

                    if(j == 0) {

                        Call<List<PostResultWishlist>> call = service.getByWishlistGenre("액션");

                        call.enqueue(new Callback<List<PostResultWishlist>>() {
                            @Override
                            public void onResponse(Call<List<PostResultWishlist>> call, Response<List<PostResultWishlist>> response) {
                                Log.e(TAG, "call onResponse");
                                if (response.isSuccessful()) {
                                    Log.e(TAG, "call onResponse success");
                                    List<PostResultWishlist> result = response.body();

                                    Random rand = new Random();
                                    int i = rand.nextInt(result.size());

                                    MovieRecommendActivity fragment = new MovieRecommendActivity();
                                    Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                                    bundle.putString("genre", result.get(i).genre);//번들에 넘길 값 저장
                                    bundle.putString("title", result.get(i).title);//번들에 넘길 값 저장
                                    bundle.putInt("num", result.get(i).num);//번들에 넘길 값 저장
                                    bundle.putString("poster_image", result.get(i).poster_image);//번들에 넘길 값 저장
                                    bundle.putString("explain", "감정 분석 결과, SURPRISE 수치가 높은 것으로 나타나 "
                                            + result.get(i).genre + " 장르의 영화 " + result.get(i).title + "을(를) 추천드립니다.");//번들에 넘길 값 저장


                                    FragmentManager fm = getSupportFragmentManager();
                                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                                    fragment.setArguments(bundle);

                                    fragmentTransaction.replace(R.id.cloud, fragment);
                                    fragmentTransaction.commit();

                                } else {
                                    // 실패
                                    Log.e(TAG, "call onResponse fail");
                                }
                            }

                            @Override
                            public void onFailure(Call<List<PostResultWishlist>> call, Throwable t) {
                                // 통신 실패
                                Log.e(TAG, "call onFailure: " + t.getMessage());
                            }

                        });
                    }

                    else if(j == 1) {

                        Call<List<PostResultWishlist>> call = service.getByWishlistGenre("공상과학");

                        call.enqueue(new Callback<List<PostResultWishlist>>() {
                            @Override
                            public void onResponse(Call<List<PostResultWishlist>> call, Response<List<PostResultWishlist>> response) {
                                Log.e(TAG, "call onResponse");
                                if (response.isSuccessful()) {
                                    Log.e(TAG, "call onResponse success");
                                    List<PostResultWishlist> result = response.body();

                                    Random rand = new Random();
                                    int i = rand.nextInt(result.size());

                                    MovieRecommendActivity fragment = new MovieRecommendActivity();
                                    Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                                    bundle.putString("genre", result.get(i).genre);//번들에 넘길 값 저장
                                    bundle.putString("title", result.get(i).title);//번들에 넘길 값 저장
                                    bundle.putInt("num", result.get(i).num);//번들에 넘길 값 저장
                                    bundle.putString("poster_image", result.get(i).poster_image);//번들에 넘길 값 저장
                                    bundle.putString("explain", "감정 분석 결과, SURPRISE 수치가 높은 것으로 나타나 "
                                            + result.get(i).genre + " 장르의 영화 " + result.get(i).title + "을(를) 추천드립니다.");//번들에 넘길 값 저장


                                    FragmentManager fm = getSupportFragmentManager();
                                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                                    fragment.setArguments(bundle);

                                    fragmentTransaction.replace(R.id.cloud, fragment);
                                    fragmentTransaction.commit();

                                } else {
                                    // 실패
                                    Log.e(TAG, "call onResponse fail");
                                }
                            }

                            @Override
                            public void onFailure(Call<List<PostResultWishlist>> call, Throwable t) {
                                // 통신 실패
                                Log.e(TAG, "call onFailure: " + t.getMessage());
                            }

                        });
                    }
                }

                else if (angerstar.equals("★★★")) {

                    Random rand = new Random();
                    int j = rand.nextInt(2);

                    if(j == 0) {

                        Call<List<PostResultWishlist>> call = service.getByWishlistGenre("액션");

                        call.enqueue(new Callback<List<PostResultWishlist>>() {
                            @Override
                            public void onResponse(Call<List<PostResultWishlist>> call, Response<List<PostResultWishlist>> response) {
                                Log.e(TAG, "call onResponse");
                                if (response.isSuccessful()) {
                                    Log.e(TAG, "call onResponse success");
                                    List<PostResultWishlist> result = response.body();

                                    Random rand = new Random();
                                    int i = rand.nextInt(result.size());

                                    MovieRecommendActivity fragment = new MovieRecommendActivity();
                                    Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                                    bundle.putString("genre", result.get(i).genre);//번들에 넘길 값 저장
                                    bundle.putString("title", result.get(i).title);//번들에 넘길 값 저장
                                    bundle.putInt("num", result.get(i).num);//번들에 넘길 값 저장
                                    bundle.putString("poster_image", result.get(i).poster_image);//번들에 넘길 값 저장
                                    bundle.putString("explain", "감정 분석 결과, ANGRY 수치가 높은 것으로 나타나 "
                                            + result.get(i).genre + " 장르의 영화 " + result.get(i).title + "을(를) 추천드립니다.");//번들에 넘길 값 저장


                                    FragmentManager fm = getSupportFragmentManager();
                                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                                    fragment.setArguments(bundle);

                                    fragmentTransaction.replace(R.id.cloud, fragment);
                                    fragmentTransaction.commit();

                                } else {
                                    // 실패
                                    Log.e(TAG, "call onResponse fail");
                                }
                            }

                            @Override
                            public void onFailure(Call<List<PostResultWishlist>> call, Throwable t) {
                                // 통신 실패
                                Log.e(TAG, "call onFailure: " + t.getMessage());
                            }

                        });
                    }

                    else if(j == 1) {

                        Call<List<PostResultWishlist>> call = service.getByWishlistGenre("전쟁");

                        call.enqueue(new Callback<List<PostResultWishlist>>() {
                            @Override
                            public void onResponse(Call<List<PostResultWishlist>> call, Response<List<PostResultWishlist>> response) {
                                Log.e(TAG, "call onResponse");
                                if (response.isSuccessful()) {
                                    Log.e(TAG, "call onResponse success");
                                    List<PostResultWishlist> result = response.body();

                                    Random rand = new Random();
                                    int i = rand.nextInt(result.size());

                                    MovieRecommendActivity fragment = new MovieRecommendActivity();
                                    Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                                    bundle.putString("genre", result.get(i).genre);//번들에 넘길 값 저장
                                    bundle.putString("title", result.get(i).title);//번들에 넘길 값 저장
                                    bundle.putInt("num", result.get(i).num);//번들에 넘길 값 저장
                                    bundle.putString("poster_image", result.get(i).poster_image);//번들에 넘길 값 저장
                                    bundle.putString("explain", "감정 분석 결과, ANGRY 수치가 높은 것으로 나타나 "
                                            + result.get(i).genre + " 장르의 영화 " + result.get(i).title + "을(를) 추천드립니다.");//번들에 넘길 값 저장


                                    FragmentManager fm = getSupportFragmentManager();
                                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                                    fragment.setArguments(bundle);

                                    fragmentTransaction.replace(R.id.cloud, fragment);
                                    fragmentTransaction.commit();

                                } else {
                                    // 실패
                                    Log.e(TAG, "call onResponse fail");
                                }
                            }

                            @Override
                            public void onFailure(Call<List<PostResultWishlist>> call, Throwable t) {
                                // 통신 실패
                                Log.e(TAG, "call onFailure: " + t.getMessage());
                            }

                        });
                    }
                }

                else if (joystar.equals("★★★")) {

                    Random rand = new Random();
                    int j = rand.nextInt(2);

                    if(j == 0) {

                        Call<List<PostResultWishlist>> call = service.getByWishlistGenre("로맨스");

                        call.enqueue(new Callback<List<PostResultWishlist>>() {
                            @Override
                            public void onResponse(Call<List<PostResultWishlist>> call, Response<List<PostResultWishlist>> response) {
                                Log.e(TAG, "call onResponse");
                                if (response.isSuccessful()) {
                                    Log.e(TAG, "call onResponse success");
                                    List<PostResultWishlist> result = response.body();

                                    Random rand = new Random();
                                    int i = rand.nextInt(result.size());

                                    MovieRecommendActivity fragment = new MovieRecommendActivity();
                                    Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                                    bundle.putString("genre", result.get(i).genre);//번들에 넘길 값 저장
                                    bundle.putString("title", result.get(i).title);//번들에 넘길 값 저장
                                    bundle.putInt("num", result.get(i).num);//번들에 넘길 값 저장
                                    bundle.putString("poster_image", result.get(i).poster_image);//번들에 넘길 값 저장
                                    bundle.putString("explain", "감정 분석 결과, JOY 수치가 높은 것으로 나타나 "
                                            + result.get(i).genre + " 장르의 영화 " + result.get(i).title + "을(를) 추천드립니다.");//번들에 넘길 값 저장


                                    FragmentManager fm = getSupportFragmentManager();
                                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                                    fragment.setArguments(bundle);

                                    fragmentTransaction.replace(R.id.cloud, fragment);
                                    fragmentTransaction.commit();

                                } else {
                                    // 실패
                                    Log.e(TAG, "call onResponse fail");
                                }
                            }

                            @Override
                            public void onFailure(Call<List<PostResultWishlist>> call, Throwable t) {
                                // 통신 실패
                                Log.e(TAG, "call onFailure: " + t.getMessage());
                            }

                        });
                    }

                    else if(j == 1) {

                        Call<List<PostResultWishlist>> call = service.getByWishlistGenre("판타지");

                        call.enqueue(new Callback<List<PostResultWishlist>>() {
                            @Override
                            public void onResponse(Call<List<PostResultWishlist>> call, Response<List<PostResultWishlist>> response) {
                                Log.e(TAG, "call onResponse");
                                if (response.isSuccessful()) {
                                    Log.e(TAG, "call onResponse success");
                                    List<PostResultWishlist> result = response.body();

                                    Random rand = new Random();
                                    int i = rand.nextInt(result.size());

                                    MovieRecommendActivity fragment = new MovieRecommendActivity();
                                    Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                                    bundle.putString("genre", result.get(i).genre);//번들에 넘길 값 저장
                                    bundle.putString("title", result.get(i).title);//번들에 넘길 값 저장
                                    bundle.putInt("num", result.get(i).num);//번들에 넘길 값 저장
                                    bundle.putString("poster_image", result.get(i).poster_image);//번들에 넘길 값 저장
                                    bundle.putString("explain", "감정 분석 결과, JOY 수치가 높은 것으로 나타나 "
                                            + result.get(i).genre + " 장르의 영화 " + result.get(i).title + "을(를) 추천드립니다.");//번들에 넘길 값 저장


                                    FragmentManager fm = getSupportFragmentManager();
                                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                                    fragment.setArguments(bundle);

                                    fragmentTransaction.replace(R.id.cloud, fragment);
                                    fragmentTransaction.commit();

                                } else {
                                    // 실패
                                    Log.e(TAG, "call onResponse fail");
                                }
                            }

                            @Override
                            public void onFailure(Call<List<PostResultWishlist>> call, Throwable t) {
                                // 통신 실패
                                Log.e(TAG, "call onFailure: " + t.getMessage());
                            }

                        });
                    }
                }

                else if (sorrowstar.equals("★★★")) {

                    Random rand = new Random();
                    int j = rand.nextInt(2);

                    if(j == 0) {

                        Call<List<PostResultWishlist>> call = service.getByWishlistGenre("코미디");

                        call.enqueue(new Callback<List<PostResultWishlist>>() {
                            @Override
                            public void onResponse(Call<List<PostResultWishlist>> call, Response<List<PostResultWishlist>> response) {
                                Log.e(TAG, "call onResponse");
                                if (response.isSuccessful()) {
                                    Log.e(TAG, "call onResponse success");
                                    List<PostResultWishlist> result = response.body();

                                    Random rand = new Random();
                                    int i = rand.nextInt(result.size());

                                    MovieRecommendActivity fragment = new MovieRecommendActivity();
                                    Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                                    bundle.putString("genre", result.get(i).genre);//번들에 넘길 값 저장
                                    bundle.putString("title", result.get(i).title);//번들에 넘길 값 저장
                                    bundle.putInt("num", result.get(i).num);//번들에 넘길 값 저장
                                    bundle.putString("poster_image", result.get(i).poster_image);//번들에 넘길 값 저장
                                    bundle.putString("explain", "감정 분석 결과, SORROW 수치가 높은 것으로 나타나 "
                                            + result.get(i).genre + " 장르의 영화 " + result.get(i).title + "을(를) 추천드립니다.");//번들에 넘길 값 저장


                                    FragmentManager fm = getSupportFragmentManager();
                                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                                    fragment.setArguments(bundle);

                                    fragmentTransaction.replace(R.id.cloud, fragment);
                                    fragmentTransaction.commit();

                                } else {
                                    // 실패
                                    Log.e(TAG, "call onResponse fail");
                                }
                            }

                            @Override
                            public void onFailure(Call<List<PostResultWishlist>> call, Throwable t) {
                                // 통신 실패
                                Log.e(TAG, "call onFailure: " + t.getMessage());
                            }

                        });
                    }

                    else if(j == 1) {

                        Call<List<PostResultWishlist>> call = service.getByWishlistGenre("드라마");

                        call.enqueue(new Callback<List<PostResultWishlist>>() {
                            @Override
                            public void onResponse(Call<List<PostResultWishlist>> call, Response<List<PostResultWishlist>> response) {
                                Log.e(TAG, "call onResponse");
                                if (response.isSuccessful()) {
                                    Log.e(TAG, "call onResponse success");
                                    List<PostResultWishlist> result = response.body();

                                    Random rand = new Random();
                                    int i = rand.nextInt(result.size());

                                    MovieRecommendActivity fragment = new MovieRecommendActivity();
                                    Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                                    bundle.putString("genre", result.get(i).genre);//번들에 넘길 값 저장
                                    bundle.putString("title", result.get(i).title);//번들에 넘길 값 저장
                                    bundle.putInt("num", result.get(i).num);//번들에 넘길 값 저장
                                    bundle.putString("poster_image", result.get(i).poster_image);//번들에 넘길 값 저장
                                    bundle.putString("explain", "감정 분석 결과, SORROW 수치가 높은 것으로 나타나 "
                                            + result.get(i).genre + " 장르의 영화 " + result.get(i).title + "을(를) 추천드립니다.");//번들에 넘길 값 저장


                                    FragmentManager fm = getSupportFragmentManager();
                                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                                    fragment.setArguments(bundle);

                                    fragmentTransaction.replace(R.id.cloud, fragment);
                                    fragmentTransaction.commit();

                                } else {
                                    // 실패
                                    Log.e(TAG, "call onResponse fail");
                                }
                            }

                            @Override
                            public void onFailure(Call<List<PostResultWishlist>> call, Throwable t) {
                                // 통신 실패
                                Log.e(TAG, "call onFailure: " + t.getMessage());
                            }

                        });
                    }
                }

                else if (surprisestar.equals("★★★")) {

                    Random rand = new Random();
                    int j = rand.nextInt(2);

                    if(j == 0) {

                        Call<List<PostResultWishlist>> call = service.getByWishlistGenre("액션");

                        call.enqueue(new Callback<List<PostResultWishlist>>() {
                            @Override
                            public void onResponse(Call<List<PostResultWishlist>> call, Response<List<PostResultWishlist>> response) {
                                Log.e(TAG, "call onResponse");
                                if (response.isSuccessful()) {
                                    Log.e(TAG, "call onResponse success");
                                    List<PostResultWishlist> result = response.body();

                                    Random rand = new Random();
                                    int i = rand.nextInt(result.size());

                                    MovieRecommendActivity fragment = new MovieRecommendActivity();
                                    Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                                    bundle.putString("genre", result.get(i).genre);//번들에 넘길 값 저장
                                    bundle.putString("title", result.get(i).title);//번들에 넘길 값 저장
                                    bundle.putInt("num", result.get(i).num);//번들에 넘길 값 저장
                                    bundle.putString("poster_image", result.get(i).poster_image);//번들에 넘길 값 저장
                                    bundle.putString("explain", "감정 분석 결과, SURPRISE 수치가 높은 것으로 나타나 "
                                            + result.get(i).genre + " 장르의 영화 " + result.get(i).title + "을(를) 추천드립니다.");//번들에 넘길 값 저장


                                    FragmentManager fm = getSupportFragmentManager();
                                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                                    fragment.setArguments(bundle);

                                    fragmentTransaction.replace(R.id.cloud, fragment);
                                    fragmentTransaction.commit();

                                } else {
                                    // 실패
                                    Log.e(TAG, "call onResponse fail");
                                }
                            }

                            @Override
                            public void onFailure(Call<List<PostResultWishlist>> call, Throwable t) {
                                // 통신 실패
                                Log.e(TAG, "call onFailure: " + t.getMessage());
                            }

                        });
                    }

                    else if(j == 1) {

                        Call<List<PostResultWishlist>> call = service.getByWishlistGenre("공상과학");

                        call.enqueue(new Callback<List<PostResultWishlist>>() {
                            @Override
                            public void onResponse(Call<List<PostResultWishlist>> call, Response<List<PostResultWishlist>> response) {
                                Log.e(TAG, "call onResponse");
                                if (response.isSuccessful()) {
                                    Log.e(TAG, "call onResponse success");
                                    List<PostResultWishlist> result = response.body();

                                    Random rand = new Random();
                                    int i = rand.nextInt(result.size());

                                    MovieRecommendActivity fragment = new MovieRecommendActivity();
                                    Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                                    bundle.putString("genre", result.get(i).genre);//번들에 넘길 값 저장
                                    bundle.putString("title", result.get(i).title);//번들에 넘길 값 저장
                                    bundle.putInt("num", result.get(i).num);//번들에 넘길 값 저장
                                    bundle.putString("poster_image", result.get(i).poster_image);//번들에 넘길 값 저장
                                    bundle.putString("explain", "감정 분석 결과, SURPRISE 수치가 높은 것으로 나타나 "
                                            + result.get(i).genre + " 장르의 영화 " + result.get(i).title + "을(를) 추천드립니다.");//번들에 넘길 값 저장


                                    FragmentManager fm = getSupportFragmentManager();
                                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                                    fragment.setArguments(bundle);

                                    fragmentTransaction.replace(R.id.cloud, fragment);
                                    fragmentTransaction.commit();

                                } else {
                                    // 실패
                                    Log.e(TAG, "call onResponse fail");
                                }
                            }

                            @Override
                            public void onFailure(Call<List<PostResultWishlist>> call, Throwable t) {
                                // 통신 실패
                                Log.e(TAG, "call onFailure: " + t.getMessage());
                            }

                        });
                    }
                }

                else if (angerstar.equals("★★")) {

                    Random rand = new Random();
                    int j = rand.nextInt(2);

                    if(j == 0) {

                        Call<List<PostResultWishlist>> call = service.getByWishlistGenre("액션");

                        call.enqueue(new Callback<List<PostResultWishlist>>() {
                            @Override
                            public void onResponse(Call<List<PostResultWishlist>> call, Response<List<PostResultWishlist>> response) {
                                Log.e(TAG, "call onResponse");
                                if (response.isSuccessful()) {
                                    Log.e(TAG, "call onResponse success");
                                    List<PostResultWishlist> result = response.body();

                                    Random rand = new Random();
                                    int i = rand.nextInt(result.size());

                                    MovieRecommendActivity fragment = new MovieRecommendActivity();
                                    Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                                    bundle.putString("genre", result.get(i).genre);//번들에 넘길 값 저장
                                    bundle.putString("title", result.get(i).title);//번들에 넘길 값 저장
                                    bundle.putInt("num", result.get(i).num);//번들에 넘길 값 저장
                                    bundle.putString("poster_image", result.get(i).poster_image);//번들에 넘길 값 저장
                                    bundle.putString("explain", "감정 분석 결과, ANGRY 수치가 높은 것으로 나타나 "
                                            + result.get(i).genre + " 장르의 영화 " + result.get(i).title + "을(를) 추천드립니다.");//번들에 넘길 값 저장


                                    FragmentManager fm = getSupportFragmentManager();
                                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                                    fragment.setArguments(bundle);

                                    fragmentTransaction.replace(R.id.cloud, fragment);
                                    fragmentTransaction.commit();

                                } else {
                                    // 실패
                                    Log.e(TAG, "call onResponse fail");
                                }
                            }

                            @Override
                            public void onFailure(Call<List<PostResultWishlist>> call, Throwable t) {
                                // 통신 실패
                                Log.e(TAG, "call onFailure: " + t.getMessage());
                            }

                        });
                    }

                    else if(j == 1) {

                        Call<List<PostResultWishlist>> call = service.getByWishlistGenre("전쟁");

                        call.enqueue(new Callback<List<PostResultWishlist>>() {
                            @Override
                            public void onResponse(Call<List<PostResultWishlist>> call, Response<List<PostResultWishlist>> response) {
                                Log.e(TAG, "call onResponse");
                                if (response.isSuccessful()) {
                                    Log.e(TAG, "call onResponse success");
                                    List<PostResultWishlist> result = response.body();

                                    Random rand = new Random();
                                    int i = rand.nextInt(result.size());

                                    MovieRecommendActivity fragment = new MovieRecommendActivity();
                                    Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                                    bundle.putString("genre", result.get(i).genre);//번들에 넘길 값 저장
                                    bundle.putString("title", result.get(i).title);//번들에 넘길 값 저장
                                    bundle.putInt("num", result.get(i).num);//번들에 넘길 값 저장
                                    bundle.putString("poster_image", result.get(i).poster_image);//번들에 넘길 값 저장
                                    bundle.putString("explain", "감정 분석 결과, ANGRY 수치가 높은 것으로 나타나 "
                                            + result.get(i).genre + " 장르의 영화 " + result.get(i).title + "을(를) 추천드립니다.");//번들에 넘길 값 저장


                                    FragmentManager fm = getSupportFragmentManager();
                                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                                    fragment.setArguments(bundle);

                                    fragmentTransaction.replace(R.id.cloud, fragment);
                                    fragmentTransaction.commit();

                                } else {
                                    // 실패
                                    Log.e(TAG, "call onResponse fail");
                                }
                            }

                            @Override
                            public void onFailure(Call<List<PostResultWishlist>> call, Throwable t) {
                                // 통신 실패
                                Log.e(TAG, "call onFailure: " + t.getMessage());
                            }

                        });
                    }
                }

                else if (joystar.equals("★★")) {

                    Random rand = new Random();
                    int j = rand.nextInt(2);

                    if(j == 0) {

                        Call<List<PostResultWishlist>> call = service.getByWishlistGenre("로맨스");

                        call.enqueue(new Callback<List<PostResultWishlist>>() {
                            @Override
                            public void onResponse(Call<List<PostResultWishlist>> call, Response<List<PostResultWishlist>> response) {
                                Log.e(TAG, "call onResponse");
                                if (response.isSuccessful()) {
                                    Log.e(TAG, "call onResponse success");
                                    List<PostResultWishlist> result = response.body();

                                    Random rand = new Random();
                                    int i = rand.nextInt(result.size());

                                    MovieRecommendActivity fragment = new MovieRecommendActivity();
                                    Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                                    bundle.putString("genre", result.get(i).genre);//번들에 넘길 값 저장
                                    bundle.putString("title", result.get(i).title);//번들에 넘길 값 저장
                                    bundle.putInt("num", result.get(i).num);//번들에 넘길 값 저장
                                    bundle.putString("poster_image", result.get(i).poster_image);//번들에 넘길 값 저장
                                    bundle.putString("explain", "감정 분석 결과, JOY 수치가 높은 것으로 나타나 "
                                            + result.get(i).genre + " 장르의 영화 " + result.get(i).title + "을(를) 추천드립니다.");//번들에 넘길 값 저장


                                    FragmentManager fm = getSupportFragmentManager();
                                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                                    fragment.setArguments(bundle);

                                    fragmentTransaction.replace(R.id.cloud, fragment);
                                    fragmentTransaction.commit();

                                } else {
                                    // 실패
                                    Log.e(TAG, "call onResponse fail");
                                }
                            }

                            @Override
                            public void onFailure(Call<List<PostResultWishlist>> call, Throwable t) {
                                // 통신 실패
                                Log.e(TAG, "call onFailure: " + t.getMessage());
                            }

                        });
                    }

                    else if(j == 1) {

                        Call<List<PostResultWishlist>> call = service.getByWishlistGenre("판타지");

                        call.enqueue(new Callback<List<PostResultWishlist>>() {
                            @Override
                            public void onResponse(Call<List<PostResultWishlist>> call, Response<List<PostResultWishlist>> response) {
                                Log.e(TAG, "call onResponse");
                                if (response.isSuccessful()) {
                                    Log.e(TAG, "call onResponse success");
                                    List<PostResultWishlist> result = response.body();

                                    Random rand = new Random();
                                    int i = rand.nextInt(result.size());

                                    MovieRecommendActivity fragment = new MovieRecommendActivity();
                                    Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                                    bundle.putString("genre", result.get(i).genre);//번들에 넘길 값 저장
                                    bundle.putString("title", result.get(i).title);//번들에 넘길 값 저장
                                    bundle.putInt("num", result.get(i).num);//번들에 넘길 값 저장
                                    bundle.putString("poster_image", result.get(i).poster_image);//번들에 넘길 값 저장
                                    bundle.putString("explain", "감정 분석 결과, JOY 수치가 높은 것으로 나타나 "
                                            + result.get(i).genre + " 장르의 영화 " + result.get(i).title + "을(를) 추천드립니다.");//번들에 넘길 값 저장


                                    FragmentManager fm = getSupportFragmentManager();
                                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                                    fragment.setArguments(bundle);

                                    fragmentTransaction.replace(R.id.cloud, fragment);
                                    fragmentTransaction.commit();

                                } else {
                                    // 실패
                                    Log.e(TAG, "call onResponse fail");
                                }
                            }

                            @Override
                            public void onFailure(Call<List<PostResultWishlist>> call, Throwable t) {
                                // 통신 실패
                                Log.e(TAG, "call onFailure: " + t.getMessage());
                            }

                        });
                    }
                }

                else if (sorrowstar.equals("★★")) {

                    Random rand = new Random();
                    int j = rand.nextInt(2);

                    if(j == 0) {

                        Call<List<PostResultWishlist>> call = service.getByWishlistGenre("코미디");

                        call.enqueue(new Callback<List<PostResultWishlist>>() {
                            @Override
                            public void onResponse(Call<List<PostResultWishlist>> call, Response<List<PostResultWishlist>> response) {
                                Log.e(TAG, "call onResponse");
                                if (response.isSuccessful()) {
                                    Log.e(TAG, "call onResponse success");
                                    List<PostResultWishlist> result = response.body();

                                    Random rand = new Random();
                                    int i = rand.nextInt(result.size());

                                    MovieRecommendActivity fragment = new MovieRecommendActivity();
                                    Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                                    bundle.putString("genre", result.get(i).genre);//번들에 넘길 값 저장
                                    bundle.putString("title", result.get(i).title);//번들에 넘길 값 저장
                                    bundle.putInt("num", result.get(i).num);//번들에 넘길 값 저장
                                    bundle.putString("poster_image", result.get(i).poster_image);//번들에 넘길 값 저장
                                    bundle.putString("explain", "감정 분석 결과, SORROW 수치가 높은 것으로 나타나 "
                                            + result.get(i).genre + " 장르의 영화 " + result.get(i).title + "을(를) 추천드립니다.");//번들에 넘길 값 저장


                                    FragmentManager fm = getSupportFragmentManager();
                                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                                    fragment.setArguments(bundle);

                                    fragmentTransaction.replace(R.id.cloud, fragment);
                                    fragmentTransaction.commit();

                                } else {
                                    // 실패
                                    Log.e(TAG, "call onResponse fail");
                                }
                            }

                            @Override
                            public void onFailure(Call<List<PostResultWishlist>> call, Throwable t) {
                                // 통신 실패
                                Log.e(TAG, "call onFailure: " + t.getMessage());
                            }

                        });
                    }

                    else if(j == 1) {

                        Call<List<PostResultWishlist>> call = service.getByWishlistGenre("드라마");

                        call.enqueue(new Callback<List<PostResultWishlist>>() {
                            @Override
                            public void onResponse(Call<List<PostResultWishlist>> call, Response<List<PostResultWishlist>> response) {
                                Log.e(TAG, "call onResponse");
                                if (response.isSuccessful()) {
                                    Log.e(TAG, "call onResponse success");
                                    List<PostResultWishlist> result = response.body();

                                    Random rand = new Random();
                                    int i = rand.nextInt(result.size());

                                    MovieRecommendActivity fragment = new MovieRecommendActivity();
                                    Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                                    bundle.putString("genre", result.get(i).genre);//번들에 넘길 값 저장
                                    bundle.putString("title", result.get(i).title);//번들에 넘길 값 저장
                                    bundle.putInt("num", result.get(i).num);//번들에 넘길 값 저장
                                    bundle.putString("poster_image", result.get(i).poster_image);//번들에 넘길 값 저장
                                    bundle.putString("explain", "감정 분석 결과, SORROW 수치가 높은 것으로 나타나 "
                                            + result.get(i).genre + " 장르의 영화 " + result.get(i).title + "을(를) 추천드립니다.");//번들에 넘길 값 저장


                                    FragmentManager fm = getSupportFragmentManager();
                                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                                    fragment.setArguments(bundle);

                                    fragmentTransaction.replace(R.id.cloud, fragment);
                                    fragmentTransaction.commit();

                                } else {
                                    // 실패
                                    Log.e(TAG, "call onResponse fail");
                                }
                            }

                            @Override
                            public void onFailure(Call<List<PostResultWishlist>> call, Throwable t) {
                                // 통신 실패
                                Log.e(TAG, "call onFailure: " + t.getMessage());
                            }

                        });
                    }
                }

                else if (surprisestar.equals("★★")) {

                    Random rand = new Random();
                    int j = rand.nextInt(2);

                    if(j == 0) {

                        Call<List<PostResultWishlist>> call = service.getByWishlistGenre("액션");

                        call.enqueue(new Callback<List<PostResultWishlist>>() {
                            @Override
                            public void onResponse(Call<List<PostResultWishlist>> call, Response<List<PostResultWishlist>> response) {
                                Log.e(TAG, "call onResponse");
                                if (response.isSuccessful()) {
                                    Log.e(TAG, "call onResponse success");
                                    List<PostResultWishlist> result = response.body();

                                    Random rand = new Random();
                                    int i = rand.nextInt(result.size());

                                    MovieRecommendActivity fragment = new MovieRecommendActivity();
                                    Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                                    bundle.putString("genre", result.get(i).genre);//번들에 넘길 값 저장
                                    bundle.putString("title", result.get(i).title);//번들에 넘길 값 저장
                                    bundle.putInt("num", result.get(i).num);//번들에 넘길 값 저장
                                    bundle.putString("poster_image", result.get(i).poster_image);//번들에 넘길 값 저장
                                    bundle.putString("explain", "감정 분석 결과, SURPRISE 수치가 높은 것으로 나타나 "
                                            + result.get(i).genre + " 장르의 영화 " + result.get(i).title + "을(를) 추천드립니다.");//번들에 넘길 값 저장


                                    FragmentManager fm = getSupportFragmentManager();
                                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                                    fragment.setArguments(bundle);

                                    fragmentTransaction.replace(R.id.cloud, fragment);
                                    fragmentTransaction.commit();

                                } else {
                                    // 실패
                                    Log.e(TAG, "call onResponse fail");
                                }
                            }

                            @Override
                            public void onFailure(Call<List<PostResultWishlist>> call, Throwable t) {
                                // 통신 실패
                                Log.e(TAG, "call onFailure: " + t.getMessage());
                            }

                        });
                    }

                    else if(j == 1) {

                        Call<List<PostResultWishlist>> call = service.getByWishlistGenre("공상과학");

                        call.enqueue(new Callback<List<PostResultWishlist>>() {
                            @Override
                            public void onResponse(Call<List<PostResultWishlist>> call, Response<List<PostResultWishlist>> response) {
                                Log.e(TAG, "call onResponse");
                                if (response.isSuccessful()) {
                                    Log.e(TAG, "call onResponse success");
                                    List<PostResultWishlist> result = response.body();

                                    Random rand = new Random();
                                    int i = rand.nextInt(result.size());

                                    MovieRecommendActivity fragment = new MovieRecommendActivity();
                                    Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                                    bundle.putString("genre", result.get(i).genre);//번들에 넘길 값 저장
                                    bundle.putString("title", result.get(i).title);//번들에 넘길 값 저장
                                    bundle.putInt("num", result.get(i).num);//번들에 넘길 값 저장
                                    bundle.putString("poster_image", result.get(i).poster_image);//번들에 넘길 값 저장
                                    bundle.putString("explain", "감정 분석 결과, SURPRISE 수치가 높은 것으로 나타나 "
                                            + result.get(i).genre + " 장르의 영화 " + result.get(i).title + "을(를) 추천드립니다.");//번들에 넘길 값 저장


                                    FragmentManager fm = getSupportFragmentManager();
                                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                                    fragment.setArguments(bundle);

                                    fragmentTransaction.replace(R.id.cloud, fragment);
                                    fragmentTransaction.commit();

                                } else {
                                    // 실패
                                    Log.e(TAG, "call onResponse fail");
                                }
                            }

                            @Override
                            public void onFailure(Call<List<PostResultWishlist>> call, Throwable t) {
                                // 통신 실패
                                Log.e(TAG, "call onFailure: " + t.getMessage());
                            }

                        });
                    }
                }

                else if (angerstar.equals("★")) {

                    Random rand = new Random();
                    int j = rand.nextInt(2);

                    if(j == 0) {

                        Call<List<PostResultWishlist>> call = service.getByWishlistGenre("액션");

                        call.enqueue(new Callback<List<PostResultWishlist>>() {
                            @Override
                            public void onResponse(Call<List<PostResultWishlist>> call, Response<List<PostResultWishlist>> response) {
                                Log.e(TAG, "call onResponse");
                                if (response.isSuccessful()) {
                                    Log.e(TAG, "call onResponse success");
                                    List<PostResultWishlist> result = response.body();

                                    Random rand = new Random();
                                    int i = rand.nextInt(result.size());

                                    MovieRecommendActivity fragment = new MovieRecommendActivity();
                                    Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                                    bundle.putString("genre", result.get(i).genre);//번들에 넘길 값 저장
                                    bundle.putString("title", result.get(i).title);//번들에 넘길 값 저장
                                    bundle.putInt("num", result.get(i).num);//번들에 넘길 값 저장
                                    bundle.putString("poster_image", result.get(i).poster_image);//번들에 넘길 값 저장
                                    bundle.putString("explain", "감정 분석 결과, ANGRY 수치가 높은 것으로 나타나 "
                                            + result.get(i).genre + " 장르의 영화 " + result.get(i).title + "을(를) 추천드립니다.");//번들에 넘길 값 저장


                                    FragmentManager fm = getSupportFragmentManager();
                                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                                    fragment.setArguments(bundle);

                                    fragmentTransaction.replace(R.id.cloud, fragment);
                                    fragmentTransaction.commit();

                                } else {
                                    // 실패
                                    Log.e(TAG, "call onResponse fail");
                                }
                            }

                            @Override
                            public void onFailure(Call<List<PostResultWishlist>> call, Throwable t) {
                                // 통신 실패
                                Log.e(TAG, "call onFailure: " + t.getMessage());
                            }

                        });
                    }

                    else if(j == 1) {

                        Call<List<PostResultWishlist>> call = service.getByWishlistGenre("전쟁");

                        call.enqueue(new Callback<List<PostResultWishlist>>() {
                            @Override
                            public void onResponse(Call<List<PostResultWishlist>> call, Response<List<PostResultWishlist>> response) {
                                Log.e(TAG, "call onResponse");
                                if (response.isSuccessful()) {
                                    Log.e(TAG, "call onResponse success");
                                    List<PostResultWishlist> result = response.body();

                                    Random rand = new Random();
                                    int i = rand.nextInt(result.size());

                                    MovieRecommendActivity fragment = new MovieRecommendActivity();
                                    Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                                    bundle.putString("genre", result.get(i).genre);//번들에 넘길 값 저장
                                    bundle.putString("title", result.get(i).title);//번들에 넘길 값 저장
                                    bundle.putInt("num", result.get(i).num);//번들에 넘길 값 저장
                                    bundle.putString("poster_image", result.get(i).poster_image);//번들에 넘길 값 저장
                                    bundle.putString("explain", "감정 분석 결과, ANGRY 수치가 높은 것으로 나타나 "
                                            + result.get(i).genre + " 장르의 영화 " + result.get(i).title + "을(를) 추천드립니다.");//번들에 넘길 값 저장


                                    FragmentManager fm = getSupportFragmentManager();
                                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                                    fragment.setArguments(bundle);

                                    fragmentTransaction.replace(R.id.cloud, fragment);
                                    fragmentTransaction.commit();

                                } else {
                                    // 실패
                                    Log.e(TAG, "call onResponse fail");
                                }
                            }

                            @Override
                            public void onFailure(Call<List<PostResultWishlist>> call, Throwable t) {
                                // 통신 실패
                                Log.e(TAG, "call onFailure: " + t.getMessage());
                            }

                        });
                    }
                }

                else if (joystar.equals("★")) {

                    Random rand = new Random();
                    int j = rand.nextInt(2);

                    if(j == 0) {

                        Call<List<PostResultWishlist>> call = service.getByWishlistGenre("로맨스");

                        call.enqueue(new Callback<List<PostResultWishlist>>() {
                            @Override
                            public void onResponse(Call<List<PostResultWishlist>> call, Response<List<PostResultWishlist>> response) {
                                Log.e(TAG, "call onResponse");
                                if (response.isSuccessful()) {
                                    Log.e(TAG, "call onResponse success");
                                    List<PostResultWishlist> result = response.body();

                                    Random rand = new Random();
                                    int i = rand.nextInt(result.size());

                                    MovieRecommendActivity fragment = new MovieRecommendActivity();
                                    Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                                    bundle.putString("genre", result.get(i).genre);//번들에 넘길 값 저장
                                    bundle.putString("title", result.get(i).title);//번들에 넘길 값 저장
                                    bundle.putInt("num", result.get(i).num);//번들에 넘길 값 저장
                                    bundle.putString("poster_image", result.get(i).poster_image);//번들에 넘길 값 저장
                                    bundle.putString("explain", "감정 분석 결과, JOY 수치가 높은 것으로 나타나 "
                                            + result.get(i).genre + " 장르의 영화 " + result.get(i).title + "을(를) 추천드립니다.");//번들에 넘길 값 저장


                                    FragmentManager fm = getSupportFragmentManager();
                                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                                    fragment.setArguments(bundle);

                                    fragmentTransaction.replace(R.id.cloud, fragment);
                                    fragmentTransaction.commit();

                                } else {
                                    // 실패
                                    Log.e(TAG, "call onResponse fail");
                                }
                            }

                            @Override
                            public void onFailure(Call<List<PostResultWishlist>> call, Throwable t) {
                                // 통신 실패
                                Log.e(TAG, "call onFailure: " + t.getMessage());
                            }

                        });
                    }

                    else if(j == 1) {

                        Call<List<PostResultWishlist>> call = service.getByWishlistGenre("판타지");

                        call.enqueue(new Callback<List<PostResultWishlist>>() {
                            @Override
                            public void onResponse(Call<List<PostResultWishlist>> call, Response<List<PostResultWishlist>> response) {
                                Log.e(TAG, "call onResponse");
                                if (response.isSuccessful()) {
                                    Log.e(TAG, "call onResponse success");
                                    List<PostResultWishlist> result = response.body();

                                    Random rand = new Random();
                                    int i = rand.nextInt(result.size());

                                    MovieRecommendActivity fragment = new MovieRecommendActivity();
                                    Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                                    bundle.putString("genre", result.get(i).genre);//번들에 넘길 값 저장
                                    bundle.putString("title", result.get(i).title);//번들에 넘길 값 저장
                                    bundle.putInt("num", result.get(i).num);//번들에 넘길 값 저장
                                    bundle.putString("poster_image", result.get(i).poster_image);//번들에 넘길 값 저장
                                    bundle.putString("explain", "감정 분석 결과, JOY 수치가 높은 것으로 나타나 "
                                            + result.get(i).genre + " 장르의 영화 " + result.get(i).title + "을(를) 추천드립니다.");//번들에 넘길 값 저장


                                    FragmentManager fm = getSupportFragmentManager();
                                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                                    fragment.setArguments(bundle);

                                    fragmentTransaction.replace(R.id.cloud, fragment);
                                    fragmentTransaction.commit();

                                } else {
                                    // 실패
                                    Log.e(TAG, "call onResponse fail");
                                }
                            }

                            @Override
                            public void onFailure(Call<List<PostResultWishlist>> call, Throwable t) {
                                // 통신 실패
                                Log.e(TAG, "call onFailure: " + t.getMessage());
                            }

                        });
                    }
                }

                else if (sorrowstar.equals("★")) {

                    Random rand = new Random();
                    int j = rand.nextInt(2);

                    if(j == 0) {

                        Call<List<PostResultWishlist>> call = service.getByWishlistGenre("코미디");

                        call.enqueue(new Callback<List<PostResultWishlist>>() {
                            @Override
                            public void onResponse(Call<List<PostResultWishlist>> call, Response<List<PostResultWishlist>> response) {
                                Log.e(TAG, "call onResponse");
                                if (response.isSuccessful()) {
                                    Log.e(TAG, "call onResponse success");
                                    List<PostResultWishlist> result = response.body();

                                    Random rand = new Random();
                                    int i = rand.nextInt(result.size());

                                    MovieRecommendActivity fragment = new MovieRecommendActivity();
                                    Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                                    bundle.putString("genre", result.get(i).genre);//번들에 넘길 값 저장
                                    bundle.putString("title", result.get(i).title);//번들에 넘길 값 저장
                                    bundle.putInt("num", result.get(i).num);//번들에 넘길 값 저장
                                    bundle.putString("poster_image", result.get(i).poster_image);//번들에 넘길 값 저장
                                    bundle.putString("explain", "감정 분석 결과, SORROW 수치가 높은 것으로 나타나 "
                                            + result.get(i).genre + " 장르의 영화 " + result.get(i).title + "을(를) 추천드립니다.");//번들에 넘길 값 저장


                                    FragmentManager fm = getSupportFragmentManager();
                                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                                    fragment.setArguments(bundle);

                                    fragmentTransaction.replace(R.id.cloud, fragment);
                                    fragmentTransaction.commit();

                                } else {
                                    // 실패
                                    Log.e(TAG, "call onResponse fail");
                                }
                            }

                            @Override
                            public void onFailure(Call<List<PostResultWishlist>> call, Throwable t) {
                                // 통신 실패
                                Log.e(TAG, "call onFailure: " + t.getMessage());
                            }

                        });
                    }

                    else if(j == 1) {

                        Call<List<PostResultWishlist>> call = service.getByWishlistGenre("드라마");

                        call.enqueue(new Callback<List<PostResultWishlist>>() {
                            @Override
                            public void onResponse(Call<List<PostResultWishlist>> call, Response<List<PostResultWishlist>> response) {
                                Log.e(TAG, "call onResponse");
                                if (response.isSuccessful()) {
                                    Log.e(TAG, "call onResponse success");
                                    List<PostResultWishlist> result = response.body();

                                    Random rand = new Random();
                                    int i = rand.nextInt(result.size());

                                    MovieRecommendActivity fragment = new MovieRecommendActivity();
                                    Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                                    bundle.putString("genre", result.get(i).genre);//번들에 넘길 값 저장
                                    bundle.putString("title", result.get(i).title);//번들에 넘길 값 저장
                                    bundle.putInt("num", result.get(i).num);//번들에 넘길 값 저장
                                    bundle.putString("poster_image", result.get(i).poster_image);//번들에 넘길 값 저장
                                    bundle.putString("explain", "감정 분석 결과, SORROW 수치가 높은 것으로 나타나 "
                                            + result.get(i).genre + " 장르의 영화 " + result.get(i).title + "을(를) 추천드립니다.");//번들에 넘길 값 저장


                                    FragmentManager fm = getSupportFragmentManager();
                                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                                    fragment.setArguments(bundle);

                                    fragmentTransaction.replace(R.id.cloud, fragment);
                                    fragmentTransaction.commit();

                                } else {
                                    // 실패
                                    Log.e(TAG, "call onResponse fail");
                                }
                            }

                            @Override
                            public void onFailure(Call<List<PostResultWishlist>> call, Throwable t) {
                                // 통신 실패
                                Log.e(TAG, "call onFailure: " + t.getMessage());
                            }

                        });
                    }
                }

                else if (surprisestar.equals("★")) {

                    Random rand = new Random();
                    int j = rand.nextInt(2);

                    if(j == 0) {

                        Call<List<PostResultWishlist>> call = service.getByWishlistGenre("액션");

                        call.enqueue(new Callback<List<PostResultWishlist>>() {
                            @Override
                            public void onResponse(Call<List<PostResultWishlist>> call, Response<List<PostResultWishlist>> response) {
                                Log.e(TAG, "call onResponse");
                                if (response.isSuccessful()) {
                                    Log.e(TAG, "call onResponse success");
                                    List<PostResultWishlist> result = response.body();

                                    Random rand = new Random();
                                    int i = rand.nextInt(result.size());

                                    MovieRecommendActivity fragment = new MovieRecommendActivity();
                                    Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                                    bundle.putString("genre", result.get(i).genre);//번들에 넘길 값 저장
                                    bundle.putString("title", result.get(i).title);//번들에 넘길 값 저장
                                    bundle.putInt("num", result.get(i).num);//번들에 넘길 값 저장
                                    bundle.putString("poster_image", result.get(i).poster_image);//번들에 넘길 값 저장
                                    bundle.putString("explain", "감정 분석 결과, SURPRISE 수치가 높은 것으로 나타나 "
                                            + result.get(i).genre + " 장르의 영화 " + result.get(i).title + "을(를) 추천드립니다.");//번들에 넘길 값 저장


                                    FragmentManager fm = getSupportFragmentManager();
                                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                                    fragment.setArguments(bundle);

                                    fragmentTransaction.replace(R.id.cloud, fragment);
                                    fragmentTransaction.commit();

                                } else {
                                    // 실패
                                    Log.e(TAG, "call onResponse fail");
                                }
                            }

                            @Override
                            public void onFailure(Call<List<PostResultWishlist>> call, Throwable t) {
                                // 통신 실패
                                Log.e(TAG, "call onFailure: " + t.getMessage());
                            }

                        });
                    }

                    else if(j == 1) {

                        Call<List<PostResultWishlist>> call = service.getByWishlistGenre("공상과학");

                        call.enqueue(new Callback<List<PostResultWishlist>>() {
                            @Override
                            public void onResponse(Call<List<PostResultWishlist>> call, Response<List<PostResultWishlist>> response) {
                                Log.e(TAG, "call onResponse");
                                if (response.isSuccessful()) {
                                    Log.e(TAG, "call onResponse success");
                                    List<PostResultWishlist> result = response.body();

                                    Random rand = new Random();
                                    int i = rand.nextInt(result.size());

                                    MovieRecommendActivity fragment = new MovieRecommendActivity();
                                    Bundle bundle = new Bundle(); // 번들을 통해 값 전달
                                    bundle.putString("genre", result.get(i).genre);//번들에 넘길 값 저장
                                    bundle.putString("title", result.get(i).title);//번들에 넘길 값 저장
                                    bundle.putInt("num", result.get(i).num);//번들에 넘길 값 저장
                                    bundle.putString("poster_image", result.get(i).poster_image);//번들에 넘길 값 저장
                                    bundle.putString("explain", "감정 분석 결과, SURPRISE 수치가 높은 것으로 나타나 "
                                            + result.get(i).genre + " 장르의 영화 " + result.get(i).title + "을(를) 추천드립니다.");//번들에 넘길 값 저장


                                    FragmentManager fm = getSupportFragmentManager();
                                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                                    fragment.setArguments(bundle);

                                    fragmentTransaction.replace(R.id.cloud, fragment);
                                    fragmentTransaction.commit();

                                } else {
                                    // 실패
                                    Log.e(TAG, "call onResponse fail");
                                }
                            }

                            @Override
                            public void onFailure(Call<List<PostResultWishlist>> call, Throwable t) {
                                // 통신 실패
                                Log.e(TAG, "call onFailure: " + t.getMessage());
                            }

                        });
                    }
                }

            }

        });

        Button choose = findViewById(R.id.choose);
        choose.setOnClickListener(view -> {
            ChooseDialog();
        });

    }

    public void ChooseDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(CloudVision.this);
        builder
                .setMessage(R.string.dialog_select_prompt)
                .setPositiveButton(R.string.dialog_select_gallery, (dialog, which) -> startGalleryChooser())
                .setNegativeButton(R.string.dialog_select_camera, (dialog, which) -> startCamera());
        builder.create().show();
    }

    public void startGalleryChooser() {
        if (PermissionUtils.requestPermission(this, GALLERY_PERMISSIONS_REQUEST, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select a photo"),
                    GALLERY_IMAGE_REQUEST);
        }
    }

    public void startCamera() {
        if (PermissionUtils.requestPermission(
                this,
                CAMERA_PERMISSIONS_REQUEST,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA)) {
            Log.d(TAG, "startCamera: if통과");
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            Log.d(TAG, "startCamera: 2");
            Uri photoUri = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", getCameraFile());
            Log.d(TAG, "startCamera: 3");
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
            Log.d(TAG, "startCamera: 4");
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Log.d(TAG, "startCamera: 5");
            startActivityForResult(intent, CAMERA_IMAGE_REQUEST);
            Log.d(TAG, "startCamera: success");
        }
    }

    public File getCameraFile() {
        File dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        Log.d(TAG, "getCameraFile: success"); 
        return new File(dir, FILE_NAME);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            uploadImage(data.getData());
        } else if (requestCode == CAMERA_IMAGE_REQUEST && resultCode == RESULT_OK) {
            Uri photoUri = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", getCameraFile());
            uploadImage(photoUri);
        }
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CAMERA_PERMISSIONS_REQUEST:
                if (PermissionUtils.permissionGranted(requestCode, CAMERA_PERMISSIONS_REQUEST, grantResults)) {
                    startCamera();
                }
                break;
            case GALLERY_PERMISSIONS_REQUEST:
                if (PermissionUtils.permissionGranted(requestCode, GALLERY_PERMISSIONS_REQUEST, grantResults)) {
                    startGalleryChooser();
                }
                break;
        }
    }

    public void uploadImage(Uri uri) {
        if (uri != null) {
            try {
                // scale the image to save on bandwidth
                Bitmap bitmap =
                        scaleBitmapDown(
                                MediaStore.Images.Media.getBitmap(getContentResolver(), uri),
                                MAX_DIMENSION);

                callCloudVision(bitmap);
                mMainImage.setImageBitmap(bitmap);

            } catch (IOException e) {
                Log.d(TAG, "Image picking failed because " + e.getMessage());
                Toast.makeText(this, R.string.image_picker_error, Toast.LENGTH_LONG).show();
            }
        } else {
            Log.d(TAG, "Image picker gave us a null image.");
            Toast.makeText(this, R.string.image_picker_error, Toast.LENGTH_LONG).show();
        }
    }

    private Vision.Images.Annotate prepareAnnotationRequest(Bitmap bitmap) throws IOException {
        HttpTransport httpTransport = AndroidHttp.newCompatibleTransport();
        JsonFactory jsonFactory = GsonFactory.getDefaultInstance();

        VisionRequestInitializer requestInitializer =
                new VisionRequestInitializer(CLOUD_VISION_API_KEY) {
                    /**
                     * We override this so we can inject important identifying fields into the HTTP
                     * headers. This enables use of a restricted cloud platform API key.
                     */
                    @Override
                    protected void initializeVisionRequest(VisionRequest<?> visionRequest)
                            throws IOException {
                        super.initializeVisionRequest(visionRequest);

                        String packageName = getPackageName();
                        visionRequest.getRequestHeaders().set(ANDROID_PACKAGE_HEADER, packageName);


                        String sig = PackageManagerUtils.getSignature(getPackageManager(), packageName);

                        visionRequest.getRequestHeaders().set(ANDROID_CERT_HEADER, sig);


                    }
                };

        Vision.Builder builder = new Vision.Builder(httpTransport, jsonFactory, null);
        builder.setVisionRequestInitializer(requestInitializer);

        Vision vision = builder.build();

        BatchAnnotateImagesRequest batchAnnotateImagesRequest =
                new BatchAnnotateImagesRequest();
        batchAnnotateImagesRequest.setRequests(new ArrayList<AnnotateImageRequest>() {{
            AnnotateImageRequest annotateImageRequest = new AnnotateImageRequest();

            // Add the image
            Image base64EncodedImage = new Image();
            // Convert the bitmap to a JPEG
            // Just in case it's a format that Android understands but Cloud Vision
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream);
            byte[] imageBytes = byteArrayOutputStream.toByteArray();

            // Base64 encode the JPEG
            base64EncodedImage.encodeContent(imageBytes);
            annotateImageRequest.setImage(base64EncodedImage);

            // add the features we want
            annotateImageRequest.setFeatures(new ArrayList<Feature>() {{
                Feature FaceDetection = new Feature();
                FaceDetection.setType("FACE_DETECTION");
                FaceDetection.setMaxResults(MAX_LABEL_RESULTS);
                add(FaceDetection);
            }});

            // Add the list of one thing to the request
            add(annotateImageRequest);
        }});

        Vision.Images.Annotate annotateRequest =
                vision.images().annotate(batchAnnotateImagesRequest);
        // Due to a bug: requests to Vision API containing large images fail when GZipped.
        annotateRequest.setDisableGZipContent(true);
        Log.d(TAG, "created Cloud Vision request object, sending request");

        return annotateRequest;
    }

    private static class LableDetectionTask extends AsyncTask<Object, Void, String> {
        private final WeakReference<CloudVision> mActivityWeakReference;
        private Vision.Images.Annotate mRequest;

        LableDetectionTask(CloudVision activity, Vision.Images.Annotate annotate) {
            mActivityWeakReference = new WeakReference<>(activity);
            mRequest = annotate;
        }

        @Override
        protected String doInBackground(Object... params) {
            try {
                Log.d(TAG, "created Cloud Vision request object, sending request");
                BatchAnnotateImagesResponse response = mRequest.execute();
                return convertResponseToString(response);

            } catch (GoogleJsonResponseException e) {
                Log.d(TAG, "failed to make API request because " + e.getContent());
            } catch (IOException e) {
                Log.d(TAG, "failed to make API request because of other IOException " +
                        e.getMessage());
            }
            return "Cloud Vision API request failed. Check logs for details.";
        }

        protected void onPostExecute(String result) {
            CloudVision activity = mActivityWeakReference.get();
            if (activity != null && !activity.isFinishing()) {
                TextView imageDetail = activity.findViewById(R.id.image_details);
                imageDetail.setText(result);
            }
        }
    }

    private void callCloudVision(final Bitmap bitmap) {
        // Switch text to loading
        mImageDetails.setText(R.string.loading_message);

        Toast.makeText(this, "•••감정 분석을 하고 있습니다•••", Toast.LENGTH_SHORT).show();


        // Do the real work in an async task, because we need to use the network anyway
        try {
            AsyncTask<Object, Void, String> FaceDetectionTask = new LableDetectionTask(this, prepareAnnotationRequest(bitmap));
            FaceDetectionTask.execute();
        } catch (IOException e) {
            Log.d(TAG, "failed to make API request because of other IOException " +
                    e.getMessage());
        }
    }

    private Bitmap scaleBitmapDown(Bitmap bitmap, int maxDimension) {

        int originalWidth = bitmap.getWidth();
        int originalHeight = bitmap.getHeight();
        int resizedWidth = maxDimension;
        int resizedHeight = maxDimension;

        if (originalHeight > originalWidth) {
            resizedHeight = maxDimension;
            resizedWidth = (int) (resizedHeight * (float) originalWidth / (float) originalHeight);
        } else if (originalWidth > originalHeight) {
            resizedWidth = maxDimension;
            resizedHeight = (int) (resizedWidth * (float) originalHeight / (float) originalWidth);
        } else if (originalHeight == originalWidth) {
            resizedHeight = maxDimension;
            resizedWidth = maxDimension;
        }
        return Bitmap.createScaledBitmap(bitmap, resizedWidth, resizedHeight, false);
    }

    private static String convertResponseToString(BatchAnnotateImagesResponse response) {
        StringBuilder message = new StringBuilder("I found these things :\n\n");

        List<FaceAnnotation> faces = response.getResponses().get(0).getFaceAnnotations();
        if (faces != null) {
            for (FaceAnnotation face : faces) {
                message.append("Anger   (◟‸◞)   :   ");
                String anger=output(face.getAngerLikelihood());
                angerstar = anger;
                message.append(anger);
                message.append("\n");
                message.append("Joy   ◠‿◠   :   ");
                String joy=output(face.getJoyLikelihood());
                joystar = joy;
                message.append(joy);
                message.append("\n");
                message.append("Sorrow   •́︿•̀ ｡   :   ");
                String sorrow=output(face.getSorrowLikelihood());
                sorrowstar = sorrow;
                message.append(sorrow);
                message.append("\n");
                message.append("Surprise   ☉་☉   :   ");
                String surprise=output(face.getSurpriseLikelihood());
                surprisestar = surprise;
                message.append(surprise);
                message.append("\n");

/*                message.append("\n");
                message.append("DetectionConfidence: ");
                message.append(face.getDetectionConfidence());
                message.append("\n");*/

            }
        } else {
            message.append("nothing");
        }

        return message.toString();
    }

    private static String output(String likelihood){
        String result="검출할 수 없습니다.";
        if (likelihood.equals("VERY_LIKELY")) result ="★★★★★";
        else if (likelihood.equals("LIKELY")) result ="★★★★⛤";
        else if (likelihood.equals("POSSIBLE")) result ="★★★⛤⛤";
        else if (likelihood.equals("UNLIKELY")) result ="★★⛤⛤⛤";
        else if (likelihood.equals("VERY_UNLIKELY")) result ="★⛤⛤⛤⛤";

        return result;
    }
}

