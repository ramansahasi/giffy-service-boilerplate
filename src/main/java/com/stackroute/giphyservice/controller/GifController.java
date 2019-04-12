package com.stackroute.giphyservice.controller;

import java.io.IOException;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.stackroute.giphyservice.domain.Gif;
import com.stackroute.giphyservice.exception.GifAlreadyExistsException;
import com.stackroute.giphyservice.service.GifService;
import com.stackroute.giphyservice.service.GipfyService;


@Controller
public class GifController {


    private static final Logger LOGGER = LoggerFactory.getLogger(GifController.class);

    @Autowired
    GifService gifService;
    @Autowired
    GipfyService gipfyService;

    @GetMapping(value={"/", "index.html"})
    public ModelAndView index() {
    	ModelAndView mv = new ModelAndView("index");
    	try {
			mv.addObject("gifs", gifService.getAllGifsFromWishList());
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return mv;
    }
    
    @GetMapping("/create")
    public ModelAndView createGif() {
    	return new ModelAndView("create");
    }

    //save gif to database
    @PostMapping(value = "/saveGifToWishList", consumes={"multipart/form-data","charset/UTF-8"})
    public ResponseEntity<?> uploadGif(@ModelAttribute Gif gif, 
    		@RequestParam("file") MultipartFile file) {
    	try {
    		String gipfyId = gipfyService.uploadGifToServer(file);
    		gif = gipfyService.updateGipfyDetails(gif, gipfyId);
			gifService.saveGif(gif);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

    }
    
    //delete a gif from database

    public ResponseEntity<?> deleteGifFromWishList() {
		return null;

    }

    //update caption for a gif
    @GetMapping("/update")
    public ModelAndView updateCaptionForGif(@RequestParam("gifId") String gifId) {
    	ModelAndView mv = new ModelAndView("update");
    	Gif gif = gifService.getByGiphyId(gifId);
    	mv.addObject("gif", gif);
		return mv;

    }

    //get all gifs from  database

    @GetMapping("/list")
    public ModelAndView getAllGifsFromWishList() {
    	ModelAndView mv = new ModelAndView("list-all");
    	try {
			mv.addObject("gifs", gifService.getAllGifsFromWishList());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return mv;

    }
}