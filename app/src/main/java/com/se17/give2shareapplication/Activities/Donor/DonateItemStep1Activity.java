package com.se17.give2shareapplication.Activities.Donor;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kofigyan.stateprogressbar.StateProgressBar;
import com.se17.give2shareapplication.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class DonateItemStep1Activity extends AppCompatActivity {

    private Context ctx = DonateItemStep1Activity.this;
    //step1 progressbar code
    private String[] StepData = {"Title", "Details"};
    private Button next, selectImgBtn;
    private ImageView itemImage;
    private EditText txtTitle;
    private static final int GALLERY_DONATE_ITEM = 2;
    private Bitmap bitmap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_item_step1);
        init();
    }

    private void init() {

        selectImgBtn = findViewById(R.id.SelectImageBtn);
        itemImage = findViewById(R.id.donateitemImg);
        txtTitle = findViewById(R.id.title);


        //step1 progressbar code
        final StateProgressBar stateProgressBar = (StateProgressBar) findViewById(R.id.your_state_progress_bar_id);
        stateProgressBar.setStateDescriptionData(StepData);

        next = (Button) findViewById(R.id.nextBtn);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Validate()) {
                    String title = txtTitle.getText().toString();

                    Intent in = new Intent(ctx, DonateItemStep2Activity.class);

                    //Create the bundle
                    Bundle b = new Bundle();
                    b.putString("title", title);
                    in.putExtras(b);

                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                    byte[] bytes = stream.toByteArray();
                    in.putExtra("image", bytes);
                    startActivity(in);

                }

            }
        });

        //select image btn working to open gallery
        selectImgBtn.setOnClickListener(v->{
            Intent i = new Intent(Intent.ACTION_PICK);
            i.setType("image/*");
            startActivityForResult(i,GALLERY_DONATE_ITEM);
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==GALLERY_DONATE_ITEM && resultCode==RESULT_OK) {
            Uri imgUri = data.getData();
            itemImage.setVisibility(View.VISIBLE);
            itemImage.setImageURI(imgUri);
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imgUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean Validate(){
        if(txtTitle.getText().toString().isEmpty()) {
            Toast.makeText(ctx, "Title is required", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (itemImage == null){
            Toast.makeText(ctx, "Select Image from the gallery", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
