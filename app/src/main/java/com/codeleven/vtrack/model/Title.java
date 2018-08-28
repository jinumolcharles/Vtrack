package com.codeleven.vtrack.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Title implements Serializable {

	@SerializedName("rendered")
	private String rendered;

	public void setRendered(String rendered){
		this.rendered = rendered;
	}

	public String getRendered(){
		return rendered;
	}

	@Override
 	public String toString(){
		return 
			"Title{" + 
			"rendered = '" + rendered + '\'' + 
			"}";
		}
}