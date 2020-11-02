
package com.se17.give2shareapplication.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserLogin{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone_no")
    @Expose
    private String phoneNo;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("cnic")
    @Expose
    private String cnic;
    @SerializedName("address")
    @Expose
    private Object address;
    @SerializedName("api_token")
    @Expose
    private Object apiToken;
    @SerializedName("email_verified_at")
    @Expose
    private Object emailVerifiedAt;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    /**
     * No args constructor for use in serialization
     * 
     */
    public UserLogin() {
    }

    /**
     * 
     * @param createdAt
     * @param role
     * @param address
     * @param apiToken
     * @param emailVerifiedAt
     * @param name
     * @param id
     * @param cnic
     * @param email
     * @param phoneNo
     * @param updatedAt
     */
    public UserLogin(Integer id, String name, String email, String phoneNo, String cnic, Object address, Object apiToken, Object emailVerifiedAt, String createdAt, String updatedAt, String role) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
        this.cnic = cnic;
        this.address = address;
        this.apiToken = apiToken;
        this.emailVerifiedAt = emailVerifiedAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public Object getAddress() {
        return address;
    }

    public void setAddress(Object address) {
        this.address = address;
    }

    public Object getApiToken() {
        return apiToken;
    }

    public void setApiToken(Object apiToken) {
        this.apiToken = apiToken;
    }

    public Object getEmailVerifiedAt() {
        return emailVerifiedAt;
    }

    public void setEmailVerifiedAt(Object emailVerifiedAt) {
        this.emailVerifiedAt = emailVerifiedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}
