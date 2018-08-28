package com.codeleven.vtrack.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by CodelevenPC on 04-Aug-18.
 */

public class imagemodel  implements Serializable
{

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<imagedata> data = null;
    private final static long serialVersionUID = -3006143093084030103L;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<imagedata> getData() {
        return data;
    }

    public void setData(List<imagedata> data) {
        this.data = data;
    }

}
