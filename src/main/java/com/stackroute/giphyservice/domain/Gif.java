package com.stackroute.giphyservice.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;



public class Gif {
    @Id
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
    private Image image;


}

