package com.stackroute.giphyservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stackroute.giphyservice.domain.Gif;
//import org.springframework.data.mongodb.repository.MongoRepository;

public interface GifRepository extends JpaRepository<Gif, String> {

}
