package com.codeleven.vtrack.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GalleryResponse implements Serializable{

	@SerializedName("date")
	private String date;

	@SerializedName("slider")
	private String slider;

	@SerializedName("_links")
	private com.codeleven.vtrack.model.Links links;

	@SerializedName("type")
	private String type;

//	@SerializedName("fw_options")
//	private String fwOptions;

	@SerializedName("modified")
	private String modified;

	@SerializedName("id")
	private int id;

	@SerializedName("slider_2_image")
	private String slider2Image;

	@SerializedName("slug")
	private String slug;

	@SerializedName("image_type")
	private String imageType;

	@SerializedName("show_pricing_fields")
	private String showPricingFields;

	@SerializedName("image")
	private String image;

	@SerializedName("deal_type")
	private String dealType;

	@SerializedName("old_price")
	private String oldPrice;

	@SerializedName("slider_1_image")
	private String slider1Image;

	@SerializedName("printable_coupon")
	private String printableCoupon;

	@SerializedName("new_price")
	private String newPrice;

	@SerializedName("stars_total")
	private String starsTotal;

	@SerializedName("ping_status")
	private String pingStatus;

	@SerializedName("ssd_post_views_count")
	private String ssdPostViewsCount;
//
//	@SerializedName("redirect_to_offer")
//	private String redirectToOffer;

	@SerializedName("print_image")
	private String printImage;

	@SerializedName("guid")
	private com.codeleven.vtrack.model.Guid guid;

	@SerializedName("geo_country_slug")
	private String geoCountrySlug;

	@SerializedName("registered_members_only")
	private String registeredMembersOnly;

	@SerializedName("show_location")
	private String showLocation;

	@SerializedName("status")
	private String status;

	@SerializedName("ssd_post_button_clicks_count")
	private String ssdPostButtonClicksCount;

	@SerializedName("template")
	private String template;

	@SerializedName("rating_count_total")
	private String ratingCountTotal;

	@SerializedName("header_image")
	private String headerImage;

	@SerializedName("rating_average")
	private String ratingAverage;

	@SerializedName("link")
	private String link;

	@SerializedName("save")
	private String save;

	@SerializedName("title")
	private com.codeleven.vtrack.model.Title title;

	@SerializedName("content")
	private Content content;

	@SerializedName("votes_count")
	private String votesCount;

	@SerializedName("geo_city")
	private String geoCity;

	@SerializedName("geo_city_slug")
	private String geoCitySlug;

	@SerializedName("company")
	private String company;

	@SerializedName("deal_layout")
	private String dealLayout;

	@SerializedName("date_gmt")
	private String dateGmt;

	@SerializedName("offer_url")
	private String offerUrl;

	@SerializedName("search_header")
	private String searchHeader;

	@SerializedName("virtual_deal")
	private String virtualDeal;

	@SerializedName("expiring_date")
	private String expiringDate;

	@SerializedName("coupon_code")
	private String couponCode;

	@SerializedName("modified_gmt")
	private String modifiedGmt;

	@SerializedName("discount_value")
	private String discountValue;

	@SerializedName("comment_status")
	private String commentStatus;

	@SerializedName("url")
	private String url;

	@SerializedName("limited_deal")
	private String limitedDeal;

	@SerializedName("slider_0_image")
	private String slider0Image;

	@SerializedName("geo_country")
	private String geoCountry;

	@SerializedName("map_zoom")
	private String mapZoom;

//	@SerializedName("location")
//	private String location;

	@SerializedName("deal_summary")
	private String dealSummary;

	@SerializedName("category")
	private String category;

	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return date;
	}

	public void setSlider(String slider){
		this.slider = slider;
	}

	public String getSlider(){
		return slider;
	}

	public void setLinks(com.codeleven.vtrack.model.Links links){
		this.links = links;
	}

	public com.codeleven.vtrack.model.Links getLinks(){
		return links;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

//	public void setFwOptions(String fwOptions){
//		this.fwOptions = fwOptions;
//	}
//
//	public String getFwOptions(){
//		return fwOptions;
//	}

	public void setModified(String modified){
		this.modified = modified;
	}

	public String getModified(){
		return modified;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setSlider2Image(String slider2Image){
		this.slider2Image = slider2Image;
	}

	public String getSlider2Image(){
		return slider2Image;
	}

	public void setSlug(String slug){
		this.slug = slug;
	}

	public String getSlug(){
		return slug;
	}

	public void setImageType(String imageType){
		this.imageType = imageType;
	}

	public String getImageType(){
		return imageType;
	}

	public void setShowPricingFields(String showPricingFields){
		this.showPricingFields = showPricingFields;
	}

	public String getShowPricingFields(){
		return showPricingFields;
	}

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setDealType(String dealType){
		this.dealType = dealType;
	}

	public String getDealType(){
		return dealType;
	}

	public void setOldPrice(String oldPrice){
		this.oldPrice = oldPrice;
	}

	public String getOldPrice(){
		return oldPrice;
	}

	public void setSlider1Image(String slider1Image){
		this.slider1Image = slider1Image;
	}

	public String getSlider1Image(){
		return slider1Image;
	}

	public void setPrintableCoupon(String printableCoupon){
		this.printableCoupon = printableCoupon;
	}

	public String getPrintableCoupon(){
		return printableCoupon;
	}

	public void setNewPrice(String newPrice){
		this.newPrice = newPrice;
	}

	public String getNewPrice(){
		return newPrice;
	}

	public void setStarsTotal(String starsTotal){
		this.starsTotal = starsTotal;
	}

	public String getStarsTotal(){
		return starsTotal;
	}

	public void setPingStatus(String pingStatus){
		this.pingStatus = pingStatus;
	}

	public String getPingStatus(){
		return pingStatus;
	}

	public void setSsdPostViewsCount(String ssdPostViewsCount){
		this.ssdPostViewsCount = ssdPostViewsCount;
	}

	public String getSsdPostViewsCount(){
		return ssdPostViewsCount;
	}

//	public void setRedirectToOffer(String redirectToOffer){
//		this.redirectToOffer = redirectToOffer;
//	}
//
//	public String getRedirectToOffer(){
//		return redirectToOffer;
//	}

	public void setPrintImage(String printImage){
		this.printImage = printImage;
	}

	public String getPrintImage(){
		return printImage;
	}

	public void setGuid(com.codeleven.vtrack.model.Guid guid){
		this.guid = guid;
	}

	public com.codeleven.vtrack.model.Guid getGuid(){
		return guid;
	}

	public void setGeoCountrySlug(String geoCountrySlug){
		this.geoCountrySlug = geoCountrySlug;
	}

	public String getGeoCountrySlug(){
		return geoCountrySlug;
	}

	public void setRegisteredMembersOnly(String registeredMembersOnly){
		this.registeredMembersOnly = registeredMembersOnly;
	}

	public String getRegisteredMembersOnly(){
		return registeredMembersOnly;
	}

	public void setShowLocation(String showLocation){
		this.showLocation = showLocation;
	}

	public String getShowLocation(){
		return showLocation;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	public void setSsdPostButtonClicksCount(String ssdPostButtonClicksCount){
		this.ssdPostButtonClicksCount = ssdPostButtonClicksCount;
	}

	public String getSsdPostButtonClicksCount(){
		return ssdPostButtonClicksCount;
	}

	public void setTemplate(String template){
		this.template = template;
	}

	public String getTemplate(){
		return template;
	}

	public void setRatingCountTotal(String ratingCountTotal){
		this.ratingCountTotal = ratingCountTotal;
	}

	public String getRatingCountTotal(){
		return ratingCountTotal;
	}

	public void setHeaderImage(String headerImage){
		this.headerImage = headerImage;
	}

	public String getHeaderImage(){
		return headerImage;
	}

	public void setRatingAverage(String ratingAverage){
		this.ratingAverage = ratingAverage;
	}

	public String getRatingAverage(){
		return ratingAverage;
	}

	public void setLink(String link){
		this.link = link;
	}

	public String getLink(){
		return link;
	}

	public void setSave(String save){
		this.save = save;
	}

	public String getSave(){
		return save;
	}

	public void setTitle(com.codeleven.vtrack.model.Title title){
		this.title = title;
	}

	public com.codeleven.vtrack.model.Title getTitle(){
		return title;
	}

	public void setContent(Content content){
		this.content = content;
	}

	public Content getContent(){
		return content;
	}

	public void setVotesCount(String votesCount){
		this.votesCount = votesCount;
	}

	public String getVotesCount(){
		return votesCount;
	}

	public void setGeoCity(String geoCity){
		this.geoCity = geoCity;
	}

	public String getGeoCity(){
		return geoCity;
	}

	public void setGeoCitySlug(String geoCitySlug){
		this.geoCitySlug = geoCitySlug;
	}

	public String getGeoCitySlug(){
		return geoCitySlug;
	}

	public void setCompany(String company){
		this.company = company;
	}

	public String getCompany(){
		return company;
	}

	public void setDealLayout(String dealLayout){
		this.dealLayout = dealLayout;
	}

	public String getDealLayout(){
		return dealLayout;
	}

	public void setDateGmt(String dateGmt){
		this.dateGmt = dateGmt;
	}

	public String getDateGmt(){
		return dateGmt;
	}

	public void setOfferUrl(String offerUrl){
		this.offerUrl = offerUrl;
	}

	public String getOfferUrl(){
		return offerUrl;
	}

	public void setSearchHeader(String searchHeader){
		this.searchHeader = searchHeader;
	}

	public String getSearchHeader(){
		return searchHeader;
	}

	public void setVirtualDeal(String virtualDeal){
		this.virtualDeal = virtualDeal;
	}

	public String getVirtualDeal(){
		return virtualDeal;
	}

	public void setExpiringDate(String expiringDate){
		this.expiringDate = expiringDate;
	}

	public String getExpiringDate(){
		return expiringDate;
	}

	public void setCouponCode(String couponCode){
		this.couponCode = couponCode;
	}

	public String getCouponCode(){
		return couponCode;
	}

	public void setModifiedGmt(String modifiedGmt){
		this.modifiedGmt = modifiedGmt;
	}

	public String getModifiedGmt(){
		return modifiedGmt;
	}

	public void setDiscountValue(String discountValue){
		this.discountValue = discountValue;
	}

	public String getDiscountValue(){
		return discountValue;
	}

	public void setCommentStatus(String commentStatus){
		this.commentStatus = commentStatus;
	}

	public String getCommentStatus(){
		return commentStatus;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	public void setLimitedDeal(String limitedDeal){
		this.limitedDeal = limitedDeal;
	}

	public String getLimitedDeal(){
		return limitedDeal;
	}

	public void setSlider0Image(String slider0Image){
		this.slider0Image = slider0Image;
	}

	public String getSlider0Image(){
		return slider0Image;
	}

	public void setGeoCountry(String geoCountry){
		this.geoCountry = geoCountry;
	}

	public String getGeoCountry(){
		return geoCountry;
	}

	public void setMapZoom(String mapZoom){
		this.mapZoom = mapZoom;
	}

	public String getMapZoom(){
		return mapZoom;
	}

//	public void setLocation(String location){
//		this.location = location;
//	}
//
//	public String getLocation(){
//		return location;
//	}

	public void setDealSummary(String dealSummary){
		this.dealSummary = dealSummary;
	}

	public String getDealSummary(){
		return dealSummary;
	}

	public void setCategory(String category){
		this.category = category;
	}

	public String getCategory(){
		return category;
	}

	@Override
 	public String toString(){
		return 
			"GalleryResponse{" + 
			"date = '" + date + '\'' + 
			",slider = '" + slider + '\'' +
			",_links = '" + links + '\'' + 
			",type = '" + type + '\'' + 
//			",fw_options = '" + fwOptions + '\'' +
			",modified = '" + modified + '\'' + 
			",id = '" + id + '\'' + 
			",slider_2_image = '" + slider2Image + '\'' + 
			",slug = '" + slug + '\'' + 
			",image_type = '" + imageType + '\'' + 
			",show_pricing_fields = '" + showPricingFields + '\'' + 
			",image = '" + image + '\'' + 
			",deal_type = '" + dealType + '\'' + 
			",old_price = '" + oldPrice + '\'' + 
			",slider_1_image = '" + slider1Image + '\'' + 
			",printable_coupon = '" + printableCoupon + '\'' + 
			",new_price = '" + newPrice + '\'' + 
			",stars_total = '" + starsTotal + '\'' + 
			",ping_status = '" + pingStatus + '\'' + 
			",ssd_post_views_count = '" + ssdPostViewsCount + '\'' + 
//			",redirect_to_offer = '" + redirectToOffer + '\'' +
			",print_image = '" + printImage + '\'' + 
			",guid = '" + guid + '\'' + 
			",geo_country_slug = '" + geoCountrySlug + '\'' + 
			",registered_members_only = '" + registeredMembersOnly + '\'' + 
			",show_location = '" + showLocation + '\'' + 
			",status = '" + status + '\'' + 
			",ssd_post_button_clicks_count = '" + ssdPostButtonClicksCount + '\'' + 
			",template = '" + template + '\'' + 
			",rating_count_total = '" + ratingCountTotal + '\'' + 
			",header_image = '" + headerImage + '\'' + 
			",rating_average = '" + ratingAverage + '\'' + 
			",link = '" + link + '\'' + 
			",save = '" + save + '\'' + 
			",title = '" + title + '\'' + 
			",content = '" + content + '\'' + 
			",votes_count = '" + votesCount + '\'' + 
			",geo_city = '" + geoCity + '\'' + 
			",geo_city_slug = '" + geoCitySlug + '\'' + 
			",company = '" + company + '\'' + 
			",deal_layout = '" + dealLayout + '\'' + 
			",date_gmt = '" + dateGmt + '\'' + 
			",offer_url = '" + offerUrl + '\'' + 
			",search_header = '" + searchHeader + '\'' + 
			",virtual_deal = '" + virtualDeal + '\'' + 
			",expiring_date = '" + expiringDate + '\'' + 
			",coupon_code = '" + couponCode + '\'' + 
			",modified_gmt = '" + modifiedGmt + '\'' + 
			",discount_value = '" + discountValue + '\'' + 
			",comment_status = '" + commentStatus + '\'' + 
			",url = '" + url + '\'' + 
			",limited_deal = '" + limitedDeal + '\'' + 
			",slider_0_image = '" + slider0Image + '\'' + 
			",geo_country = '" + geoCountry + '\'' + 
			",map_zoom = '" + mapZoom + '\'' + 
//			",location = '" + location + '\'' +
			",deal_summary = '" + dealSummary + '\'' + 
			",category = '" + category + '\'' + 
			"}";
		}
}