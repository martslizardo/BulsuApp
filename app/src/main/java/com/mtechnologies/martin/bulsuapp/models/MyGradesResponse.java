package com.mtechnologies.martin.bulsuapp.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by martin on 3/17/18.
 */

public class MyGradesResponse  implements Serializable{

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    private boolean success;

    private String content;




    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
