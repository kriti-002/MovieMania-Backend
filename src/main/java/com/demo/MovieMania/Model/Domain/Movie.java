package com.demo.MovieMania.Model.Domain;

import com.demo.MovieMania.Model.Domain.Enums.Genre;
import com.demo.MovieMania.Model.Response.MovieResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Movie {
    @Id
    @Column(name= "id" , nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Date date_of_release;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    @NotNull
    private Time lengthOfMovie;

    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "movie_casts", joinColumns = @JoinColumn(name = "movie_id"))
    @Column(name = "casts", nullable = false)
    private List<String> movie_casts = new ArrayList<>();

    @NotNull
    private String title;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Review> reviewList;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    @JsonIgnore
    @Builder.Default
    List<Shows> showsList= new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_favorites",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @JsonIgnore
    private List<User> userFavourite;

    private Double ratings;


    public static MovieResponse toResponse(Movie m){
        return MovieResponse.builder()
                .title(m.title)
                .date_of_release(m.date_of_release)
                .genre(m.genre)
                .lengthOfMovie(m.lengthOfMovie)
                .message("Done!")
                .movie_casts(m.movie_casts)
                .ratings(m.ratings)
                .reviewList(m.reviewList.stream().map(Review::toResponseStatic).collect(Collectors.toList()))
                .build();
    }
    public MovieResponse toResponse(String message){
        return MovieResponse.builder()
                .title(title)
                .date_of_release(date_of_release)
                .genre(genre)
                .lengthOfMovie(lengthOfMovie)
                .message(message)
                .movie_casts(movie_casts)
                .ratings(ratings)
                .reviewList(reviewList.stream().map(Review::toResponseStatic).collect(Collectors.toList()))
                .build();
    }

}
