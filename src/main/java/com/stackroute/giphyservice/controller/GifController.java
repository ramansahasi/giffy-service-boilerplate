package com.stackroute.giphyservice.controller;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.stackroute.giphyservice.domain.Gif;
import com.stackroute.giphyservice.exception.GifAlreadyExistsException;
import com.stackroute.giphyservice.exception.GifNotFoundException;
import com.stackroute.giphyservice.exception.GifUploadFailedException;
import com.stackroute.giphyservice.service.GifService;
import com.stackroute.giphyservice.service.GipfyService;

@RestController
@RequestMapping("/rest")
public class GifController {

    @Autowired
    GifService gifService;
    @Autowired
    GipfyService gipfyService;

    private static final Logger LOGGER = LoggerFactory.getLogger(GifController.class);

    //save gif to database
    @PostMapping(value = "/saveGifToWishList", consumes={"multipart/form-data","charset/UTF-8"})
    public ResponseEntity<?> saveGifToWishList(@RequestBody Gif gif, 
    		@RequestParam("file") MultipartFile file) {
    	LOGGER.info("/saveGifToWishList url called");
    	String gipfyId;
		try {
			gipfyId = gipfyService.uploadGifToServer(file);
			gif = gipfyService.updateGipfyDetails(gif, gipfyId);
			gifService.saveGif(gif);
			return new ResponseEntity<Gif>(gif, HttpStatus.OK);
		} catch (UnsupportedOperationException | IOException | JSONException | GifUploadFailedException e) {
			LOGGER.error("Error occurred while saving GIF", e);
			return new ResponseEntity<>(
			          "There was some error while saving this GIF", 
			          HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (GifAlreadyExistsException e) {
			LOGGER.error("GIF already exists - cannot save", e);
			return new ResponseEntity<>(
			          "GIF already exists", 
			          HttpStatus.BAD_REQUEST);
		}
    }

    //delete a gif from database
    @GetMapping("/delete")
    public ResponseEntity<?> deleteGifFromWishList(@RequestParam("gifId") String gifId) {
    	LOGGER.info("/delete url called");
    	try {
			gifService.deleteGif(gifId);
			return new ResponseEntity<>("GIF Deleted Successfully", HttpStatus.OK);
		} catch (GifNotFoundException e) {
			LOGGER.error("GIF was not found on theserver", e);
			return new ResponseEntity<>("GIF was not found on theserver", HttpStatus.BAD_REQUEST);
		}
    }

    //update caption for a gif
    @PostMapping("/updateCaptionForGif")
    public ResponseEntity<?> updateCaptionForGif(@RequestParam("caption") String caption, @RequestParam("gifId") String gifId) {
    	LOGGER.info("/updateCaptionForGif url called");
    	try {
			gifService.updateCaptionForGif(caption, gifId);
			return new ResponseEntity<>("GIF updated successfully", HttpStatus.OK);
    	} catch (GifNotFoundException e) {
    		LOGGER.error("GIF was not found on theserver", e);
			return new ResponseEntity<>("GIF was not found on theserver", HttpStatus.BAD_REQUEST);
		}
    }

    //get all gifs from  database
    @GetMapping("/list")
    public ResponseEntity<?> getAllGifsFromWishList() {
    	LOGGER.info("/list url called");
    	try {
			return new ResponseEntity<List<Gif>>(gifService.getAllGifsFromWishList(), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("There was some error fetching all GIF", e);
			return new ResponseEntity<>(
			          "There was some error fetching all GIF", 
			          HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
}