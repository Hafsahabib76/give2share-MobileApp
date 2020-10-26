package com.se17.give2shareapplication.Activities.Authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.se17.give2shareapplication.Activities.Donor.DonorHomeActivity;
import com.se17.give2shareapplication.Api.ApiInterface;
import com.se17.give2shareapplication.Api.ClientApi;
import com.se17.give2shareapplication.Model.UserLoginResponse;
import com.se17.give2shareapplication.R;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private Context ctx = LoginActivity.this;
    private TextInputLayout layoutEmail, layoutPassword;
    private TextInputEditText txtEmail, txtPassword;
    private TextView txtRegister;
    private Button btnLogin;
    private ProgressDialog dialog;

    private ApiInterface apiInterface;
    private String tag = "give2share";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {

        layoutEmail = findViewById(R.id.LayoutEmailLogin);
        layoutPassword = findViewById(R.id.LayoutPasswordLogin);
        txtEmail = findViewById(R.id.txtEmailLogin);
        txtPassword = findViewById(R.id.txtPasswordLogin);
        txtRegister = findViewById(R.id.txtRegister);
        btnLogin = findViewById(R.id.btnLogin);
        dialog = new ProgressDialog(ctx);
        dialog.setCancelable(false);

        apiInterface= ClientApi.getClient().create(ApiInterface.class);

        txtRegister.setOnClickListener(v->{
            startActivity(new Intent(ctx, RegisterActivity.class));
            finish();
        });

        btnLogin.setOnClickListener(v->{
            //validate fields

            if (validate()){
                getLogin();
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
    }

    private boolean validate() {
        if (txtEmail.getText().toString().isEmpty()){
            layoutEmail.setErrorEnabled(true);
            layoutEmail.setError("Email is Required");
            return false;
        }

        if(txtPassword.getText().toString().length()<8){
            layoutPassword.setErrorEnabled(true);
            layoutPassword.setError("Atleast 8 Characters Required");
            return false;
        }

        return true;
    }

    private void getLogin(){
        dialog.setMessage("Logging in...");
        dialog.show();

        Map<String,String> params = new HashMap<String, String>();

        String email = txtEmail.getText().toString().trim();
        String password = txtPassword.getText().toString().trim();

        params.put("email", email);
        params.put("password", password);

        Call<UserLoginResponse> call= apiInterface.getUserLogin(params);

        call.enqueue(new Callback<UserLoginResponse>() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onResponse(Call<UserLoginResponse> call, Response<UserLoginResponse> response) {

                if(response.isSuccessful())
                {
                    UserLoginResponse userResponse=response.body();

                    //make shared preference user
                    SharedPreferences userPref = ctx.getApplicationContext().getSharedPreferences("user", ctx.MODE_PRIVATE);
                    SharedPreferences.Editor editor = userPref.edit();
                    editor.putString("token", userResponse.getAccessToken());
                    editor.putInt("donor_id", userResponse.getUser().getId());
                    editor.putString("name", userResponse.getUser().getName());
                    editor.putString("email", userResponse.getUser().getEmail());
                    editor.putString("phone_no", userResponse.getUser().getPhoneNo());
                    editor.putString("cnic", userResponse.getUser().getCnic());
                    editor.putString("role", userResponse.getUser().getRole());
                    editor.putBoolean("isLoggedIn",true);
                    editor.apply();

                    //if success
                    startActivity(new Intent(ctx ,DonorHomeActivity.class));
                    finish();
                    Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();

                }
                else {
                    dialog.dismiss();
                    Toast toast = Toast.makeText(LoginActivity.this, R.string.auth_failed, Toast.LENGTH_LONG);
                    toast.getView().setBackgroundColor(Color.parseColor("#000000"));
                    TextView toastMessage=(TextView) toast.getView().findViewById(android.R.id.message);
                    toastMessage.setTextColor(Color.WHITE);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<UserLoginResponse> call, Throwable t) {
                dialog.dismiss();
            }
        });
    }


}
