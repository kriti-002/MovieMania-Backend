package com.demo.MovieMania.Model.Response;

import com.demo.MovieMania.Model.Domain.ShowSeat;
import com.demo.MovieMania.Model.Domain.Shows;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShowsResponse {
    private LocalDateTime showTime;
    private List<ShowSeatResponse> showSeats;
    private TheatreResponse theatre;
    private MovieResponse movie;
    private String message;


}
