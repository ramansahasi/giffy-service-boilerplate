package com.stackroute.giphyservice.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.stackroute.giphyservice.domain.Gif;
import com.stackroute.giphyservice.exception.GifUploadFailedException;

@Service("gipfyService")
public class GipfyService {
	
	public String uploadGifToServer(MultipartFile file) throws IOException, UnsupportedOperationException, JSONException, GifUploadFailedException { 
		String url = "https://upload.giphy.com/v1/gifs";
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost uploadFile = new HttpPost(url);
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.addTextBody("api_key", "hnZaC5l6tkmQ5uezGjtS7FLJDtCJLQ0N");

		File f = convert(file);
		builder.addBinaryBody(
		    "file",
		    new FileInputStream(f),
		    ContentType.APPLICATION_OCTET_STREAM,
		    f.getName()
		);

		HttpEntity multipart = builder.build();
		uploadFile.setEntity(multipart);
		CloseableHttpResponse response = httpClient.execute(uploadFile);
		HttpEntity responseEntity = response.getEntity();

		String responseString = EntityUtils.toString(responseEntity, "UTF-8");
		JSONObject jo = new JSONObject(responseString);
		
		if(jo.optJSONObject("meta") != null && jo.optJSONObject("meta").optInt("status") == 200) {
			return jo.optJSONObject("data").optString("id");
		}
		else {
			throw new GifUploadFailedException();
		}
	}
	
	public File convert(MultipartFile file) throws IOException {    
	    File convFile = new File(file.getOriginalFilename());
	    convFile.createNewFile(); 
	    FileOutputStream fos = new FileOutputStream(convFile); 
	    fos.write(file.getBytes());
	    fos.close(); 
	    return convFile;
	}
	
	public Gif updateGipfyDetails(Gif gif, String giphyId) {
		if(giphyId != null && !giphyId.isEmpty()) {
			gif.setGiphyId(giphyId);
			gif.setGifUrl("https://media.giphy.com/media/" + giphyId + "/giphy.gif");
			gif.setGifDetails(gif.getGifUrl());
			gif.setCreatedOn(new SimpleDateFormat("dd/MMM/yyyy HH:mm").format(new Date()));
			gif.setType("GIF");
		}
		return gif;
	}

}
