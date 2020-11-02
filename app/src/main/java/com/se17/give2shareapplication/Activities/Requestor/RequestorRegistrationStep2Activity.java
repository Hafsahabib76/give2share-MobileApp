package com.se17.give2shareapplication.Activities.Requestor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.kofigyan.stateprogressbar.StateProgressBar;
import com.se17.give2shareapplication.R;

public class RequestorRegistrationStep2Activity extends AppCompatActivity {

    String[] StepData = {"Step 1", "Step 2", "Step 3"};
    LinearLayout next, back;
    Context ctx= RequestorRegistrationStep2Activity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requestor_registration_step2);


        final StateProgressBar stateProgressBar = (StateProgressBar) findViewById(R.id.your_state_progress_bar_id);
        stateProgressBar.setStateDescriptionData(StepData);

        next = (LinearLayout) findViewById(R.id.lyt_next);
        back = (LinearLayout) findViewById(R.id.lyt_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.ONE);
                Intent in = new Intent(ctx, RequestorRegistrationStep1Activity.class);
                startActivity(in);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.THREE);
                Intent in = new Intent(ctx, RequestorRegistrationStep3Activity.class);
                startActivity(in);
            }
        });
    }
}
