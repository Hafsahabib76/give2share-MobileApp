package com.se17.give2shareapplication.Activities.Donor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.se17.give2shareapplication.R;

import com.se17.give2shareapplication.testActivity;


public class DonorHomeActivity extends AppCompatActivity {

    private Context ctx = DonorHomeActivity.this;
    Button g2sYoutubeVidBtn;
    CardView donateMoney, donateItem, donateEasypaisa, donateCallCollect;
    private ProgressDialog dialog;

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_home);
        init();
    }

    public void init() {
        g2sYoutubeVidBtn = findViewById(R.id.HowitWorks);
        donateMoney = findViewById(R.id.donate_money);
        donateItem = findViewById(R.id.donate_item);
        donateEasypaisa = findViewById(R.id.donate_easypaisa);
        donateCallCollect = findViewById(R.id.donate_callCollect);

        dialog = new ProgressDialog(ctx);
        dialog.setCancelable(false);

        g2sYoutubeVidBtn.setOnClickListener(v->{
            dialog.setMessage("Opening Give2Share Video");
            dialog.show();

            startActivity(new Intent(ctx, g2sVideoYoutubeActivity.class));
            dialog.dismiss();
        });

        donateItem.setOnClickListener(v->{
            startActivity(new Intent(ctx, DonateItemStep1Activity.class));
        });

        donateCallCollect.setOnClickListener(v->{
            startActivity(new Intent(ctx, CallCollectActivity.class));
        });

        donateEasypaisa.setOnClickListener(v->{
            startActivity(new Intent(ctx, DonateEasypaisaActivity.class));
        });

        //for Bottom navigation code
        bottomNavigationView = findViewById(R.id.bottom_nav);
        //setting home as a selection
        bottomNavigationView.setSelectedItemId(R.id.nav_home);
        //Item selection
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.nav_home:
                        finish();
                        return true;

                    case R.id.nav_profile:
                        startActivity(new Intent(ctx, DonorProfileActivity.class));
                        return true;

                }
                return false;
            }
        });
    }



}
