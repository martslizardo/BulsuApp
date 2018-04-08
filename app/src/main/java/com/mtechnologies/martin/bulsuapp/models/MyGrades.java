package com.mtechnologies.martin.bulsuapp.models;

/**
 * Created by martin on 3/17/18.
 */

public class MyGrades {

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    private boolean success;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private String content;
}
