package com.demo.MovieMania.Model.Domain;

import com.demo.MovieMania.Model.Response.ShowsResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Shows {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="movie_id", nullable=false)
    private Movie movie;

    @ManyToOne
    @JsonIgnore
    private Theatre theatre;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @UpdateTimestamp
    private Date updated;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @Column(name = "show_time", columnDefinition = "TIME", nullable = false)
    private LocalDateTime showTime;

    @OneToMany(mappedBy = "shows", cascade = CascadeType.ALL)
    private List<ShowSeat> showSeats;

    public static ShowsResponse toResponse(Shows s){
        return ShowsResponse.builder()
                .showTime(s.getShowTime())
                .showSeats(s.getShowSeats().stream().map(ShowSeat::toResource).toList())
                .movie(s.getMovie().toResponse("Got the Show"))
                .theatre(s.getTheatre().toResponse("Got the Theatre"))
                .build();
    }
    public ShowsResponse toResponse(String message){
        return ShowsResponse.builder()
                .showTime(showTime)
                .showSeats(showSeats.stream().map(ShowSeat::toResource).toList())
                .movie(movie.toResponse("Got the Show"))
                .theatre(theatre.toResponse("Got the Theatre"))
                .message(message)
                .build();
    }

}
