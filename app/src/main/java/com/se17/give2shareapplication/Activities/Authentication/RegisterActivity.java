package com.se17.give2shareapplication.Activities.Authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.se17.give2shareapplication.Activities.Donor.DonorHomeActivity;
import com.se17.give2shareapplication.Api.ApiInterface;
import com.se17.give2shareapplication.Api.ClientApi;
import com.se17.give2shareapplication.Model.UserRegisterResponse;
import com.se17.give2shareapplication.R;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private Context ctx = RegisterActivity.this;
    private TextInputLayout layoutRole, layoutName, layoutEmail, layoutPhone, layoutCnic, layoutAddress, layoutPassword, layoutConfirmPassword;
    private TextInputEditText txtRole, txtName, txtEmail, txtPhone, txtCnic, txtAddress, txtPassword, txtConfirmPassword;
    private TextView txtLogin;
    private Button btnRegister;
    private ProgressDialog dialog;

    private ApiInterface apiInterface;
    private String tag = "bff";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }

    private void init() {
        layoutRole = findViewById(R.id.LayoutRoleRegister);
        layoutName = findViewById(R.id.LayoutNameRegister);
        layoutEmail = findViewById(R.id.LayoutEmailRegister);
        layoutPhone = findViewById(R.id.LayoutPhoneNoRegister);
        layoutCnic = findViewById(R.id.LayoutCnicRegister);
        layoutAddress = findViewById(R.id.LayoutAddressRegister);
        layoutPassword = findViewById(R.id.LayoutPasswordRegister);
        layoutConfirmPassword = findViewById(R.id.LayoutConfirmPasswordRegister);

        txtRole = findViewById(R.id.txtRoleRegister);
        txtName = findViewById(R.id.txtNameRegister);
        txtEmail = findViewById(R.id.txtEmailRegister);
        txtPhone = findViewById(R.id.txtPhoneNoRegister);
        txtCnic = findViewById(R.id.txtCnicRegister);
        txtAddress = findViewById(R.id.txtAddressRegister);
        txtPassword = findViewById(R.id.txtPasswordRegister);
        txtConfirmPassword = findViewById(R.id.txtConfirmPasswordRegister);
        txtLogin = findViewById(R.id.txtLogin);
        btnRegister = findViewById(R.id.btnRegister);
        dialog = new ProgressDialog(ctx);
        dialog.setCancelable(false);

        apiInterface = ClientApi.getClient().create(ApiInterface.class);

        txtLogin.setOnClickListener(v->{

            startActivity(new Intent(ctx, LoginActivity.class));
            finish();
        });

        btnRegister.setOnClickListener(v->{
            //validate fields

            if (validate()){
                getRegister();
            }
        });

        txtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!txtName.getText().toString().isEmpty()){
                    layoutName.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!txtEmail.getText().toString().isEmpty()){
                    layoutEmail.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!txtPhone.getText().toString().isEmpty() && txtPhone.getText().toString().length()==11){
                    layoutPhone.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtCnic.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!txtCnic.getText().toString().isEmpty() && txtCnic.getText().toString().length()==13){
                    layoutCnic.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!txtAddress.getText().toString().isEmpty()){
                    layoutAddress.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (txtPassword.getText().toString().length()>7){
                    layoutPassword.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtConfirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (txtConfirmPassword.getText().toString().equals(txtPassword.getText().toString())){
                    layoutConfirmPassword.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    private boolean validate() {
        if (txtName.getText().toString().isEmpty()){
            layoutName.setErrorEnabled(true);
            layoutName.setError("Name is Required");
            return false;
        }

        if (txtEmail.getText().toString().isEmpty()){
            layoutEmail.setErrorEnabled(true);
            layoutEmail.setError("Email is Required");
            return false;
        }

        if (txtPhone.getText().toString().isEmpty()) {
            layoutPhone.setErrorEnabled(true);
            layoutPhone.setError("Phone Number is Required");
            return false;
        }

        if (txtPhone.getText().toString().isEmpty()) {
            layoutPhone.setErrorEnabled(true);
            layoutPhone.setError("CNIC Number is required");
            return false;
        }

        if (txtCnic.getText().toString().length()<13 || txtPhone.getText().toString().length()>13) {
            layoutCnic.setErrorEnabled(true);
            layoutCnic.setError("Enter Valid CNIC Number");
            return false;
        }

        if (txtCnic.getText().toString().length()<13 || txtPhone.getText().toString().length()>13) {
            layoutCnic.setErrorEnabled(true);
            layoutCnic.setError("Enter Valid CNIC Number");
            return false;
        }

        if (txtAddress.getText().toString().isEmpty()) {
            layoutAddress.setErrorEnabled(true);
            layoutAddress.setError("Address is required");
            return false;
        }

        if(txtPassword.getText().toString().length()<8){
            layoutPassword.setErrorEnabled(true);
            layoutPassword.setError("Atleast 8 Characters Required");
            return false;
        }

        if(!txtConfirmPassword.getText().toString().equals(txtPassword.getText().toString())){
            layoutConfirmPassword.setErrorEnabled(true);
            layoutConfirmPassword.setError("Password does not match");
            return false;
        }

        return true;
    }

    private void getRegister(){
        dialog.setMessage("Registering");
        dialog.show();

        Map<String,String> fields = new HashMap<String, String>();

        String name = txtName.getText().toString().trim();
        String email = txtEmail.getText().toString().trim();
        String phone_no = txtPhone.getText().toString().trim();
        String cnic = txtCnic.getText().toString().trim();
        String address = txtAddress.getText().toString().trim();
        String password = txtPassword.getText().toString().trim();

        fields.put("name", name);
        fields.put("email", email);
        fields.put("phone_no", phone_no);
        fields.put("cnic", cnic);
        fields.put("address", address);
        fields.put("password", password);


        Call<UserRegisterResponse> call= apiInterface.getUserRegister(fields);

        call.enqueue(new Callback<UserRegisterResponse>() {
            @Override
            public void onResponse(Call<UserRegisterResponse> call, Response<UserRegisterResponse> response) {

                if(response.isSuccessful())
                {

                    UserRegisterResponse userResponse = response.body();

                    //make shared preference user
                    SharedPreferences userPref = ctx.getApplicationContext().getSharedPreferences("user", ctx.MODE_PRIVATE);
                    SharedPreferences.Editor editor = userPref.edit();
                    editor.putString("token", userResponse.getAccessToken());
                    editor.putBoolean("isLoggedIn",true);
                    editor.apply();

                    //if success
                    startActivity(new Intent(ctx , LoginActivity.class));
                    finish();
                    Toast.makeText(RegisterActivity.this, "Register Success", Toast.LENGTH_LONG).show();
                }
                else {
                    Log.d(tag,response.raw().toString());
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<UserRegisterResponse> call, Throwable t) {
                dialog.dismiss();
            }
        });
    }
}
