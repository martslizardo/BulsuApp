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

    public List<String> getCurrentuserDetails() {
        return currentuserDetails;
    }

    public void setCurrentuserDetails(List<String> currentuserDetails) {
        this.currentuserDetails = currentuserDetails;
    }

    private String currentuserName;
    private List<String> currentuserDetails;

}
