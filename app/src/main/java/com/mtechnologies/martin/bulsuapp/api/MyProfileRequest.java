package com.mtechnologies.martin.bulsuapp.api;

import android.content.Context;
import android.util.Log;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mtechnologies.martin.bulsuapp.utilities.AddCookiesInterceptor;
import com.mtechnologies.martin.bulsuapp.utilities.ReceivedCookiesInterceptor;

import org.riversun.okhttp3.OkHttp3CookieHelper;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by martin on 3/30/18.
 */

public class MyProfileRequest implements Callback<ResponseBody>{


    private com.mtechnologies.martin.bulsuapp.utilities.Callback mCallback;
    private String mCookie;
    private OkHttpClient.Builder builder = new OkHttpClient.Builder();
    public static OkHttpClient client = new OkHttpClient();
    public MyProfileRequest(com.mtechnologies.martin.bulsuapp.utilities.Callback callback,Context context){
        builder.interceptors().add(new AddCookiesInterceptor(context));
        builder.interceptors().add(new ReceivedCookiesInterceptor(context));
        client=builder.build();
        this.mCallback = callback;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://myportal.bulsu.edu.ph")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();


        BulsuApi bulsuApi = retrofit.create(BulsuApi.class);
        Call<ResponseBody> call=bulsuApi.myProfile();
        call.enqueue(this);

    }




    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        try{
            Gson gson = new GsonBuilder().create();

//            String result,result2 = "";
            Log.i("reponse code:",String.valueOf(response.body()));
            Log.i("reponse code:",String.valueOf(response.headers()));

//            Log.i("reponse Body:",html);


            if (response.isSuccessful()){
//                Log.i("Elements",element.toString());
//                Log.i("reponse:",String.valueOf(response.body().string()));

            }else{
//                Log.i("reponse code:",response.errorBody().string());
            }

        } catch(Exception e){
            e.printStackTrace();
        }


    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {

    }


}
