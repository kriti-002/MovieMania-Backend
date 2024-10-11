package com.demo.MovieMania.Model.Domain;

import com.demo.MovieMania.Model.Domain.Enums.Seat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
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
public class TheatreSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String seatNumber;

    @Enumerated(value = EnumType.STRING)
    @NotNull
    private Seat type;

    @ManyToOne
    @JoinColumn(name= "theatre_id", nullable = false)
    @JsonIgnore
    private Theatre theatre;

}
