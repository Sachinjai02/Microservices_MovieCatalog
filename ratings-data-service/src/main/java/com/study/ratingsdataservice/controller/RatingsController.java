package com.study.ratingsdataservice.controller;

import com.study.ratingsdataservice.model.MovieRating;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratings")
public class RatingsController {

    @GetMapping("/{movieId}")
    public MovieRating getMovieRating(@PathVariable("movieId") String movieID) {
        return new MovieRating(movieID, 4);
    }
}
