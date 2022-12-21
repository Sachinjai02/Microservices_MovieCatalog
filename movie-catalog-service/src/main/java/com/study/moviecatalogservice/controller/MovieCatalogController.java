package com.study.moviecatalogservice.controller;

import com.study.moviecatalogservice.model.CatalogItem;
import com.study.moviecatalogservice.model.Movie;
import com.study.moviecatalogservice.model.MovieRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{id}")
    public List<CatalogItem> getCatalog(@PathVariable("id") String userId) {
        //get all movies for this user along with its rating
        List<MovieRating> movieRatings = Arrays.asList(new MovieRating("1", 4), new MovieRating("2", 5));

        //for each movie, get movie details from movie-info-service
        return movieRatings.stream().map(rating -> {
                Movie movieDesc = restTemplate.getForObject("http://localhost:8081/movie/" + rating.getMovieId(), Movie.class);
                return new CatalogItem( movieDesc.getName(), movieDesc.getDescription() , rating.getRating());
        }).collect(Collectors.toList());
        //club both information

    }
}
