package com.example.movie;

import android.animation.ObjectAnimator;
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
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListPopupWindow;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends Fragment {
    String TAG = "Retrofit homeactivity";
    Button btstart;
    Bitmap bitmap;
    String base = "http://3.36.121.174";
    int num;
    private Boolean isFabOpen = false;
    private FloatingActionButton add, fab1;

    public HomeActivity() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home, container, false);

        //Retrofit ???????????? ??????
        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl("http://3.36.121.174:8081/")    // baseUrl ??????
                .addConverterFactory(GsonConverterFactory.create())  // Gson ????????? ??????
                .build();

        // ???????????? ??????????????? ?????? ??????
        RetrofitService service = retrofit.create(RetrofitService.class);

        ListView listView = (ListView)view.findViewById(R.id.movie_list);
        btstart = (Button) view.findViewById(R.id.btstart);
        add = (FloatingActionButton) view.findViewById(R.id.add);
        fab1 = (FloatingActionButton) view.findViewById(R.id.fab1);

        MyAdapter mMyAdapter = new MyAdapter();

        if (android.os.Build.VERSION.SDK_INT > 9) { StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); StrictMode.setThreadPolicy(policy); }


        Spinner genreSpinner = (Spinner) view.findViewById(R.id.spGenre);
        ArrayAdapter genreAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.genre_choice, R.layout.spinner_item);
        genreAdapter.setDropDownViewResource(R.layout.spin_dropdown);
        genreSpinner.setAdapter(genreAdapter);

        try {
            Field popup = Spinner.class.getDeclaredField("mPopup");
            popup.setAccessible(true);

            // Get private mPopup member variable and try cast to ListPopupWindow
            ListPopupWindow popupWindow = (ListPopupWindow) popup.get(genreSpinner);


            // Set popupWindow height to 500px
            popupWindow.setHeight(600);
        }
        catch (NoClassDefFoundError | ClassCastException | NoSuchFieldException | IllegalAccessException e) {
            // silently fail...
        }



        /*


        Bundle bundle = getArguments();
        if(bundle != null) {
            num = bundle.getInt("num");
            System.out.println("????????? ??? : " + num);

            //Diary??? ??????????????? ?????? ????????????????????? ????????????

            Call<List<PostResultWishlist>> call2 = service.deleteWishlist(num);

            call2.enqueue(new Callback<List<PostResultWishlist>>() {
                @Override
                public void onResponse(Call<List<PostResultWishlist>> call, Response<List<PostResultWishlist>> response) {
                    Log.e(TAG, "call onResponse");
                    if (response.isSuccessful()) {
                        Log.e(TAG, "call onResponse success");

                    } else {
                        // ??????
                        Log.e(TAG, "call onResponse fail");
                    }
                }
                @Override
                public void onFailure(Call<List<PostResultWishlist>> call, Throwable t) {
                    // ?????? ??????
                    Log.e(TAG, "call onFailure: " + t.getMessage());
                }

            });



        }

         */









        // ??????????????? ?????? ?????? ?????? ????????? ??????
        genreSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) { // ?????? ??????

                    // ????????? ?????? ??????
                    mMyAdapter.clearItem();
                    // listview ??????
                    mMyAdapter.notifyDataSetChanged();

                    Call<List<PostResultWishlist>> call = service.getWishlist();

                    call.enqueue(new Callback<List<PostResultWishlist>>() {
                        @Override
                        public void onResponse(Call<List<PostResultWishlist>> call, Response<List<PostResultWishlist>> response) {
                            Log.e(TAG, "call onResponse");
                            if (response.isSuccessful()) {
                                Log.e(TAG, "call onResponse success");
                                List<PostResultWishlist> result = response.body();

                                for (PostResultWishlist item : result) {
                                    Thread uThread = new Thread() {
                                        @Override
                                        public void run(){
                                            try{
                                                //????????? ????????? ????????? URL
                                                URL url = new URL(base + item.poster_image);
                                                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                                                conn.setDoInput(true); //Server ???????????? ?????? ????????? ????????? ??????
                                                conn.connect(); //????????? ?????? ????????? ??? (connect() ???????????? ?????? ?????? ?????????)
                                                InputStream is = conn.getInputStream(); //inputStream ??? ????????????
                                                bitmap = BitmapFactory.decodeStream(is); // Bitmap?????? ??????
                                            }catch (MalformedURLException e){
                                                e.printStackTrace();
                                            }catch (IOException e){
                                                e.printStackTrace();
                                            }
                                        }
                                    };
                                    uThread.start(); // ?????? Thread ??????
                                    try{
                                        uThread.join();
                                        mMyAdapter.addItem(bitmap, item.title);

                                    }catch (InterruptedException e){
                                        e.printStackTrace();
                                    }
                                }

                                listView.setAdapter(mMyAdapter);


                            } else {
                                // ??????
                                Log.e(TAG, "call onResponse fail");
                            }
                        }
                        @Override
                        public void onFailure(Call<List<PostResultWishlist>> call, Throwable t) {
                            // ?????? ??????
                            Log.e(TAG, "call onFailure: " + t.getMessage());
                        }

                    });


                }

                if (position == 1) { // ????????? ??????

                    // ????????? ?????? ??????
                    mMyAdapter.clearItem();
                    // listview ??????
                    mMyAdapter.notifyDataSetChanged();

                    Call<List<PostResultWishlist>> call = service.getByWishlistGenre("?????????");

                    call.enqueue(new Callback<List<PostResultWishlist>>() {
                        @Override
                        public void onResponse(Call<List<PostResultWishlist>> call, Response<List<PostResultWishlist>> response) {
                            Log.e(TAG, "call onResponse");
                            if (response.isSuccessful()) {
                                Log.e(TAG, "call onResponse success");
                                List<PostResultWishlist> result = response.body();

                                for (PostResultWishlist item : result) {
                                    Thread uThread = new Thread() {
                                        @Override
                                        public void run(){
                                            try{
                                                //????????? ????????? ????????? URL
                                                URL url = new URL(base + item.poster_image);
                                                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                                                conn.setDoInput(true); //Server ???????????? ?????? ????????? ????????? ??????
                                                conn.connect(); //????????? ?????? ????????? ??? (connect() ???????????? ?????? ?????? ?????????)
                                                InputStream is = conn.getInputStream(); //inputStream ??? ????????????
                                                bitmap = BitmapFactory.decodeStream(is); // Bitmap?????? ??????
                                            }catch (MalformedURLException e){
                                                e.printStackTrace();
                                            }catch (IOException e){
                                                e.printStackTrace();
                                            }
                                        }
                                    };
                                    uThread.start(); // ?????? Thread ??????
                                    try{
                                        uThread.join();
                                        mMyAdapter.addItem(bitmap, item.title);

                                    }catch (InterruptedException e){
                                        e.printStackTrace();
                                    }
                                }

                                listView.setAdapter(mMyAdapter);


                            } else {
                                // ??????
                                Log.e(TAG, "call onResponse fail");
                            }
                        }
                        @Override
                        public void onFailure(Call<List<PostResultWishlist>> call, Throwable t) {
                            // ?????? ??????
                            Log.e(TAG, "call onFailure: " + t.getMessage());
                        }

                    });


                }

                if (position == 2) { // ????????? ??????

                    // ????????? ?????? ??????
                    mMyAdapter.clearItem();
                    // listview ??????
                    mMyAdapter.notifyDataSetChanged();

                    Call<List<PostResultWishlist>> call = service.getByWishlistGenre("?????????");

                    call.enqueue(new Callback<List<PostResultWishlist>>() {
                        @Override
                        public void onResponse(Call<List<PostResultWishlist>> call, Response<List<PostResultWishlist>> response) {
                            Log.e(TAG, "call onResponse");
                            if (response.isSuccessful()) {
                                Log.e(TAG, "call onResponse success");
                                List<PostResultWishlist> result = response.body();

                                for (PostResultWishlist item : result) {
                                    Thread uThread = new Thread() {
                                        @Override
                                        public void run(){
                                            try{
                                                //????????? ????????? ????????? URL
                                                URL url = new URL(base + item.poster_image);
                                                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                                                conn.setDoInput(true); //Server ???????????? ?????? ????????? ????????? ??????
                                                conn.connect(); //????????? ?????? ????????? ??? (connect() ???????????? ?????? ?????? ?????????)
                                                InputStream is = conn.getInputStream(); //inputStream ??? ????????????
                                                bitmap = BitmapFactory.decodeStream(is); // Bitmap?????? ??????
                                            }catch (MalformedURLException e){
                                                e.printStackTrace();
                                            }catch (IOException e){
                                                e.printStackTrace();
                                            }
                                        }
                                    };
                                    uThread.start(); // ?????? Thread ??????
                                    try{
                                        uThread.join();
                                        mMyAdapter.addItem(bitmap, item.title);

                                    }catch (InterruptedException e){
                                        e.printStackTrace();
                                    }
                                }

                                listView.setAdapter(mMyAdapter);


                            } else {
                                // ??????
                                Log.e(TAG, "call onResponse fail");
                            }
                        }
                        @Override
                        public void onFailure(Call<List<PostResultWishlist>> call, Throwable t) {
                            // ?????? ??????
                            Log.e(TAG, "call onFailure: " + t.getMessage());
                        }

                    });


                }

                if (position == 3) { // ?????? ??????

                    // ????????? ?????? ??????
                    mMyAdapter.clearItem();
                    // listview ??????
                    mMyAdapter.notifyDataSetChanged();

                    Call<List<PostResultWishlist>> call = service.getByWishlistGenre("??????");

                    call.enqueue(new Callback<List<PostResultWishlist>>() {
                        @Override
                        public void onResponse(Call<List<PostResultWishlist>> call, Response<List<PostResultWishlist>> response) {
                            Log.e(TAG, "call onResponse");
                            if (response.isSuccessful()) {
                                Log.e(TAG, "call onResponse success");
                                List<PostResultWishlist> result = response.body();

                                for (PostResultWishlist item : result) {
                                    Thread uThread = new Thread() {
                                        @Override
                                        public void run(){
                                            try{
                                                //????????? ????????? ????????? URL
                                                URL url = new URL(base + item.poster_image);
                                                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                                                conn.setDoInput(true); //Server ???????????? ?????? ????????? ????????? ??????
                                                conn.connect(); //????????? ?????? ????????? ??? (connect() ???????????? ?????? ?????? ?????????)
                                                InputStream is = conn.getInputStream(); //inputStream ??? ????????????
                                                bitmap = BitmapFactory.decodeStream(is); // Bitmap?????? ??????
                                            }catch (MalformedURLException e){
                                                e.printStackTrace();
                                            }catch (IOException e){
                                                e.printStackTrace();
                                            }
                                        }
                                    };
                                    uThread.start(); // ?????? Thread ??????
                                    try{
                                        uThread.join();
                                        mMyAdapter.addItem(bitmap, item.title);

                                    }catch (InterruptedException e){
                                        e.printStackTrace();
                                    }
                                }

                                listView.setAdapter(mMyAdapter);


                            } else {
                                // ??????
                                Log.e(TAG, "call onResponse fail");
                            }
                        }
                        @Override
                        public void onFailure(Call<List<PostResultWishlist>> call, Throwable t) {
                            // ?????? ??????
                            Log.e(TAG, "call onFailure: " + t.getMessage());
                        }

                    });


                }

                if (position == 4) { // ?????? ??????

                    // ????????? ?????? ??????
                    mMyAdapter.clearItem();
                    // listview ??????
                    mMyAdapter.notifyDataSetChanged();

                    Call<List<PostResultWishlist>> call = service.getByWishlistGenre("??????");

                    call.enqueue(new Callback<List<PostResultWishlist>>() {
                        @Override
                        public void onResponse(Call<List<PostResultWishlist>> call, Response<List<PostResultWishlist>> response) {
                            Log.e(TAG, "call onResponse");
                            if (response.isSuccessful()) {
                                Log.e(TAG, "call onResponse success");
                                List<PostResultWishlist> result = response.body();

                                for (PostResultWishlist item : result) {
                                    Thread uThread = new Thread() {
                                        @Override
                                        public void run(){
                                            try{
                                                //????????? ????????? ????????? URL
                                                URL url = new URL(base + item.poster_image);
                                                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                                                conn.setDoInput(true); //Server ???????????? ?????? ????????? ????????? ??????
                                                conn.connect(); //????????? ?????? ????????? ??? (connect() ???????????? ?????? ?????? ?????????)
                                                InputStream is = conn.getInputStream(); //inputStream ??? ????????????
                                                bitmap = BitmapFactory.decodeStream(is); // Bitmap?????? ??????
                                            }catch (MalformedURLException e){
                                                e.printStackTrace();
                                            }catch (IOException e){
                                                e.printStackTrace();
                                            }
                                        }
                                    };
                                    uThread.start(); // ?????? Thread ??????
                                    try{
                                        uThread.join();
                                        mMyAdapter.addItem(bitmap, item.title);

                                    }catch (InterruptedException e){
                                        e.printStackTrace();
                                    }
                                }

                                listView.setAdapter(mMyAdapter);


                            } else {
                                // ??????
                                Log.e(TAG, "call onResponse fail");
                            }
                        }
                        @Override
                        public void onFailure(Call<List<PostResultWishlist>> call, Throwable t) {
                            // ?????? ??????
                            Log.e(TAG, "call onFailure: " + t.getMessage());
                        }

                    });


                }

                if (position == 5) { // ????????? ??????

                    // ????????? ?????? ??????
                    mMyAdapter.clearItem();
                    // listview ??????
                    mMyAdapter.notifyDataSetChanged();

                    Call<List<PostResultWishlist>> call = service.getByWishlistGenre("?????????");

                    call.enqueue(new Callback<List<PostResultWishlist>>() {
                        @Override
                        public void onResponse(Call<List<PostResultWishlist>> call, Response<List<PostResultWishlist>> response) {
                            Log.e(TAG, "call onResponse");
                            if (response.isSuccessful()) {
                                Log.e(TAG, "call onResponse success");
                                List<PostResultWishlist> result = response.body();

                                for (PostResultWishlist item : result) {
                                    Thread uThread = new Thread() {
                                        @Override
                                        public void run(){
                                            try{
                                                //????????? ????????? ????????? URL
                                                URL url = new URL(base + item.poster_image);
                                                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                                                conn.setDoInput(true); //Server ???????????? ?????? ????????? ????????? ??????
                                                conn.connect(); //????????? ?????? ????????? ??? (connect() ???????????? ?????? ?????? ?????????)
                                                InputStream is = conn.getInputStream(); //inputStream ??? ????????????
                                                bitmap = BitmapFactory.decodeStream(is); // Bitmap?????? ??????
                                            }catch (MalformedURLException e){
                                                e.printStackTrace();
                                            }catch (IOException e){
                                                e.printStackTrace();
                                            }
                                        }
                                    };
                                    uThread.start(); // ?????? Thread ??????
                                    try{
                                        uThread.join();
                                        mMyAdapter.addItem(bitmap, item.title);

                                    }catch (InterruptedException e){
                                        e.printStackTrace();
                                    }
                                }

                                listView.setAdapter(mMyAdapter);


                            } else {
                                // ??????
                                Log.e(TAG, "call onResponse fail");
                            }
                        }
                        @Override
                        public void onFailure(Call<List<PostResultWishlist>> call, Throwable t) {
                            // ?????? ??????
                            Log.e(TAG, "call onFailure: " + t.getMessage());
                        }

                    });


                }

                if (position == 6) { // ????????? ??????

                    // ????????? ?????? ??????
                    mMyAdapter.clearItem();
                    // listview ??????
                    mMyAdapter.notifyDataSetChanged();

                    Call<List<PostResultWishlist>> call = service.getByWishlistGenre("?????????");

                    call.enqueue(new Callback<List<PostResultWishlist>>() {
                        @Override
                        public void onResponse(Call<List<PostResultWishlist>> call, Response<List<PostResultWishlist>> response) {
                            Log.e(TAG, "call onResponse");
                            if (response.isSuccessful()) {
                                Log.e(TAG, "call onResponse success");
                                List<PostResultWishlist> result = response.body();

                                for (PostResultWishlist item : result) {
                                    Thread uThread = new Thread() {
                                        @Override
                                        public void run(){
                                            try{
                                                //????????? ????????? ????????? URL
                                                URL url = new URL(base + item.poster_image);
                                                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                                                conn.setDoInput(true); //Server ???????????? ?????? ????????? ????????? ??????
                                                conn.connect(); //????????? ?????? ????????? ??? (connect() ???????????? ?????? ?????? ?????????)
                                                InputStream is = conn.getInputStream(); //inputStream ??? ????????????
                                                bitmap = BitmapFactory.decodeStream(is); // Bitmap?????? ??????
                                            }catch (MalformedURLException e){
                                                e.printStackTrace();
                                            }catch (IOException e){
                                                e.printStackTrace();
                                            }
                                        }
                                    };
                                    uThread.start(); // ?????? Thread ??????
                                    try{
                                        uThread.join();
                                        mMyAdapter.addItem(bitmap, item.title);

                                    }catch (InterruptedException e){
                                        e.printStackTrace();
                                    }
                                }

                                listView.setAdapter(mMyAdapter);


                            } else {
                                // ??????
                                Log.e(TAG, "call onResponse fail");
                            }
                        }
                        @Override
                        public void onFailure(Call<List<PostResultWishlist>> call, Throwable t) {
                            // ?????? ??????
                            Log.e(TAG, "call onFailure: " + t.getMessage());
                        }

                    });


                }
                if (position == 7) { // ???????????? ??????

                    // ????????? ?????? ??????
                    mMyAdapter.clearItem();
                    // listview ??????
                    mMyAdapter.notifyDataSetChanged();

                    Call<List<PostResultWishlist>> call = service.getByWishlistGenre("????????????");

                    call.enqueue(new Callback<List<PostResultWishlist>>() {
                        @Override
                        public void onResponse(Call<List<PostResultWishlist>> call, Response<List<PostResultWishlist>> response) {
                            Log.e(TAG, "call onResponse");
                            if (response.isSuccessful()) {
                                Log.e(TAG, "call onResponse success");
                                List<PostResultWishlist> result = response.body();

                                for (PostResultWishlist item : result) {
                                    Thread uThread = new Thread() {
                                        @Override
                                        public void run(){
                                            try{
                                                //????????? ????????? ????????? URL
                                                URL url = new URL(base + item.poster_image);
                                                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                                                conn.setDoInput(true); //Server ???????????? ?????? ????????? ????????? ??????
                                                conn.connect(); //????????? ?????? ????????? ??? (connect() ???????????? ?????? ?????? ?????????)
                                                InputStream is = conn.getInputStream(); //inputStream ??? ????????????
                                                bitmap = BitmapFactory.decodeStream(is); // Bitmap?????? ??????
                                            }catch (MalformedURLException e){
                                                e.printStackTrace();
                                            }catch (IOException e){
                                                e.printStackTrace();
                                            }
                                        }
                                    };
                                    uThread.start(); // ?????? Thread ??????
                                    try{
                                        uThread.join();
                                        mMyAdapter.addItem(bitmap, item.title);

                                    }catch (InterruptedException e){
                                        e.printStackTrace();
                                    }
                                }

                                listView.setAdapter(mMyAdapter);


                            } else {
                                // ??????
                                Log.e(TAG, "call onResponse fail");
                            }
                        }
                        @Override
                        public void onFailure(Call<List<PostResultWishlist>> call, Throwable t) {
                            // ?????? ??????
                            Log.e(TAG, "call onFailure: " + t.getMessage());
                        }

                    });


                }

                if (position == 8) { // ?????? ??????

                    // ????????? ?????? ??????
                    mMyAdapter.clearItem();
                    // listview ??????
                    mMyAdapter.notifyDataSetChanged();

                    Call<List<PostResultWishlist>> call = service.getByWishlistGenre("??????");

                    call.enqueue(new Callback<List<PostResultWishlist>>() {
                        @Override
                        public void onResponse(Call<List<PostResultWishlist>> call, Response<List<PostResultWishlist>> response) {
                            Log.e(TAG, "call onResponse");
                            if (response.isSuccessful()) {
                                Log.e(TAG, "call onResponse success");
                                List<PostResultWishlist> result = response.body();

                                for (PostResultWishlist item : result) {
                                    Thread uThread = new Thread() {
                                        @Override
                                        public void run(){
                                            try{
                                                //????????? ????????? ????????? URL
                                                URL url = new URL(base + item.poster_image);
                                                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                                                conn.setDoInput(true); //Server ???????????? ?????? ????????? ????????? ??????
                                                conn.connect(); //????????? ?????? ????????? ??? (connect() ???????????? ?????? ?????? ?????????)
                                                InputStream is = conn.getInputStream(); //inputStream ??? ????????????
                                                bitmap = BitmapFactory.decodeStream(is); // Bitmap?????? ??????
                                            }catch (MalformedURLException e){
                                                e.printStackTrace();
                                            }catch (IOException e){
                                                e.printStackTrace();
                                            }
                                        }
                                    };
                                    uThread.start(); // ?????? Thread ??????
                                    try{
                                        uThread.join();
                                        mMyAdapter.addItem(bitmap, item.title);

                                    }catch (InterruptedException e){
                                        e.printStackTrace();
                                    }
                                }

                                listView.setAdapter(mMyAdapter);


                            } else {
                                // ??????
                                Log.e(TAG, "call onResponse fail");
                            }
                        }
                        @Override
                        public void onFailure(Call<List<PostResultWishlist>> call, Throwable t) {
                            // ?????? ??????
                            Log.e(TAG, "call onFailure: " + t.getMessage());
                        }

                    });


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        btstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), CloudVision.class));


            }

        });

        /*

        View.OnClickListener allMovieOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new aMovieActivity()).commit();

            }
        };


         */

        add.setOnClickListener(new View.OnClickListener() {


                                   @Override
                                   public void onClick(View view) {
                                       if (isFabOpen) {
                                           ObjectAnimator.ofFloat(fab1, "translationX", 0f).start();

                                           add.setImageResource(R.drawable.ic_add);
                                       } else {
                                           ObjectAnimator.ofFloat(fab1, "translationX", -250f).start();

                                           add.setImageResource(R.drawable.ic_close);
                                       }


                                       isFabOpen = !isFabOpen;
                                   }

                /*
                Snackbar.make(view, "?????? ?????? ?????????", Snackbar.LENGTH_LONG)
                        .setAction("AllMovie", allMovieOnClickListener).show();

                 */
        });


            fab1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new aMovieActivity()).commit();

                }


        });


        return view;
    }

}