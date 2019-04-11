package com.stackroute.giphyservice.controller;

import com.stackroute.giphyservice.domain.Gif;
import com.stackroute.giphyservice.exception.GifNotFoundException;
import com.stackroute.giphyservice.service.GifService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


public class GifController {


    private static final Logger LOGGER = LoggerFactory.getLogger(GifController.class);


    //save gif to database

    public ResponseEntity<?> saveGifToWishList(@RequestBody Gif gif) {
		return null;

    }

    //delete a gif from database

    public ResponseEntity<?> deleteGifFromWishList() {
		return null;

    }

    //update caption for a gif

    public ResponseEntity<?> updateCaptionForGif() {
		return null;

    }

    //get all gifs from  database

    public ResponseEntity<?> getAllGifsFromWishList() {
		return null;

    }
}