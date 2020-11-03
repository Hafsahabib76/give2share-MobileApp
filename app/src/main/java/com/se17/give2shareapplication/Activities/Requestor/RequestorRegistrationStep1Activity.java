package com.se17.give2shareapplication.Activities.Requestor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.LinearLayout;

import com.kofigyan.stateprogressbar.StateProgressBar;
import com.se17.give2shareapplication.R;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class RequestorRegistrationStep1Activity extends AppCompatActivity {

    //step1 progressbar code
    String[] StepData = {"Step 1", "Step 2", "Step 3"};
    LinearLayout next;
    Context ctx= RequestorRegistrationStep1Activity.this;

    //profileImage Code...
    private CircleImageView ProfileImage;
    private static final int PICK_IMAGE = 1;
    Uri imageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requestor_registration_step1);

        //step1 progressbar code
        final StateProgressBar stateProgressBar = (StateProgressBar) findViewById(R.id.your_state_progress_bar_id);
        stateProgressBar.setStateDescriptionData(StepData);

        next = (LinearLayout) findViewById(R.id.lyt_next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.TWO);
                Intent in = new Intent(ctx, RequestorRegistrationStep2Activity.class);
                startActivity(in);
            }
        });

        //profileImage Code......
        ProfileImage = (CircleImageView) findViewById(R.id.profile_image);
        ProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(gallery, "Select Picture"), PICK_IMAGE);
            }
        });
    }
    //For Profile Image Code..........
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                ProfileImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
