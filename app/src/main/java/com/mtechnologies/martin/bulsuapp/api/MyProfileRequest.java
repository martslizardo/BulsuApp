package com.mtechnologies.martin.bulsuapp.api;

import android.text.Html;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mtechnologies.martin.bulsuapp.models.CurrentUser;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.mtechnologies.martin.bulsuapp.pages.Dashboard;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import okhttp3.ResponseBody;
import pl.droidsonroids.jspoon.Jspoon;
import pl.droidsonroids.retrofit2.JspoonConverterFactory;
import retrofit2.Call;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by martin on 3/30/18.
 */

public class MyProfileRequest implements Callback<ResponseBody>{

    private com.mtechnologies.martin.bulsuapp.utilities.Callback mCallback;

    public MyProfileRequest(com.mtechnologies.martin.bulsuapp.utilities.Callback callback, String cookie){
        this.mCallback = callback;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://myportal.bulsu.edu.ph")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BulsuApi bulsuApi = retrofit.create(BulsuApi.class);
        Call<ResponseBody> call=bulsuApi.myProfile(cookie);
        call.enqueue(this);
    }



    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        try{
            Gson gson = new GsonBuilder().create();
//            String result,result2 = "";
            Log.i("reponse code:",String.valueOf(response.code()));

            if (response.isSuccessful()){

                Log.i("reponse:",String.valueOf(response.body().string()));

            }else{
                Log.i("reponse code:",response.errorBody().string());
            }

        } catch(Exception e){
            e.printStackTrace();
        }


    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {

    }


}
