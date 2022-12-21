package com.study.moviecatalogservice.model;

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
