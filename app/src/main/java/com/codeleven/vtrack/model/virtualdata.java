package com.codeleven.vtrack.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by CodelevenPC on 04-Aug-18.
 */

public class virtualdata implements Serializable
{

    @SerializedName("link")
    @Expose
    private String link;
    private final static long serialVersionUID = -8625950555465719120L;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
