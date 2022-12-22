package com.study.movieinfoservice.controller;

import com.study.movieinfoservice.model.Movie;
import com.study.movieinfoservice.model.TheMovieDBMovie;
import com.study.movieinfoservice.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController()
@RequestMapping("/movie")
public class MovieController {

    @Value("${movieApi.key}")
    private String movieApiKey;
    @Autowired
    private MovieRepository movieReository;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String id) {
        TheMovieDBMovie movie = restTemplate.getForObject("https://api.themoviedb.org/3/movie/"
        + id + "?api_key=" + movieApiKey, TheMovieDBMovie.class);

        return new Movie(id, movie.getOriginal_title(), movie.getOverview());
    }


}
