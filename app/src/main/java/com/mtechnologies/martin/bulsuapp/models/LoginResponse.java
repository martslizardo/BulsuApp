package com.mtechnologies.martin.bulsuapp.models;

/**
 * Created by LENOVO on 01/03/2018.
 */

public class LoginResponse {
    public String getSucccess() {
        return succcess;
    }

    public void setSucccess(String succcess) {
        this.succcess = succcess;
    }

    private String succcess;



    private String content;
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
