package com.demo.MovieMania.Model.Response;

import com.demo.MovieMania.Model.Domain.Enums.Seat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowSeatResponse {

    private Boolean isBooked;
    private Integer price;
    private String seatNumber;
    private Seat type;
}
