package com.demo.MovieMania.Service;

import com.demo.MovieMania.Model.Domain.Movie;
import com.demo.MovieMania.Model.Domain.Review;
import com.demo.MovieMania.Model.Response.ReviewResponse;
import com.demo.MovieMania.Repository.MovieRepository;
import com.demo.MovieMania.Repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {
    @Autowired
    public ReviewRepository reviewRepository;

    @Autowired
    public MovieRepository movieRepository;

    public ReviewResponse addReview(Review r){
        Long id= r.getMovie().getId();
        System.out.println(id);
        Movie movie=movieRepository.findById(r.getMovie().getId()).orElse(null);
        reviewRepository.save(r);
        if(movie!=null) {
            Double average = reviewRepository.getAverageReview(movie.getId());
            movie.setRatings(average);
            movieRepository.save(movie);
        }
        return r.toResponse("Added the review.");
    }
    public String deleteReview(Long id){
        boolean flag= reviewRepository.findById(id).isPresent();
        if(!flag) return "ID with the following ID doesn't exists.";
        Review r= reviewRepository.findById(id).get();
        reviewRepository.delete(r);
        return "Review with following ID is deleted.";
    }

    public ReviewResponse updateReview(Long id, Review r){
        System.out.println(r);
        boolean flag= reviewRepository.findById(id).isPresent();
        if(!flag) return null;
        Review update= reviewRepository.findById(id).get();
        if(r.getRatings() != null) update.setRatings(r.getRatings());
        if(r.getReview() != null) update.setReview(r.getReview());
        reviewRepository.save(update);

        return update.toResponse("Updated the review.");
    }

    public ReviewResponse getReviewById(Long id){
        boolean flag= reviewRepository.findById(id).isPresent();
        if(!flag) return null;
        return reviewRepository.findById(id).get().toResponse("Got the Review with the given ID.");
    }
    public List<ReviewResponse> getAllReviewsOfMovie(Long id){
        boolean flag= movieRepository.findById(id).isPresent();
        if(!flag) return new ArrayList<>();

        Movie m= movieRepository.findById(id).get();
        return m.getReviewList().stream().map(Review::toResponseStatic).collect(Collectors.toList());
    }
}
