package com.demo.MovieMania.Model.Request;

import com.demo.MovieMania.Model.Domain.Movie;
import com.demo.MovieMania.Model.Domain.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequest {
    private String review;

    private double ratings;

    private Long movie_id;

    public Review toReview(){
        return Review.builder()
                .review(review)
                .ratings(ratings)
                .movie(Movie.builder()
                        .id(movie_id)
                        .build())
                .build();

    }
}
