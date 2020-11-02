
package com.se17.give2shareapplication.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Items {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("donor_id")
    @Expose
    private Integer donorId;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("product_category")
    @Expose
    private String productCategory;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("condition")
    @Expose
    private String condition;
    @SerializedName("image")
    @Expose
    private String image;
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
    public Items() {
    }

    /**
     * 
     * @param image
     * @param createdAt
     * @param condition
     * @param quantity
     * @param donorId
     * @param id
     * @param title
     * @param status
     * @param productCategory
     * @param desc
     * @param updatedAt
     */
    public Items(Integer id, Integer donorId, String status, String productCategory, String title, String desc, Integer quantity, String condition, String image, String createdAt, String updatedAt) {
        super();
        this.id = id;
        this.donorId = donorId;
        this.status = status;
        this.productCategory = productCategory;
        this.title = title;
        this.desc = desc;
        this.quantity = quantity;
        this.condition = condition;
        this.image = image;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDonorId() {
        return donorId;
    }

    public void setDonorId(Integer donorId) {
        this.donorId = donorId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
