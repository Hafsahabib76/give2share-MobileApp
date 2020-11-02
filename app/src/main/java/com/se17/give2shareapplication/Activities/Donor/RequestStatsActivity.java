package com.se17.give2shareapplication.Activities.Donor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.se17.give2shareapplication.Constant;
import com.se17.give2shareapplication.R;

public class RequestStatsActivity extends AppCompatActivity {

    private Context ctx = RequestStatsActivity.this;
    private LinearLayout pending, approve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_stats);

        init();
    }

    private void init() {

        pending = findViewById(R.id.LinearWaiting);
        approve = findViewById(R.id.LinearStatus);

        //get intent
        Intent intent = getIntent();

        String status = intent.getStringExtra("status");

        if( status.equalsIgnoreCase("approve") ){
            approve.setVisibility(View.VISIBLE);
        }else{
            pending.setVisibility(View.VISIBLE);
        }
    }

}
