package com.stackroute.giphyservice.controller;

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
import com.stackroute.giphyservice.exception.GifNotFoundException;
import com.stackroute.giphyservice.service.GifService;
import com.stackroute.giphyservice.service.GipfyService;


@Controller
public class GifControllerView {


    private static final Logger LOGGER = LoggerFactory.getLogger(GifControllerView.class);

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
    	ModelAndView mv = new ModelAndView("create");
    	try {
			mv.addObject("gifs", gifService.getAllGifsFromWishList());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return mv;
    }

    //save gif to database
    @PostMapping(value = "/saveGifToWishList", consumes={"multipart/form-data","charset/UTF-8"})
    public ModelAndView uploadGif(@ModelAttribute Gif gif, 
    		@RequestParam("file") MultipartFile file) {
    	LOGGER.info("/saveGifToWishList url called");
    	ModelAndView mv = new ModelAndView("update");
    	try {
    		String gipfyId = gipfyService.uploadGifToServer(file);
    		gif = gipfyService.updateGipfyDetails(gif, gipfyId);
			gifService.saveGif(gif);
			mv.addObject("gif", gif);
			mv.addObject("isCreated", true);
			mv.addObject("gifs", gifService.getAllGifsFromWishList());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mv.addObject("isCreated", false);
		}
		return mv;
    }

    //save gif to database
    @PostMapping(value = "/updateGif", consumes={"multipart/form-data","charset/UTF-8"})
    public ResponseEntity<?> updateGif(@ModelAttribute Gif gif) {
    	try {
			gifService.saveGif(gif);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

    }
    
    //delete a gif from database
    @GetMapping("/delete")
    public ModelAndView deleteGifFromWishList(@RequestParam("gifId") String gifId) {
    	LOGGER.info("/delete url called");
    	ModelAndView mv = new ModelAndView("index");
		try {
			gifService.deleteGif(gifId);
			mv.addObject("isDeleted", true);
			mv.addObject("gifs", gifService.getAllGifsFromWishList());
		} catch (GifNotFoundException e) {
			mv.addObject("isDeleted", false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mv;
    }

    //update caption for a gif
    @GetMapping("/update")
    public ModelAndView updateView(@RequestParam("gifId") String gifId) {
    	ModelAndView mv = new ModelAndView("update");
    	Gif gif = gifService.getByGiphyId(gifId);
    	mv.addObject("gif", gif);
    	try {
			mv.addObject("gifs", gifService.getAllGifsFromWishList());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mv;

    }

    //update caption for a gif
    @PostMapping("/updateCaptionForGif")
    public ModelAndView updateCaptionForGif(@RequestParam("caption") String caption, @RequestParam("gifId") String gifId) {
    	ModelAndView mv = new ModelAndView("update");
    	Gif gif = gifService.getByGiphyId(gifId);
    	mv.addObject("gif", gif);
    	try {
    		gifService.updateCaptionForGif(caption, gifId);
    		mv.addObject("isUpdated", true);
		} catch (GifNotFoundException e1) {
			mv.addObject("isUpdated", false);
		}
    	try {
			mv.addObject("gifs", gifService.getAllGifsFromWishList());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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