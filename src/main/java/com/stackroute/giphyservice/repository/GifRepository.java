package com.stackroute.giphyservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stackroute.giphyservice.domain.Gif;

public interface GifRepository extends JpaRepository<Gif, String> {
}
