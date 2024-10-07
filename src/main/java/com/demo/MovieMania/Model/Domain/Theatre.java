package com.demo.MovieMania.Model.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Theatre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Max(100)
    private String name;

    @NotNull
    private String address;

    @NotNull
    @Max(50)
    private String city;

    @OneToMany(mappedBy = "theatre", cascade = CascadeType.ALL)
    @Builder.Default
    @JsonIgnore
    private List<TheatreSeat> theatreSeats= new ArrayList<>();

    @OneToMany(mappedBy = "theatre", cascade = CascadeType.ALL)
    @JsonIgnore
    @Builder.Default
    private List<Shows> shows = new ArrayList<>();
}
