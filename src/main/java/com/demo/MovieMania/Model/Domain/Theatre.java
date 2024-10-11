package com.demo.MovieMania.Model.Domain;

import com.demo.MovieMania.Model.Response.TheatreResponse;
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
import java.util.stream.Collectors;

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
    private String name;

    @NotNull
    private String address;

    @NotNull
    private String city;

    @OneToMany(mappedBy = "theatre", cascade = CascadeType.ALL)
    @Builder.Default
    @JsonIgnore
    private List<TheatreSeat> theatreSeats= new ArrayList<>();

    @OneToMany(mappedBy = "theatre", cascade = CascadeType.ALL)
    @JsonIgnore
    @Builder.Default
    private List<Shows> shows = new ArrayList<>();


    public TheatreResponse toResponse(Theatre t){
        return TheatreResponse.builder()
                .Address(t.getAddress())
                .city(t.getCity())
                .name(t.getName())
                .build();
    }
    public TheatreResponse toResponse(String message){
        return TheatreResponse.builder()
                .Address(address)
                .city(city)
                .name(name)
                .message(message)
                .build();
    }
}
