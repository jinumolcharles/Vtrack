package com.codeleven.vtrack.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by CodelevenPC on 22-Aug-18.
 */

public class locationmodel implements Serializable
{

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private locationdata data;
    private final static long serialVersionUID = -2099262984510029772L;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public locationdata getData() {
        return data;
    }

    public void setData(locationdata data) {
        this.data = data;
    }

}


