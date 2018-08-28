package com.codeleven.vtrack.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by CodelevenPC on 03-Aug-18.
 */

public class royaldata implements Serializable
{

    @SerializedName("royalty_id")
    @Expose
    private String royaltyId;
    @SerializedName("royalty")
    @Expose
    private String royalty;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("link")
    @Expose
    private String link;
    private final static long serialVersionUID = 9157729936002883983L;

    public String getRoyaltyId() {
        return royaltyId;
    }

    public void setRoyaltyId(String royaltyId) {
        this.royaltyId = royaltyId;
    }

    public String getRoyalty() {
        return royalty;
    }

    public void setRoyalty(String royalty) {
        this.royalty = royalty;
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
