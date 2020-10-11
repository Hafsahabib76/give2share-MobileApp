package com.se17.give2shareapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.se17.give2shareapplication.Activities.OnBoardActivity;
import com.se17.give2shareapplication.Authentication.LoginActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                isFirstTime();
            }
        }, 1500);
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
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }
    }
}
