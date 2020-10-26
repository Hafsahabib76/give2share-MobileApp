package com.se17.give2shareapplication.Api;


import com.se17.give2shareapplication.Model.ItemDonation;
import com.se17.give2shareapplication.Model.Items;
import com.se17.give2shareapplication.Model.UserLoginResponse;
import com.se17.give2shareapplication.Model.UserRegisterResponse;

import java.util.List;
import java.util.Map;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
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

    @Headers("Content-Type: application/json")


    @POST("donateItem")
    @FormUrlEncoded
    Call<ItemDonation> donateItem(
            @FieldMap Map<String,String> map
    );

    @GET("donate")
    Call<List<Items>> getItems();


}
