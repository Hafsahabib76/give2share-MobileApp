package com.se17.give2shareapplication.Activities.Donor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.se17.give2shareapplication.Model.GlideApp;
import com.se17.give2shareapplication.Model.ItemGlideApp;
import com.se17.give2shareapplication.R;

public class SingleSharingActivity extends AppCompatActivity {

    private TextView singleTitle, singleCondition, singleCategory, singleDesc, singleQuantity;
    private ImageView singleImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_sharing);

        init();
    }

    private void init() {

        singleTitle = findViewById(R.id.singleTitle);
        singleCategory = findViewById(R.id.singleCategory);
        singleCondition = findViewById(R.id.singleCondition);
        singleDesc = findViewById(R.id.singleDesc);
        singleQuantity = findViewById(R.id.singleQuantity);
        singleImage = findViewById(R.id.singleImage);

        //get intent
        Intent intent = getIntent();

        String title = intent.getStringExtra("title");
        String image = intent.getStringExtra("image");
        String category = intent.getStringExtra("category");
        String condition = intent.getStringExtra("condition");
        String desc = intent.getStringExtra("description");
        String quantity = intent.getStringExtra("quantity");

        //set data
        singleTitle.setText(title);
        singleCategory.setText(category);
        singleCondition.setText(condition);
        singleQuantity.setText(quantity);
        singleDesc.setText(desc);

        GlideApp.with(this)
                .load(image)
                .into(singleImage);

    }
}
