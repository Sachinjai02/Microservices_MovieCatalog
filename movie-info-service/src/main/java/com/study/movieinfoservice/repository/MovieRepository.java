package com.study.movieinfoservice.repository;

import com.study.movieinfoservice.model.Movie;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class MovieRepository {
    private Map<String, Movie> movies = new HashMap<>();

    public MovieRepository() {
        movies.put("1", new Movie("1", "Avatar", "Awesome Movie!!"));
        movies.put("2", new Movie("2", "Transformer", "Nice Movie!!"));
        movies.put("3", new Movie("3", "Snakes on a plane", "Disappointing Movie!!"));
    }

    public void addMovie(String movieId, Movie movie) {
        this.movies.put(movieId, movie);
    }

    public Movie getMovie(String movieId) {
        return this.movies.get(movieId);
    }

}
