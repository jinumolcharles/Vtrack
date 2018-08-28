
package com.codeleven.vtrack.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class BanquetActivities implements Serializable
{

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<com.codeleven.vtrack.model.Datum> data = null;
    private final static long serialVersionUID = -7835578815877009252L;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<com.codeleven.vtrack.model.Datum> getData() {
        return data;
    }

    public void setData(List<com.codeleven.vtrack.model.Datum> data) {
        this.data = data;
    }

}
