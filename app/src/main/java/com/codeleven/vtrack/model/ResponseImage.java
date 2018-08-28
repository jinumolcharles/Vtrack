package com.codeleven.vtrack.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ResponseImage implements Serializable {

	@SerializedName("result")
	private String result;

	@SerializedName("id")
	private String id;

	public void setResult(String result){
		this.result = result;
	}

	public String getResult(){
		return result;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"ResponseImage{" + 
			"result = '" + result + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}