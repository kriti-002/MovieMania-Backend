package com.demo.MovieMania.Model.Request;

import com.demo.MovieMania.Model.Domain.Movie;
import com.demo.MovieMania.Model.Domain.Shows;
import com.demo.MovieMania.Model.Domain.Theatre;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowsRequest {
    @NotNull(message = "Show Time is Mandatory")
    private LocalDateTime showTime;

    @NotNull(message = "Movie is Mandatory for Show")
    private Long movieId;

    @NotNull(message = "Theatre is Mandatory for Show")
    private Long theatreId;

    public Shows toRequest(){
        return Shows.builder()
                .movie(Movie.builder()
                        .id(movieId)
                        .build())
                .theatre(Theatre.builder()
                        .id(theatreId)
                        .build())
                .showTime(showTime)
                .build();
    }
}
