package com.study.moviecatalogservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.study.moviecatalogservice.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieInfoService {
    @Autowired
    private RestTemplate restTemplate;
    @HystrixCommand(fallbackMethod = "getFallbackMovieDesc",
            threadPoolKey = "movieInfoPool",
            threadPoolProperties = {
                    @HystrixProperty(name="coreSize", value="20"),
                    @HystrixProperty(name="maxQueueSize", value="100"),
            },
            commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value = "5"),
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value = "50"),
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value = "5000")
            })
    public Movie getMovieDesc(String movieId) {
        return restTemplate.getForObject("http://MOVIE-INFO-SERVICE/movie/" + movieId, Movie.class);
    }

    public Movie getFallbackMovieDesc(String movieId) {
        return new Movie(movieId, "Unable to find movie name", "Unable to find movie description");
    }
}
