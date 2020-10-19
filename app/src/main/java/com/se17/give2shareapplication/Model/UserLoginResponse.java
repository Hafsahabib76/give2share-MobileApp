
package com.se17.give2shareapplication.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserLoginResponse{

    @SerializedName("user")
    @Expose
    private UserLogin user;
    @SerializedName("access_token")
    @Expose
    private String accessToken;

    /**
     * No args constructor for use in serialization
     * 
     */
    public UserLoginResponse() {
    }

    /**
     * 
     * @param accessToken
     * @param user
     */
    public UserLoginResponse(UserLogin user, String accessToken) {
        super();
        this.user = user;
        this.accessToken = accessToken;
    }

    public UserLogin getUser() {
        return user;
    }

    public void setUser(UserLogin user) {
        this.user = user;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

}
