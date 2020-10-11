package com.se17.give2shareapplication.Authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.se17.give2shareapplication.Constant;
import com.se17.give2shareapplication.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private Context ctx = LoginActivity.this;
    private TextInputLayout layoutEmail, layoutPassword;
    private TextInputEditText txtEmail, txtPassword;
    private TextView txtRegister;
    private Button btnLogin;
    private ProgressDialog dialog;


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

        txtRegister.setOnClickListener(v->{
            startActivity(new Intent(ctx, RegisterActivity.class));
            finish();
        });

        btnLogin.setOnClickListener(v->{
            //validate fields

            if (validate()){
                login();
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

    private void login() {
        dialog.setMessage("Logging in");
        dialog.show();
        StringRequest request = new StringRequest(Request.Method.POST, Constant.LOGIN, response -> {
            //we get response if connection success
            try {
                JSONObject object = new JSONObject(response);
                if (object.getBoolean("success")){
                    JSONObject user = object.getJSONObject("user");

                    //make shared preference user
                    SharedPreferences userPref = getApplicationContext().getSharedPreferences("user", ctx.MODE_PRIVATE);
                    SharedPreferences.Editor editor = userPref.edit();
                    editor.putString("token", object.getString("token"));
                    editor.putString("fullname", user.getString("fullname"));
                    editor.apply();

                    //if success
                    Toast.makeText(ctx, "Login Success", Toast.LENGTH_SHORT).show();
                }

            }catch (JSONException e){
                e.printStackTrace();
                dialog.dismiss();

            }
        }, error -> {
            //error if connection not success
            error.printStackTrace();
            dialog.dismiss();

        }) {

            //add parameters

            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put("email", txtEmail.getText().toString().trim());
                map.put("password", txtPassword.getText().toString());
                return map;
            }

        };

        //add this request to requestqueue
        RequestQueue queue = Volley.newRequestQueue(ctx);
        queue.add(request);
    }


}
