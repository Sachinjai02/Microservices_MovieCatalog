package com.study.moviecatalogservice.controller;

import com.study.moviecatalogservice.model.CatalogItem;
import com.study.moviecatalogservice.model.Movie;
import com.study.moviecatalogservice.model.MovieRating;
import com.study.moviecatalogservice.model.MovieRatingResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

    @Autowired
    private RestTemplate restTemplate;

    //reactive programming web client
    @Autowired
    private WebClient.Builder webClientBuilder;

    @GetMapping("/{id}")
    public List<CatalogItem> getCatalog(@PathVariable("id") String userId) {

        //get all movies for this user along with its rating
        MovieRatingResponseDto responseDto = restTemplate.getForObject("http://RATINGS-DATA-SERVICE/ratings/users/" + userId, MovieRatingResponseDto.class);

        //for each movie, get movie details from movie-info-service
        return responseDto.getMovieRatingList().stream().map(rating -> {
            Movie movieDesc = restTemplate.getForObject("http://movie-info-service/movie/" + rating.getMovieId(), Movie.class);

            /*Movie movieDesc = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8081/movie/" + rating.getMovieId())
                    .retrieve()
                    .bodyToMono(Movie.class)
                    .block();*/
            return new CatalogItem( movieDesc.getName(), movieDesc.getDescription() , rating.getRating());
        }).collect(Collectors.toList());
        //club both information

    }
}
