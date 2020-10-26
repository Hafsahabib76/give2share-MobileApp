package com.se17.give2shareapplication.Activities.Donor;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.kofigyan.stateprogressbar.StateProgressBar;
import com.se17.give2shareapplication.Api.ApiInterface;
import com.se17.give2shareapplication.Api.ClientApi;
import com.se17.give2shareapplication.Model.ItemDonation;
import com.se17.give2shareapplication.R;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DonateItemStep2Activity extends AppCompatActivity {

    private Context ctx = DonateItemStep2Activity.this;
    //step1 progressbar code
    private String[] StepData = {"Title", "Details"};
    private Button donate;
    private Spinner categorySpinner;
    private RadioGroup conditionRadioGroup;
    private RadioButton selectedRadioButton;
    private EditText txtDescription;
    private ElegantNumberButton quantityVal;
    private ProgressDialog dialog;
    private Bitmap bitmap = null;
    private ApiInterface apiInterface;
    private String tag = "g2s";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_item_step2);
        init();
    }

    private void init() {

        categorySpinner = findViewById(R.id.catgeorySpinner);
        conditionRadioGroup = findViewById(R.id.condition_radio_group);
        txtDescription = findViewById(R.id.description);
        quantityVal = findViewById(R.id.quantity);

        dialog = new ProgressDialog(ctx);
        dialog.setCancelable(false);

        apiInterface = ClientApi.getClient().create(ApiInterface.class);

        //step2 progressbar code
        final StateProgressBar stateProgressBar = (StateProgressBar) findViewById(R.id.your_state_progress_bar_id);
        stateProgressBar.setStateDescriptionData(StepData);

        donate = (Button) findViewById(R.id.donateBtn);
        donate.setOnClickListener(v -> {
            stateProgressBar.setAllStatesCompleted(true);
            if (validate()){
                donateItem();
            }
        });

    }

    private boolean validate(){
        if (categorySpinner.getSelectedItem().equals("--Select Category--")){
            Toast.makeText(ctx, "Select Category", Toast.LENGTH_SHORT).show();
            return false; }

        if (txtDescription.getText().toString().isEmpty()){
            Toast.makeText(ctx, "Item description is required", Toast.LENGTH_SHORT).show();
            return false;}

        if (quantityVal.getNumber().equals("0")){
            Toast.makeText(ctx, "Item quantity is required", Toast.LENGTH_SHORT).show();
            return false; }

        return true;
    }

    private void donateItem(){

        dialog.setMessage("Donating...");
        dialog.show();

        Map<String,String> map = new HashMap<String, String>();

        //for title
        Bundle bundle = getIntent().getExtras();
        String title = bundle.getString("title");

        Intent intent = getIntent();
        byte[] bytes = getIntent().getByteArrayExtra("image");
        Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);


        //for category, description and quantity
        String category = categorySpinner.getSelectedItem().toString().trim();
        String description = txtDescription.getText().toString().trim();
        String quantity = quantityVal.getNumber().trim();

        //for condition
        int selectedId = conditionRadioGroup.getCheckedRadioButtonId();
        if (selectedId != -1) {
            selectedRadioButton = findViewById(selectedId);
            String condition = selectedRadioButton.getText().toString();
            map.put("condition", condition);
            Log.d(tag+"condition", condition);
        } else {
            Toast.makeText(ctx, "Select Condition", Toast.LENGTH_SHORT).show();
        }

        //for donorID
        SharedPreferences userPref = getSharedPreferences("user", MODE_PRIVATE);
        String donor_id = String.valueOf((userPref.getInt("donor_id", -1)));

        map.put("donor_id", donor_id);
        map.put("title", title);
        map.put("image", bitmapToString(bmp));
        map.put("product_category", category);
        map.put("desc", description);
        map.put("quantity", quantity);

        Log.d(tag+"donor_id", donor_id);
        Log.d(tag+"title", title);
        Log.d(tag+"product_category", category);
        Log.d(tag+"desc", description);
        Log.d(tag+"quantity", quantity);
        Log.d(tag+"image", bitmapToString(bmp));

        Call<ItemDonation> call = apiInterface.donateItem(map);

        call.enqueue(new Callback<ItemDonation>() {
            @Override
            public void onResponse(Call<ItemDonation> call, Response<ItemDonation> response) {

                if(response.isSuccessful())
                {
                    ItemDonation userResponse = response.body();
                    Log.d(tag+"code: ", String.valueOf(response.code()));
                    //if success
                    Toast.makeText(ctx, "Item Donation Successful", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Log.d(tag,response.raw().toString());
                    Toast.makeText(ctx, response.raw().toString(), Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<ItemDonation> call, Throwable t) {
                dialog.dismiss();
            }
        });

    }

    private String bitmapToString(Bitmap bitmap) {

        if (bitmap!=null){
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
            byte [] array = byteArrayOutputStream.toByteArray();
            return Base64.encodeToString(array,Base64.DEFAULT);
        }

        return "";
    }
}
