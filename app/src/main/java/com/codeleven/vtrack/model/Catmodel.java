
package com.codeleven.vtrack.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Catmodel implements Serializable
{

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<datacat> data = null;
    private final static long serialVersionUID = -8144193677766341278L;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<datacat> getData() {
        return data;
    }

    public void setData(List<datacat> data) {
        this.data = data;
    }

}
