package com.mtechnologies.martin.bulsuapp.utilities;

/**
 * Created by martin on 3/29/18.
 */
import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.mtechnologies.martin.bulsuapp.LoginActivity;

public class SessionManager {
    SharedPreferences pref;

    // Editor for Shared preferences
    Editor editor;

    // Context
    Context _context;

    private static final String PREF_NAME = "BulsuApp";

    // All Shared Preferences Keys
    int PRIVATE_MODE = 0;

    private static final String IS_LOGIN = "IsLoggedIn";

    // User name (make variable public to access from outside)
    public static final String KEY_COOKIE = "cookie";

    // Email address (make variable public to access from outside)
    public static final String KEY_EMAIL = "email";


    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createLoginSession(String email) {
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing name in pref

        // Storing email in pref
        editor.putString(KEY_EMAIL, email);

        // commit changes
        editor.commit();
    }


    public void checkLogin() {
        // Check login status
        if (!this.isLoggedIn()) {
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, LoginActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
        }

    }

    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();

        user.put(KEY_COOKIE, pref.getString(KEY_COOKIE, null));

        // user email id
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));

        // return user
        return user;
    }
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }
}
