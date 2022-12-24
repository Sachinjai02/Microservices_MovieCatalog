package com.study.moviecatalogservice.controller;

import com.study.moviecatalogservice.model.CatalogItem;
import com.study.moviecatalogservice.model.Movie;
import com.study.moviecatalogservice.model.MovieRatingResponseDto;
import com.study.moviecatalogservice.service.MovieInfoService;
import com.study.moviecatalogservice.service.UserRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

    @Autowired
    private MovieInfoService movieInfoService;

    @Autowired
    private UserRatingService userRatingService;
    //reactive programming web client
    @Autowired
    private WebClient.Builder webClientBuilder;

    @GetMapping("/{id}")
    public List<CatalogItem> getCatalog(@PathVariable("id") String userId) {
        MovieRatingResponseDto userRating = this.userRatingService.getUserRating(userId);
        return userRating.getMovieRatingList().stream()
                .map(rating -> {
                    Movie movie = this.movieInfoService.getMovieDesc(rating.getMovieId());
                    return new CatalogItem( movie.getName(), movie.getDescription(), rating.getRating());
                }).collect(Collectors.toList());
    }


}
