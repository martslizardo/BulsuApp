package com.mtechnologies.martin.bulsuapp.api;


import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mtechnologies.martin.bulsuapp.models.LoginResponse;
import com.mtechnologies.martin.bulsuapp.models.LoginResponseSuccess;
import com.mtechnologies.martin.bulsuapp.models.LoginToken;
import com.mtechnologies.martin.bulsuapp.utilities.LoginCallback;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by LENOVO on 01/03/2018.
 */

public class LoginRequest implements Callback<ResponseBody> {

    private LoginCallback mCallback;
    public LoginRequest(String username,String password,LoginCallback callback){
        this.mCallback = callback;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://myportal.bulsu.edu.ph")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BulsuApi bsuApi = retrofit.create(BulsuApi.class);
        Call<ResponseBody> call = bsuApi.login(username,password);
        call.enqueue(this);

    }



    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        try{
            Gson gson = new GsonBuilder().create();
            LoginResponse loginResponse = new LoginResponse();
            String result,result2 = "";
            if (response.isSuccessful()){
                result = response.body().string();
                String cookie=response.headers().get("Set-Cookie").toString();
                LoginResponseSuccess success =gson.fromJson(result,LoginResponseSuccess.class);
                Log.i("header",result);
                String split_cookie[]=cookie.split(";");
                loginResponse.setSucccess(success.getSuccess());
                loginResponse.setContent(success.getContent());
                loginResponse.setCookie(split_cookie[0]);
                Log.i("header",loginResponse.getCookie());
                mCallback.login(loginResponse);

            }else{
                result = response.errorBody().string();
                Log.i("header",result);
                loginResponse.setSucccess("false");
                loginResponse.setCookie("");
                mCallback.login(loginResponse);


            }

        } catch(Exception e){
            e.printStackTrace();
        }


    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t ) {

    }
}
