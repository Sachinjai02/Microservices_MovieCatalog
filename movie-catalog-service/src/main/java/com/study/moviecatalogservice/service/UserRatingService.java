package com.study.moviecatalogservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.study.moviecatalogservice.model.MovieRating;
import com.study.moviecatalogservice.model.MovieRatingResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserRatingService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackUserRating",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
            }
    )
    public MovieRatingResponseDto getUserRating(String userId) {
        //get all movies for this user along with its rating
        MovieRatingResponseDto responseDto = restTemplate.getForObject("http://RATINGS-DATA-SERVICE/ratings/users/" + userId, MovieRatingResponseDto.class);
        return responseDto;
    }

    public MovieRatingResponseDto getFallbackUserRating(String userId) {
        MovieRatingResponseDto responseDto = new MovieRatingResponseDto();
        responseDto.getMovieRatingList().add(new MovieRating("0",0));
        responseDto.setUserId(userId);
        return responseDto;
    }

}
