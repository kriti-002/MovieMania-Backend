package com.demo.MovieMania.Model.Response;

import com.demo.MovieMania.Model.Domain.Enums.Genre;
import lombok.*;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieResponse {
    private String title;
    private Double ratings;
    private List<String> movie_casts;
    private Time lengthOfMovie;
    private Genre genre;
    private Date date_of_release;
    private String message;
    private List<ReviewResponse> reviewList;
    private String sentiment;
}
