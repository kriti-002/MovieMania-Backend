package com.demo.MovieMania.Model.Domain;

import com.demo.MovieMania.Model.Response.ReviewResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String review;

    @Min(0)
    @Max(5)
    @NotNull
    private Double ratings;

    @CreationTimestamp
    private Date created_date;

    @UpdateTimestamp
    private Date updated_date;

    @ManyToOne
    @JoinColumn(name="movie_id", nullable=false)
    @JsonIgnore
    private Movie movie;

    private String sentiment;

    public static ReviewResponse toResponseStatic(Review r){
        return ReviewResponse.builder()
                .message("Done!")
                .createdDate(r.created_date)
                .updatedDate(r.updated_date)
                .review(r.review)
                .ratings(r.ratings)
                .sentiment(r.sentiment)
                .build();
    }
    public static List<ReviewResponse> toResponseStatic(List<Review> reviewList){
        if(reviewList == null) return new ArrayList<>();
        return reviewList.stream().map(Review::toResponseStatic).collect(Collectors.toList());
    }
    public ReviewResponse toResponse(String message){
        return ReviewResponse.builder()
                .message(message)
                .createdDate(created_date)
                .updatedDate(updated_date)
                .review(review)
                .ratings(ratings)
                .sentiment(sentiment)
                .build();
    }

}
