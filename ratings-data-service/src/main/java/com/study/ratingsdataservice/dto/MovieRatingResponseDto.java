package com.study.ratingsdataservice.dto;

import com.study.ratingsdataservice.model.MovieRating;

import java.util.List;

public class MovieRatingResponseDto {
    private List<MovieRating> movieRatingList;

    public MovieRatingResponseDto() {
    }

    public List<MovieRating> getMovieRatingList() {
        return movieRatingList;
    }

    public void setMovieRatingList(List<MovieRating> movieRatingList) {
        this.movieRatingList = movieRatingList;
    }
}
