package com.study.moviecatalogservice.model;

import java.util.ArrayList;
import java.util.List;

public class MovieRatingResponseDto {
    private List<MovieRating> movieRatingList;
    private String userId;

    public MovieRatingResponseDto() {
        this.movieRatingList = new ArrayList<>();
    }

    public List<MovieRating> getMovieRatingList() {
        return movieRatingList;
    }

    public void setMovieRatingList(List<MovieRating> movieRatingList) {
        this.movieRatingList = movieRatingList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
