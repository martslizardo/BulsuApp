package com.mtechnologies.martin.bulsuapp.utilities;

import com.mtechnologies.martin.bulsuapp.models.LoginResponse;

/**
 * Created by LENOVO on 01/03/2018.
 */

public interface LoginCallback {

        void login(LoginResponse loginToken);

}
