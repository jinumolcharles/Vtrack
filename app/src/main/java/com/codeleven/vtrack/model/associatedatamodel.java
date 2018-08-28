package com.codeleven.vtrack.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by CodelevenPC on 10-Aug-18.
 */

public class associatedatamodel implements Serializable
{

    @SerializedName("associate_id")
    @Expose
    private String associateId;
    @SerializedName("associate")
    @Expose
    private String associate;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("link")
    @Expose
    private String link;
    private final static long serialVersionUID = -219874631940865131L;

    public String getAssociateId() {
        return associateId;
    }

    public void setAssociateId(String associateId) {
        this.associateId = associateId;
    }

    public String getAssociate() {
        return associate;
    }

    public void setAssociate(String associate) {
        this.associate = associate;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
