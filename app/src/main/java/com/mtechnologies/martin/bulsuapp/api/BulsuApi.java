package com.mtechnologies.martin.bulsuapp.api;

import com.mtechnologies.martin.bulsuapp.models.LoginToken;
import com.mtechnologies.martin.bulsuapp.pages.Dashboard;

import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by LENOVO on 01/03/2018.
 */

public interface BulsuApi {
    @FormUrlEncoded
    @POST("/login/verifylogin")
    Call<ResponseBody> login(@Field("username") String username,@Field("password") String password);


    @FormUrlEncoded
    @POST("/grades/txn/get/grades")
    Call<ResponseBody> myGrades(@Field("student") String studenID,@Field("term") String term);

    @GET("/profile")
    Call<ResponseBody> myProfile();
}
