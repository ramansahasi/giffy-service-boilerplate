package com.stackroute.giphyservice.domain;


import org.springframework.data.annotation.Id;

import java.util.List;

public class Image {
    private String imageId;
    private String defaultUrl;
    private String defaultWidth;
    private String defaultHeight;
    private String defaultSize;
    private List<ImageRepresentation> imageRepresentations;
    
    // Getters and Setters
	public String getImageId() {
		return imageId;
	}
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
	public String getDefaultUrl() {
		return defaultUrl;
	}
	public void setDefaultUrl(String defaultUrl) {
		this.defaultUrl = defaultUrl;
	}
	public String getDefaultWidth() {
		return defaultWidth;
	}
	public void setDefaultWidth(String defaultWidth) {
		this.defaultWidth = defaultWidth;
	}
	public String getDefaultHeight() {
		return defaultHeight;
	}
	public void setDefaultHeight(String defaultHeight) {
		this.defaultHeight = defaultHeight;
	}
	public String getDefaultSize() {
		return defaultSize;
	}
	public void setDefaultSize(String defaultSize) {
		this.defaultSize = defaultSize;
	}
	public List<ImageRepresentation> getImageRepresentations() {
		return imageRepresentations;
	}
	public void setImageRepresentations(List<ImageRepresentation> imageRepresentations) {
		this.imageRepresentations = imageRepresentations;
	}
}
