package com.se17.give2shareapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.se17.give2shareapplication.Api.ApiInterface;
import com.se17.give2shareapplication.Api.ClientApi;
import com.se17.give2shareapplication.Model.ItemDonation;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class testActivity extends AppCompatActivity {

    private Button selectImgBtn;
    private ImageView itemImage;
    private EditText txtTitle;
    private static final int GALLERY_DONATE_ITEM = 2;
    private Bitmap bitmap = null;

    private Button donate;
    private Spinner categorySpinner;
    private RadioGroup conditionRadioGroup;
    private RadioButton selectedRadioButton;
    private EditText txtDescription;
    private ElegantNumberButton quantityVal;
    private ProgressDialog dialog;
    private ApiInterface apiInterface;
    private String tag = "g2s";

    private Uri fileUri;
    private String postPath;
    private String mediaPath;
    private String mImageFileLocation = "";
    public static final String IMAGE_DIRECTORY_NAME = "Android File Upload";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        init();
    }

    private void init() {
        selectImgBtn = findViewById(R.id.SelectImageBtn);
        itemImage = findViewById(R.id.donateitemImg);
        txtTitle = findViewById(R.id.title);

        //select image btn working to open gallery
        selectImgBtn.setOnClickListener(v->{
            Intent i = new Intent(Intent.ACTION_PICK);
            i.setType("image/*");
            startActivityForResult(i,GALLERY_DONATE_ITEM);
        });

        categorySpinner = findViewById(R.id.catgeorySpinner);
        conditionRadioGroup = findViewById(R.id.condition_radio_group);
        txtDescription = findViewById(R.id.description);
        quantityVal = findViewById(R.id.quantity);

        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);


        apiInterface = ClientApi.getClient().create(ApiInterface.class);

        donate = (Button) findViewById(R.id.donateBtn);
        donate.setOnClickListener(v -> {

                donateItem();
        });
    }

    private void donateItem(){

        dialog.setMessage("Donating...");
        dialog.show();


        Map<String,String> map = new HashMap<String, String>();

        File file = new File(postPath);

        // Parsing any Media type file
        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);

        //for category, description and quantity
        String title = txtTitle.getText().toString().trim();
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
            Toast.makeText(this, "Select Condition", Toast.LENGTH_SHORT).show();
        }

        //for donorID
        SharedPreferences userPref = getSharedPreferences("user", MODE_PRIVATE);
        String donor_id = String.valueOf((userPref.getInt("donor_id", -1)));

        map.put("donor_id", donor_id);
        map.put("title", title);
        map.put("image", String.valueOf(requestBody));
        map.put("product_category", category);
        map.put("desc", description);
        map.put("quantity", quantity);

        Log.d(tag+"donor_id", donor_id);
        Log.d(tag+"title", title);
        Log.d(tag+"product_category", category);
        Log.d(tag+"desc", description);
        Log.d(tag+"quantity", quantity);
        Log.d(tag+"image", String.valueOf(requestBody));

        Call<ItemDonation> call = apiInterface.donateItem(map);

        call.enqueue(new Callback<ItemDonation>() {
            @Override
            public void onResponse(Call<ItemDonation> call, Response<ItemDonation> response) {

                if(response.isSuccessful())
                {
                    ItemDonation userResponse = response.body();
                    Log.d(tag+"code: ", String.valueOf(response.code()));
                    //if success
                    Toast.makeText(testActivity.this, "Item Donation Successful", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Log.d(tag,response.raw().toString());
                    Toast.makeText(testActivity.this, response.raw().toString(), Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<ItemDonation> call, Throwable t) {
                dialog.dismiss();
                Log.e("Response",t.getMessage());
            }
        });

    }

    private String bitmapToString(Bitmap bitmap) {
        if (bitmap!=null){
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
            String image = Base64.encodeToString(byteArrayOutputStream.toByteArray(),Base64.DEFAULT);
            return image;
        }

        return "";
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==GALLERY_DONATE_ITEM && resultCode==RESULT_OK) {
            Uri imgUri = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(imgUri, filePathColumn, null, null, null);
            assert cursor != null;
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            mediaPath = cursor.getString(columnIndex);
            // Set the Image in ImageView for Previewing the Media
            itemImage.setVisibility(View.VISIBLE);
            itemImage.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
            cursor.close();

            postPath = mediaPath;
        }
    }

    File createImageFile() throws IOException {
        Logger.getAnonymousLogger().info("Generating the image - method started");

        // Here we create a "non-collision file name", alternatively said, "an unique filename" using the "timeStamp" functionality
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmSS").format(new Date());
        String imageFileName = "IMAGE_" + timeStamp;
        // Here we specify the environment location and the exact path where we want to save the so-created file
        File storageDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES + "/givetoshare_app");
        Logger.getAnonymousLogger().info("Storage directory set");

        // Then we create the storage directory if does not exists
        if (!storageDirectory.exists()) storageDirectory.mkdir();

        // Here we create the file using a prefix, a suffix and a directory
        File image = new File(storageDirectory, imageFileName + ".jpg");
        // File image = File.createTempFile(imageFileName, ".jpg", storageDirectory);

        // Here the location is saved into the string mImageFileLocation
        Logger.getAnonymousLogger().info("File name and path set");

        mImageFileLocation = image.getAbsolutePath();
        // fileUri = Uri.parse(mImageFileLocation);
        // The file is returned to the previous intent across the camera application
        return image;
    }




}
