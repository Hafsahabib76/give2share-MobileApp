
package com.se17.give2shareapplication.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemDonation {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("donor_id")
    @Expose
    private String donorId;
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

    /**
     * No args constructor for use in serialization
     *
     */
    public ItemDonation () {
    }
    /**
     *
     * @param image
     * @param condition
     * @param quantity
     * @param donorId
     * @param title
     * @param productCategory
     * @param desc
     */
    public ItemDonation(String donorId, String productCategory, String title, String desc, Integer quantity, String condition, String image) {
        super();
        this.donorId = donorId;
        this.productCategory = productCategory;
        this.title = title;
        this.desc = desc;
        this.quantity = quantity;
        this.condition = condition;
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDonorId() {
        return donorId;
    }

    public void setDonorId(String donorId) {
        this.donorId = donorId;
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

}
