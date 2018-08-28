package com.codeleven.vtrack.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

 public class Links implements Serializable {

	@SerializedName("curies")
	private List<CuriesItem> curies;

	@SerializedName("replies")
	private List<com.codeleven.vtrack.model.RepliesItem> replies;

	@SerializedName("about")
	private List<AboutItem> about;

	@SerializedName("self")
	private List<com.codeleven.vtrack.model.SelfItem> self;

	@SerializedName("collection")
	private List<CollectionItem> collection;

	@SerializedName("wp:attachment")
	private List<com.codeleven.vtrack.model.WpAttachmentItem> wpAttachment;

	public void setCuries(List<CuriesItem> curies){
		this.curies = curies;
	}

	public List<CuriesItem> getCuries(){
		return curies;
	}

	public void setReplies(List<com.codeleven.vtrack.model.RepliesItem> replies){
		this.replies = replies;
	}

	public List<com.codeleven.vtrack.model.RepliesItem> getReplies(){
		return replies;
	}

	public void setAbout(List<AboutItem> about){
		this.about = about;
	}

	public List<AboutItem> getAbout(){
		return about;
	}

	public void setSelf(List<com.codeleven.vtrack.model.SelfItem> self){
		this.self = self;
	}

	public List<com.codeleven.vtrack.model.SelfItem> getSelf(){
		return self;
	}

	public void setCollection(List<CollectionItem> collection){
		this.collection = collection;
	}

	public List<CollectionItem> getCollection(){
		return collection;
	}

	public void setWpAttachment(List<com.codeleven.vtrack.model.WpAttachmentItem> wpAttachment){
		this.wpAttachment = wpAttachment;
	}

	public List<com.codeleven.vtrack.model.WpAttachmentItem> getWpAttachment(){
		return wpAttachment;
	}

	@Override
 	public String toString(){
		return 
			"Links{" + 
			"curies = '" + curies + '\'' + 
			",replies = '" + replies + '\'' + 
			",about = '" + about + '\'' + 
			",self = '" + self + '\'' + 
			",collection = '" + collection + '\'' + 
			",wp:attachment = '" + wpAttachment + '\'' + 
			"}";
		}
}