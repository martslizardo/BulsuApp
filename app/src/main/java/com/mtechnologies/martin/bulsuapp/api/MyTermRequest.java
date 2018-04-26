package com.mtechnologies.martin.bulsuapp.api;

import android.content.Context;
import android.util.Log;

import com.mtechnologies.martin.bulsuapp.models.CurrentUser;
import com.mtechnologies.martin.bulsuapp.models.TermUser;
import com.mtechnologies.martin.bulsuapp.utilities.AddCookiesInterceptor;
import com.mtechnologies.martin.bulsuapp.utilities.ReceivedCookiesInterceptor;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import pl.droidsonroids.retrofit2.JspoonConverterFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by martin on 4/8/18.
 */

public class MyTermRequest implements Callback<ResponseBody> {
    private com.mtechnologies.martin.bulsuapp.utilities.Callback mCallback;
    private OkHttpClient.Builder builder = new OkHttpClient.Builder();
    public static OkHttpClient client = new OkHttpClient();
    public MyTermRequest(com.mtechnologies.martin.bulsuapp.utilities.Callback callback,Context context){
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
        bulsuApi.myTerm().subscribe(term -> {
            List<TermUser> termUsersList =new ArrayList<>();
            String[] myTerm=term.termDetails;
            String[] myTermID=term.termID;
            for(int i=0;i<myTerm.length;i++){
                TermUser termUser=new TermUser();
                termUser.setTermID(myTermID[i]);
                termUser.setTerm(myTerm[i]);
                termUsersList.add(termUser);
                Log.i("Size",String.valueOf(termUsersList.get(i).getTerm()));
                System.out.println(term.termDetails[i]);
            }

            mCallback.result(termUsersList);
        });
    }



    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {

    }
}
