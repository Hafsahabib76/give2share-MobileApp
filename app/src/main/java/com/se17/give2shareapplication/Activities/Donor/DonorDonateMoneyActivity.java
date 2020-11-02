package com.se17.give2shareapplication.Activities.Donor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.se17.give2shareapplication.R;

public class DonorDonateMoneyActivity extends AppCompatActivity {

    Context ctx = DonorDonateMoneyActivity.this;
    Button donateMoney;

    //for toolbar
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_donate_money);


        donateMoney = (Button)findViewById(R.id.donateMoneyBtn);

        donateMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(ctx, DonorMoneyPaymentActivity.class);
                startActivity(in);
            }
        });

        //toolbar code
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            toolbar.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

    }
}
