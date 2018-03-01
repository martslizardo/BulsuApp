package com.mtechnologies.martin.bulsuapp.models;
import com.google.gson.annotations.SerializedName;
/**
 * Created by LENOVO on 01/03/2018.
 */

public class LoginResponseSuccess {
    @SerializedName("content")
    private String content;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
    @SerializedName("success")
    private String success;

    @SerializedName("error")
    private String error;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }


}
