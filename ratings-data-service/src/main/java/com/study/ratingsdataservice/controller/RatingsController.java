package com.study.ratingsdataservice.controller;

import com.study.ratingsdataservice.dto.MovieRatingResponseDto;
import com.study.ratingsdataservice.model.MovieRating;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingsController {

    @GetMapping("/{movieId}")
    public MovieRating getMovieRating(@PathVariable("movieId") String movieID) {
        return new MovieRating(movieID, 4);
    }

    @GetMapping("/users/{userId}")
    public MovieRatingResponseDto getMovieRatings(@PathVariable("userId") String id) {
        MovieRatingResponseDto movieRatingResponseDto = new MovieRatingResponseDto();
        movieRatingResponseDto.setMovieRatingList( Arrays.asList(new MovieRating("1", 5),
                new MovieRating("2", 4),
                new MovieRating("3", 2)));

        return movieRatingResponseDto;
    }
}
