package com.se17.give2shareapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.se17.give2shareapplication.Activities.Donor.DonorHomeActivity;
import com.se17.give2shareapplication.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //this code will pause the app for 1.5 secs and then any thing in run method will run.
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                SharedPreferences userPref = getApplicationContext().getSharedPreferences("user",Context.MODE_PRIVATE);
                boolean isLoggedIn = userPref.getBoolean("isLoggedIn",false);

                if (isLoggedIn){
                    startActivity(new Intent(MainActivity.this, DonorHomeActivity.class));
                    finish();
                }

                else {
                    isFirstTime();
                }
            }
        },1500);
    }

    private void isFirstTime(){
        //checking if app is running for the very first time
        SharedPreferences preferences = getApplication().getSharedPreferences("onBoard", Context.MODE_PRIVATE);
        boolean isFirstTime = preferences.getBoolean("isFirstTime", true);

        if(isFirstTime){
            //if its true then its first time
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("isFirstTime", false);
            editor.apply();

            //start OnBoard Activity
            startActivity(new Intent(MainActivity.this, OnBoardActivity.class));
            finish();

        }
        else  {
            //start Auth (Login/Signup) Activity
            startActivity(new Intent(MainActivity.this, Main2Activity.class));
            finish();
        }
    }
}
