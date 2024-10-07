package com.demo.MovieMania.Model.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewResponse {
    private String review;
    private Double ratings;
    private Date createdDate;
    private Date updatedDate;
    private String message;
    private String sentiment;
}
