package com.demo.MovieMania.Model.Domain;

import com.demo.MovieMania.Model.Domain.Enums.Seat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Max(50)
    @NotNull
    private String seatNumber;

    @Enumerated(value = EnumType.STRING)
    @NotNull
    private Seat type;

    @ManyToOne
    @JoinColumn(name = "show_id", nullable = false)
    private Shows shows;

}
