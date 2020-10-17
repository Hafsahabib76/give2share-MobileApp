package com.se17.give2shareapplication.Activities.Donor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.se17.give2shareapplication.R;
import com.se17.give2shareapplication.g2sVideoYoutubeActivity;

public class DonorHomeActivity extends AppCompatActivity {

    private Context ctx = DonorHomeActivity.this;
    Button g2sYoutubeVidBtn;
    CardView donateMoney, donateItem, donateEasypaisa, donateCallCollect;
    private ProgressDialog dialog;


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

        donateCallCollect.setOnClickListener(v->{
            startActivity(new Intent(ctx, CallCollectActivity.class));
        });

        donateEasypaisa.setOnClickListener(v->{
            startActivity(new Intent(ctx, DonateEasypaisaActivity.class));
        });
    }



}
