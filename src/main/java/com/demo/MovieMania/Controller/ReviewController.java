package com.demo.MovieMania.Controller;

import com.demo.MovieMania.Model.Domain.Review;
import com.demo.MovieMania.Model.Request.ReviewRequest;
import com.demo.MovieMania.Model.Response.ReviewResponse;
import com.demo.MovieMania.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReviewController {

    @Autowired
    public ReviewService reviewService;

    //ADMIN Endpoint
    @DeleteMapping("/admin/review/delete")
    public String deleteReviewAdmin(@RequestParam Long id){
        return reviewService.deleteReview(id);
    }

    //User Endpoints
    @PostMapping("/user/review/add")
    public ReviewResponse addReview(@RequestBody ReviewRequest r){
        System.out.println(r);
        return reviewService.addReview(r.toReview());
    }
    @DeleteMapping("/user/review/delete")
    public String deleteReview(@RequestParam Long id){
        return reviewService.deleteReview(id);
    }

    @PutMapping("/user/review/update")
    public ReviewResponse updateReview(@RequestParam Long id, @RequestBody Review r){
        return reviewService.updateReview(id, r);
    }

    @GetMapping("user/review/getById")
    public ReviewResponse getReviewById(@RequestParam Long id){
        return reviewService.getReviewById(id);
    }

    @GetMapping("user/review/getAll")
    public List<ReviewResponse> getAllReviewsOfAMovie(@RequestParam Long id){
        return reviewService.getAllReviewsOfMovie(id);
    }

}
