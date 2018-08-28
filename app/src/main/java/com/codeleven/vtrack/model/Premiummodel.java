package com.codeleven.vtrack.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by CodelevenPC on 03-Aug-18.
 */

public class Premiummodel implements Serializable
{

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<PreData> data = null;
    private final static long serialVersionUID = -7041239647431227477L;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<PreData> getData() {
        return data;
    }

    public void setData(List<PreData> data) {
        this.data = data;
    }

}
