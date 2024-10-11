package com.demo.MovieMania.Model.Domain;

import com.demo.MovieMania.Model.Domain.Enums.Seat;
import com.demo.MovieMania.Model.Response.ShowSeatResponse;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ShowSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "is_booked", columnDefinition = "bit(1) default 0", nullable = false)
    private Boolean isBooked;

    @Max(600)
    @Min(100)
    private Integer price;

    @NotNull
    private String seatNumber;

    @Enumerated(value = EnumType.STRING)
    @NotNull
    private Seat type;

    @ManyToOne
    @JoinColumn(name = "show_id", nullable = false)
    private Shows shows;

    public static List<ShowSeatResponse> toResource(List<ShowSeat> seats) {

        if (seats.isEmpty()) {
            return new ArrayList<>();
        }
        return seats.stream().map(ShowSeat::toResource).collect(Collectors.toList());
    }

    public static ShowSeatResponse toResource(ShowSeat seat) {

        return ShowSeatResponse.builder()
                .seatNumber(seat.getSeatNumber())
                .price(seat.getPrice())
                .type(seat.getType())
                .isBooked(seat.getIsBooked())
                .build();

    }

}
