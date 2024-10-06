package com.demo.MovieMania.Controller;


import com.demo.MovieMania.Model.Domain.Genre;
import com.demo.MovieMania.Model.Domain.Movie;
import com.demo.MovieMania.Model.Domain.Review;
import com.demo.MovieMania.Model.Request.MovieRequest;
import com.demo.MovieMania.Model.Response.MovieResponse;
import com.demo.MovieMania.Model.Response.ReviewResponse;
import com.demo.MovieMania.Service.MovieService;
import com.demo.MovieMania.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.List;

@RestController
public class MovieController {
    @Autowired
    public MovieService movieService;

    @Autowired
    public ReviewService reviewService;

    //All Admin Endpoints
    @PostMapping("/admin/movie/add")
    public MovieResponse addMovie(@RequestBody MovieRequest m){
        return movieService.addMovie(m.toMovie());
    }
    @PutMapping("admin/movie/update")
    public MovieResponse updateMovie(@RequestParam Long id, @RequestBody MovieRequest m){
        return movieService.updateMovie(id, m.toMovie());
    }
    @DeleteMapping("admin/movie/delete")
    public String deleteMovie(@RequestParam Long id){
        return movieService.deleteMovie(id);
    }
    @GetMapping("/admin/movie/getAll")
    public List<MovieResponse> getAllMovies(){
        return movieService.getAllMovies();
    }
    @GetMapping("admin/movie/getReviews")
    public List<ReviewResponse> getAllReviewsOfAMovie(@RequestParam Long id){
        return reviewService.getAllReviewsOfMovie(id);
    }

    //All User Endpoints
    @GetMapping("/user/movie/getMovieByGenre")
    public List<MovieResponse> getMovieByGenre(@RequestParam Genre genre){
        return movieService.getMovieByGenre(genre);
    }
    @GetMapping("/user/movie/getMovieByTitle")
    public MovieResponse getMovieByTitle(@RequestParam String title){
        return movieService.getMovieByTitle(title);
    }
    @GetMapping("/user/movie/getAll")
    public List<MovieResponse> getAllMovie(){
        return movieService.getAllMovies();
    }
    @GetMapping("/user/movie/getMovieByRating")
    public List<MovieResponse> getMovieByRating(@RequestParam Double ratings){
        return movieService.getMovieByRating(ratings);
    }
    @GetMapping("/user/movie/getMovieByLength")
    public List<MovieResponse> getMovieByLength(@RequestParam Time time){
        return movieService.getMovieByLength(time);
    }



}
