package com.study.movieinfoservice.controller;

import com.study.movieinfoservice.model.Movie;
import com.study.movieinfoservice.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieRepository movieReository;

    @GetMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String id) {
        return this.movieReository.getMovie(id);
    }


}
