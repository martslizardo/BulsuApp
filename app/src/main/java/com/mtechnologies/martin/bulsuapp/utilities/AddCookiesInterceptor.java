package com.mtechnologies.martin.bulsuapp.utilities;

import android.content.Context;
import android.util.Log;

import java.io.IOException;

import java.util.HashSet;
import android.preference.PreferenceManager;


import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by martin on 4/3/18.
 */

public class AddCookiesInterceptor implements Interceptor {
    private Context context;
    public static final String PREF_COOKIES = "PREF_COOKIES";
    public AddCookiesInterceptor(Context context) {
        this.context = context;
    } // AddCook
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        HashSet<String> preferences = (HashSet<String>) PreferenceManager.getDefaultSharedPreferences(context).getStringSet(PREF_COOKIES, new HashSet<String>());
        for (String cookie : preferences) {
            builder.addHeader("Cookie", cookie);
            Log.v("OkHttp", "Adding Header: " + cookie); // This is done so I know which headers are being added; this interceptor is used after the normal logging of OkHttp
        }

        return chain.proceed(builder.build());
    }
}