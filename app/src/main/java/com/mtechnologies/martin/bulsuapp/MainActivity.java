package com.mtechnologies.martin.bulsuapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mtechnologies.martin.bulsuapp.api.MyGradesRequest;
import com.mtechnologies.martin.bulsuapp.api.MyProfileRequest;
import com.mtechnologies.martin.bulsuapp.fragments.GradesFragment;
import com.mtechnologies.martin.bulsuapp.fragments.ProfileFragment;
import com.mtechnologies.martin.bulsuapp.models.CurrentUser;
import com.mtechnologies.martin.bulsuapp.models.MyGrades;
import com.mtechnologies.martin.bulsuapp.utilities.Callback;
import com.mtechnologies.martin.bulsuapp.utilities.MyGradesCallback;
import com.mtechnologies.martin.bulsuapp.utilities.RetrofitUtil;
import com.mtechnologies.martin.bulsuapp.utilities.SessionManager;

import java.util.HashMap;

import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,MyGradesCallback {
    EditText txtStudentID;
    Button  btnGrades;
    private CurrentUser mCurrentUser;

    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SessionManager session =new SessionManager(getApplicationContext());
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
//        btnGrades=(Button)findViewById(R.id.btn_grades);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        session.checkLogin();
//        txtStudentID=(EditText)findViewById(R.id.txtStudentID);
//        btnGrades.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                viewGrades();
//             }
//        });
//        Fragment fragment = null;
//        fragment = new ProfileFragment();
////HomeFragment= fragment class to launch that
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.content_main, fragment);
//        fragmentTransaction.commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    public void viewGrades(){
       MyGradesRequest myGradesRequest=new MyGradesRequest(txtStudentID.getText().toString(),"415",this);


    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        String title="";
        Fragment fragment=null;
        if (id == R.id.nav_camera) {
            fragment= new ProfileFragment();
        } else if (id == R.id.nav_gallery) {
            fragment= new GradesFragment();
        }  else if (id == R.id.nav_share) {

        }


        clearBackStack();
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.content_main, fragment,title)
                    .commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



        private void clearBackStack() {
            FragmentManager manager = getSupportFragmentManager();
            if (manager.getBackStackEntryCount() > 0) {
                FragmentManager.BackStackEntry first = manager.getBackStackEntryAt(0);
                manager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        }

        @Override
        public void setTitle(CharSequence title) {
            getSupportActionBar().setTitle(title);
        }


    @Override
    public void myGrades(MyGrades myGrades) {
         if(myGrades.isSuccess()){
             Toast.makeText(getApplicationContext(), myGrades.getContent(),
                     Toast.LENGTH_LONG).show();
         }
    }
}
