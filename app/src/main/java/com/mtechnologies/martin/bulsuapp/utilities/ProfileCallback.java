package com.mtechnologies.martin.bulsuapp.utilities;

import com.mtechnologies.martin.bulsuapp.models.CurrentUser;

import java.util.List;

/**
 * Created by martin on 4/5/18.
 */

public interface ProfileCallback {
    void myProfile(List<CurrentUser> currentUser);

}
