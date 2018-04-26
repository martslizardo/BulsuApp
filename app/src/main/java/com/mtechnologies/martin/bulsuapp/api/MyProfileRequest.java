package com.mtechnologies.martin.bulsuapp.api;

import android.content.Context;
import android.util.Log;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.mtechnologies.martin.bulsuapp.models.CurrentUser;
import com.mtechnologies.martin.bulsuapp.pages.Dashboard;
import com.mtechnologies.martin.bulsuapp.utilities.AddCookiesInterceptor;
import com.mtechnologies.martin.bulsuapp.utilities.ProfileCallback;
import com.mtechnologies.martin.bulsuapp.utilities.ReceivedCookiesInterceptor;
import com.mtechnologies.martin.bulsuapp.utilities.SessionManager;

import org.riversun.okhttp3.OkHttp3CookieHelper;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import pl.droidsonroids.jspoon.HtmlAdapter;
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
    private String mCookie;
    private String html;
    private OkHttpClient.Builder builder = new OkHttpClient.Builder();
    public static OkHttpClient client = new OkHttpClient();
    public MyProfileRequest(com.mtechnologies.martin.bulsuapp.utilities.Callback callback,Context context){
        builder.interceptors().add(new AddCookiesInterceptor(context));
        builder.interceptors().add(new ReceivedCookiesInterceptor(context));
        client=builder.build();
        this.mCallback = callback;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://myportal.bulsu.edu.ph")
//                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(JspoonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();


        BulsuApi bulsuApi = retrofit.create(BulsuApi.class);
//        Call<ResponseBody> call=bulsuApi.myProfile();
//        call.enqueue(this);
        bulsuApi.myProfile().subscribe(myData->{
            System.out.println(myData.profileDetails);
            System.out.println(myData.profileName);
            System.out.println(myData.imageSrc);
            List<CurrentUser> currentUserList =new ArrayList<>();
        CurrentUser currentUser=new CurrentUser();
        currentUser.setCurrentuserName(myData.profileName);
        String[]profileDetails=myData.profileDetails;
        currentUser.setCurrentUserImage(myData.imageSrc);
        currentUser.setCurrentUserDefinition(profileDetails[0]);
        currentUser.setCurrentUserID(profileDetails[1]);
        currentUser.setCurrentUserAddress(profileDetails[2]);
        currentUser.setCurrentUserEmail(profileDetails[3]);
        currentUserList.add(currentUser);
        Log.i("Size",String.valueOf(currentUserList.size()));
        mCallback.result(currentUserList);
        });


//

    }

//    public static void CurrentUser(Dashboard post){
//        List<CurrentUser> currentUserList =new ArrayList<>();
//        CurrentUser currentUser=new CurrentUser();
//        currentUser.setCurrentuserDetails(post.profileDetails);
//        currentUser.setCurrentuserName(post.profileName);
//        currentUserList.add(currentUser);
//        Log.i("Size",String.valueOf(currentUserList.size()));
//        mCallback.result(currentUserList);



    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        try{

//            String result,result2 = "";
            List<CurrentUser> currentUserList =new ArrayList<>();
            Log.i("reponse code:",String.valueOf(response.body()));
            Log.i("reponse code:",String.valueOf(response.headers()));

//            Log.i("reponse Body:",html);


            if (response.isSuccessful()){
                Log.i("successful","true");
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.body().byteStream(),"UTF-8"));
                Gson gson = new GsonBuilder().create();
                String line;
                while ((line = reader.readLine()) != null) {
                    Log.e("Debug","Server Response "+line);
                    html+=line;
                }
                Jspoon jspoon= Jspoon.create();
                HtmlAdapter<Dashboard> htmlAdapter = jspoon.adapter(Dashboard.class);

                Dashboard page = htmlAdapter.fromHtml(html);
                CurrentUser currentUser=new CurrentUser();
//                currentUser.setCurrentuserDetails(page.profileDetails);
                currentUser.setCurrentuserName(page.profileName);
                currentUserList.add(currentUser);
                Log.i("Size",String.valueOf(currentUserList.size()));
                mCallback.result(currentUserList);


//
            }else{
//
            }

        } catch(Exception e){
            e.printStackTrace();
        }


    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {

    }


}
