package com.mtechnologies.martin.bulsuapp.models;

/**
 * Created by LENOVO on 01/03/2018.
 */

public class LoginResponse {

    private String succcess;
    private String content;
    private String error;
    private String cookie;
    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }


    public String getSucccess() {
        return succcess;
    }

    public void setSucccess(String succcess) {
        this.succcess = succcess;
    }

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
