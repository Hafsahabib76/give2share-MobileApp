package com.se17.give2shareapplication.Api;

import com.se17.give2shareapplication.Model.UserLogin;
import com.se17.give2shareapplication.Model.UserLoginResponse;
import com.se17.give2shareapplication.Model.UserRegisterResponse;

import java.util.Map;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("register")
    @FormUrlEncoded
    Call<UserRegisterResponse> getUserRegister(
            @FieldMap Map<String,String> fields
    );

    @POST("login")
    @FormUrlEncoded
    Call<UserLoginResponse> getUserLogin(
            @FieldMap Map<String,String> params
    );


}
