package com.demo.MovieMania.Model.Request;

import com.demo.MovieMania.Model.Domain.Genre;
import com.demo.MovieMania.Model.Domain.Movie;
import com.demo.MovieMania.Model.Domain.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieRequest {
    private String title;
    private Genre genre;
    private Date date_of_release;
    private Time lengthOfMovie;
    private List<Review> reviewList;

    public Movie toMovie(){
        return Movie.builder().
                title(title).
                genre(genre).
                ratings(0.0).
                date_of_release(date_of_release).
                lengthOfMovie(lengthOfMovie).
                reviewList(reviewList).
                build();
    }
}
