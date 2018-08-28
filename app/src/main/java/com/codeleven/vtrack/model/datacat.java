
package com.codeleven.vtrack.model;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class datacat implements Serializable
{

    @SerializedName("icon_ids")
    @Expose
    private String iconIds;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("icon")
    @Expose
    private String icon;
    private final static long serialVersionUID = -8877674317928989722L;

    public String getIconIds() {
        return iconIds;
    }

    public void setIconIds(String iconIds) {
        this.iconIds = iconIds;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

}
