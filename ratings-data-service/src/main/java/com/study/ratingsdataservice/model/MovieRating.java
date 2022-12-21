package com.study.ratingsdataservice.model;

public class MovieRating {
    private int rating;
    private String movieId;

    public MovieRating(String movieId, int rating) {
        this.rating = rating;
        this.movieId = movieId;
    }


    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }
}
