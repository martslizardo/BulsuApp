package com.mtechnologies.martin.bulsuapp.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by martin on 3/17/18.
 */

public class MyGradesResponse {
    @SerializedName("success")
    private String success;
    @SerializedName("content")
    private String content;


    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
