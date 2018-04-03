package com.mtechnologies.martin.bulsuapp;


import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mtechnologies.martin.bulsuapp.models.LoginResponse;
import com.mtechnologies.martin.bulsuapp.models.LoginToken;
import com.mtechnologies.martin.bulsuapp.utilities.Callback;

import com.mtechnologies.martin.bulsuapp.api.LoginRequest;
import com.mtechnologies.martin.bulsuapp.utilities.LoginCallback;
import com.mtechnologies.martin.bulsuapp.utilities.MyGradesCallback;
import com.mtechnologies.martin.bulsuapp.utilities.RetrofitUtil;
import com.mtechnologies.martin.bulsuapp.utilities.SessionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements LoginCallback {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;
    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */


    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    static Retrofit retrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        retrofit= new RetrofitUtil().getRetrofitInstance();

        mPasswordView = (EditText) findViewById(R.id.password);
        mEmailView=(AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }



    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */


    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */


    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(LoginActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }


    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */


    public void login(){
        if (isValid()) {
          LoginRequest loginRequest=new LoginRequest(mEmailView.getText().toString().trim(),mPasswordView.getText().toString().trim(),this,getApplicationContext());

        }
    }





    @Override
    public void login(LoginResponse token) {
        if (token.getSucccess().toString().equalsIgnoreCase("true")){
            new SessionManager(this).createLoginSession(mEmailView.getText().toString());
            Intent intent =new Intent(this,MainActivity.class);
            startActivity(intent);
            System.out.println(token.getCookie());

        } else{
            Toast.makeText(this,"Invalid username/password",Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isValid(){
        boolean isValid = true;
        if (mPasswordView.getText().toString().trim().isEmpty()){
            mPasswordView.setError("Password cannot be empty");
            isValid = false;
        }else{
            mPasswordView.setError(null);
        }
        if (mEmailView.getText().toString().trim().isEmpty()){
            mEmailView.setError("Username cannot be empty");
            isValid = false;
        } else{
            mEmailView.setError(null);
        }
        return isValid;
    }
}

