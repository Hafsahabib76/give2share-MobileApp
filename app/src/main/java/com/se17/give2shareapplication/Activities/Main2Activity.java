package com.se17.give2shareapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.se17.give2shareapplication.Activities.Authentication.LoginActivity;
import com.se17.give2shareapplication.Activities.Authentication.RegisterActivity;
import com.se17.give2shareapplication.Activities.Donor.DonorHomeActivity;
import com.se17.give2shareapplication.R;

public class Main2Activity extends AppCompatActivity {
    Context ctx = Main2Activity.this;
    private Button registerBtn, loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        init();

    }

    private void init() {

        registerBtn = findViewById(R.id.registerBtn);
        loginBtn = findViewById(R.id.loginBtn);

        registerBtn.setOnClickListener(v->{
            startActivity(new Intent(ctx, RegisterTypeActivity.class));
            finish();
        });

        loginBtn.setOnClickListener(v->{
            startActivity(new Intent(ctx, LoginActivity.class));
        });
    }
}
