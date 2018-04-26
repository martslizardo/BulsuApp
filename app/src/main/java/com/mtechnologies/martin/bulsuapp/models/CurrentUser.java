package com.mtechnologies.martin.bulsuapp.models;

import java.util.List;

/**
 * Created by martin on 3/30/18.
 */

public class CurrentUser {

    public String getCurrentuserName() {
        return currentuserName;
    }

    public void setCurrentuserName(String currentuserName) {
        this.currentuserName = currentuserName;
    }



    private String currentuserName;
    private String currentUserDefinition;
    private String currentUserID;
    private String currentUserAddress;
    private String currentUserEmail;
    private String currentUserImage;

    public String getCurrentUserImage() {
        return currentUserImage;
    }

    public void setCurrentUserImage(String currentUserImage) {
        this.currentUserImage = currentUserImage;
    }

  ;

    public String getCurrentUserDefinition() {
        return currentUserDefinition;
    }

    public void setCurrentUserDefinition(String currentUserDefinition) {
        this.currentUserDefinition = currentUserDefinition;
    }

    public String getCurrentUserID() {
        return currentUserID;
    }

    public void setCurrentUserID(String currentUserID) {
        this.currentUserID = currentUserID;
    }

    public String getCurrentUserAddress() {
        return currentUserAddress;
    }

    public void setCurrentUserAddress(String currentUserAddress) {
        this.currentUserAddress = currentUserAddress;
    }

    public String getCurrentUserEmail() {
        return currentUserEmail;
    }

    public void setCurrentUserEmail(String currentUserEmail) {
        this.currentUserEmail = currentUserEmail;
    }
}
