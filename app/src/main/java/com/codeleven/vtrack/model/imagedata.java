package com.codeleven.vtrack.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by CodelevenPC on 04-Aug-18.
 */

public class imagedata implements Serializable
{

    @SerializedName("img_id")
    @Expose
    private String imgId;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("imageurl")
    @Expose
    private String imageurl;
    private final static long serialVersionUID = -5551177427368929451L;

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

}