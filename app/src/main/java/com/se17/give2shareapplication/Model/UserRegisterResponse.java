
package com.se17.give2shareapplication.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserRegisterResponse {

    @SerializedName("user")
    @Expose
    private UserRegister user;
    @SerializedName("access_token")
    @Expose
    private String accessToken;

    /**
     * No args constructor for use in serialization
     * 
     */
    public UserRegisterResponse() {
    }

    /**
     * 
     * @param accessToken
     * @param user
     */
    public UserRegisterResponse(UserRegister user, String accessToken) {
        super();
        this.user = user;
        this.accessToken = accessToken;
    }

    public UserRegister getUser() {
        return user;
    }

    public void setUser(UserRegister user) {
        this.user = user;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

}
