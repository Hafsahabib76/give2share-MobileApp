package com.se17.give2shareapplication.Activities.Donor;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.se17.give2shareapplication.R;

import java.io.IOException;

public class DonorEditProfileActivity extends AppCompatActivity {

    private EditText etFullName, etAddress, etEmail, etCNIC, etPhone;
    private Spinner gender;
    private SharedPreferences userPref;
    private TextView changeProfileImg;
    private static final int PROFILE_IMG = 10;
    private Bitmap bitmap;
    ImageView profileImage;
    private Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_edit_profile);

        init();
    }

    private void init() {

        //change profile code
        profileImage = findViewById(R.id.profileImg);
        changeProfileImg = findViewById(R.id.chngProfileImg);

        changeProfileImg.setOnClickListener(v -> {
            Intent i = new Intent(Intent.ACTION_PICK);
            i.setType("image/*");
            startActivityForResult(i, PROFILE_IMG);
        });

        etFullName = findViewById(R.id.editFullName);
        etEmail = findViewById(R.id.editEmail);
        etCNIC = findViewById(R.id.editCNIC);
        etPhone = findViewById(R.id.editPhone);
        etAddress = findViewById(R.id.editAddress);

        //passing data through shared preferences
        userPref = getSharedPreferences("user", MODE_PRIVATE);

        String fullName = userPref.getString("name", "");
        String email = userPref.getString("email", "");
        String cnic = userPref.getString("cnic", "");
        String phone = userPref.getString("phone_no", "");

        etFullName.setText(fullName);
        etEmail.setText(email);
        etPhone.setText(phone);
        etCNIC.setText(cnic);

        //button cancel to go back to previous page
        btnCancel = findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(v -> {
            startActivity(new Intent (this,DonorProfileActivity.class));
            finish();
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PROFILE_IMG && resultCode==RESULT_OK) {
            Uri imgUri = data.getData();
            profileImage.setVisibility(View.VISIBLE);
            profileImage.setImageURI(imgUri);
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imgUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
