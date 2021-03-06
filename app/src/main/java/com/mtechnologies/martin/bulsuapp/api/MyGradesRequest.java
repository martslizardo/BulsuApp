package com.mtechnologies.martin.bulsuapp.api;

import android.text.Html;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mtechnologies.martin.bulsuapp.models.MyGrades;
import com.mtechnologies.martin.bulsuapp.models.MyGradesResponse;
import com.mtechnologies.martin.bulsuapp.utilities.MyGradesCallback;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by martin on 3/17/18.
 */

public class MyGradesRequest implements Callback<ResponseBody> {



    private com.mtechnologies.martin.bulsuapp.utilities.Callback mCallback;
    public MyGradesRequest(String studentID,String term,com.mtechnologies.martin.bulsuapp.utilities.Callback callback){
        this.mCallback = callback;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://myportal.bulsu.edu.ph")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BulsuApi bsuApi = retrofit.create(BulsuApi.class);
        Call<ResponseBody> call = bsuApi.myGrades(studentID,term);
        call.enqueue(this);
    }



    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        try{
            Gson gson = new GsonBuilder().create();
            MyGrades myGrades = new MyGrades();
            String result,result2 = "";
            if (response.isSuccessful()){
                result = response.body().string();

                MyGradesResponse gradesResponse =gson.fromJson(result, MyGradesResponse .class);
                Log.i("header",result);
                myGrades.setSuccess(true);
                myGrades.setContent(Html.fromHtml(gradesResponse.getContent()).toString().trim());
                mCallback.result(myGrades);

            }else{
                result = response.errorBody().string();
                Log.i("header",result);
                myGrades.setSuccess(false);
                mCallback.result(myGrades);


            }

        } catch(Exception e){
            e.printStackTrace();
        }


    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t ) {

    }

}
