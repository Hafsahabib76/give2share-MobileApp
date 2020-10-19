package com.se17.give2shareapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.se17.give2shareapplication.Activities.Authentication.RegisterActivity;
import com.se17.give2shareapplication.R;

public class RegisterTypeActivity extends AppCompatActivity {

    private Context ctx = RegisterTypeActivity.this;
    private Button donorType, requestorType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_type);

        init();


    }

    private void init() {

        donorType = findViewById(R.id.donorType);
        requestorType = findViewById(R.id.requestorType);

        donorType.setOnClickListener(v -> {
            startActivity(new Intent(ctx, RegisterActivity.class));
            finish();
        });
    }
}
