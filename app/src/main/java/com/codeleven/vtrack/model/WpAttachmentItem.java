package com.codeleven.vtrack.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class WpAttachmentItem implements Serializable {

	@SerializedName("href")
	private String href;

	public void setHref(String href){
		this.href = href;
	}

	public String getHref(){
		return href;
	}

	@Override
 	public String toString(){
		return 
			"WpAttachmentItem{" + 
			"href = '" + href + '\'' + 
			"}";
		}
}