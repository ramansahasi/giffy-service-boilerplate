package com.stackroute.giphyservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.giphyservice.domain.Gif;
import com.stackroute.giphyservice.exception.GifAlreadyExistsException;
import com.stackroute.giphyservice.exception.GifNotFoundException;
import com.stackroute.giphyservice.repository.GifRepository;

@Service("GifService")
public class GifServiceImpl implements GifService {

	@Autowired
	GifRepository gifRepository;
	
	@Override
	public Gif saveGif(Gif gif) throws GifAlreadyExistsException {
		if(gifRepository.existsById(gif.getGifId())) {
			throw new GifAlreadyExistsException();
		}
		else {
			gif = gifRepository.save(gif);
		}
		return gif;
	}

	@Override
	public boolean deleteGif(String gifId) throws GifNotFoundException {
		if(gifRepository.existsById(gifId)) {
			gifRepository.deleteById(gifId);
			return true;
		}
		else {
			throw new GifNotFoundException();
		}
	}

	@Override
	public Gif updateCaptionForGif(String caption, String gifId) throws GifNotFoundException {
		Gif gif = gifRepository.getOne(gifId);
		if(gif != null) {
			gif.setCaption(caption);
			gif = gifRepository.save(gif);
		}
		else {
			throw new GifNotFoundException();
		}
		return gif;
	}

	@Override
	public List<Gif> getAllGifsFromWishList() throws Exception {
		return gifRepository.findAll();
	}

	@Override
	public Gif getByGiphyId(String giphyId) {
		Optional<Gif> isGif = gifRepository.findById(giphyId);
		if(isGif.isPresent())
			return isGif.get();
		else
			return null;
	}

}