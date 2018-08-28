package com.codeleven.vtrack.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by CodelevenPC on 03-Aug-18.
 */

public class royalmodel implements Serializable
{

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<royaldata> data = null;
    private final static long serialVersionUID = 5167236952232811223L;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<royaldata> getData() {
        return data;
    }

    public void setData(List<royaldata> data) {
        this.data = data;
    }

}