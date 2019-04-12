package com.stackroute.giphyservice.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonProperty;
//import org.springframework.data.mongodb.core.mapping.Document;


@Entity
public class Gif {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String gifId;
    @JsonProperty("id")
    private String giphyId;
    private String type;
    @JsonProperty("embed_url")
    private String gifUrl;
    @JsonProperty("url")
    private String gifDetails;
    private String title;
    private String caption;
    @JsonProperty("username")
    private String userName;
    private String rating;
    @JsonProperty("import_datetime")
    private String createdOn;
    @Transient
    private Image image;
    
    // Getters and Setters
	public String getGifId() {
		return gifId;
	}
	public void setGifId(String gifId) {
		this.gifId = gifId;
	}
	public String getGiphyId() {
		return giphyId;
	}
	public void setGiphyId(String giphyId) {
		this.giphyId = giphyId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getGifUrl() {
		return gifUrl;
	}
	public void setGifUrl(String gifUrl) {
		this.gifUrl = gifUrl;
	}
	public String getGifDetails() {
		return gifDetails;
	}
	public void setGifDetails(String gifDetails) {
		this.gifDetails = gifDetails;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
}

