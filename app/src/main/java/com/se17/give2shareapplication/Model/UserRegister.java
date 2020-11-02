
package com.se17.give2shareapplication.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserRegister {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone_no")
    @Expose
    private String phoneNo;
    @SerializedName("cnic")
    @Expose
    private String cnic;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("id")
    @Expose
    private Integer id;

    private String role;

    /**
     * No args constructor for use in serialization
     * 
     */
    public UserRegister() {
    }

    /**
     * 
     * @param createdAt
     * @param name
     * @param cnic
     * @param id
     * @param email
     * @param phoneNo
     * @param updatedAt
     */
    public UserRegister(String name, String email, String phoneNo, String cnic, String updatedAt, String createdAt, Integer id, String role) {
        super();
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
        this.cnic = cnic;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.id = id;
        this.role = role;
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

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
