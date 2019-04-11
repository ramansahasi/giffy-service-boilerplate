package com.stackroute.giphyservice.service;

import java.util.List;

import com.stackroute.giphyservice.domain.Gif;
import com.stackroute.giphyservice.exception.GifAlreadyExistsException;
import com.stackroute.giphyservice.exception.GifNotFoundException;

public class GifServiceImpl implements GifService {

	@Override
	public Gif saveGif(Gif gif) throws GifAlreadyExistsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteGif(String gifId) throws GifNotFoundException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Gif updateCaptionForGif(String caption, String gifId) throws GifNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Gif> getAllGifsFromWishList() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Gif getByGiphyId(String giphyId) {
		// TODO Auto-generated method stub
		return null;
	}

}