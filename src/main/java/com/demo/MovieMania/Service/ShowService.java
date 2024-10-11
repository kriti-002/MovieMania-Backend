package com.demo.MovieMania.Service;

import com.demo.MovieMania.Model.Domain.*;
import com.demo.MovieMania.Model.Domain.Enums.Seat;
import com.demo.MovieMania.Model.Response.ShowSeatResponse;
import com.demo.MovieMania.Model.Response.ShowsResponse;
import com.demo.MovieMania.Repository.MovieRepository;
import com.demo.MovieMania.Repository.ShowRepository;
import com.demo.MovieMania.Repository.ShowSeatRepository;
import com.demo.MovieMania.Repository.TheatreRepository;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShowService {
    @Autowired
    public MovieRepository movieRepository;

    @Autowired
    public TheatreRepository theatreRepository;

    @Autowired
    public ShowRepository showRepository;

    @Autowired
    public ShowSeatRepository showSeatRepository;

    public List<ShowsResponse> search(String city, String theatre, String movie){
        if(!StringUtils.hasText(city)) return new ArrayList<>();
        List<Shows> shows;

        if(StringUtils.hasText(movie))
            shows=showRepository.findByMovieNameAndCity(movie,city);
        else if(StringUtils.hasText(theatre)){
            shows=showRepository.findByTheatreAndCity(theatre,city);
        }
        else{
            shows=showRepository.findByCity(city);
        }
        if(CollectionUtils.isEmpty(shows))
            return new ArrayList<>();
        else
            return shows.stream().map(Shows::toResponse).collect(Collectors.toList());

    }
    public ShowsResponse addShows(Shows shows){
        Long movie_id= shows.getMovie().getId();
        Long theatre_id= shows.getTheatre().getId();
        boolean flag1= movieRepository.findById(movie_id).isPresent(), flag2= theatreRepository.findById(theatre_id).isPresent();
        if(!flag1) return ShowsResponse.builder().message("Movie with following ID doesn't exists.").build();
        if(!flag2) return ShowsResponse.builder().message("Theatre with following ID doesn't exists.").build();

        Movie m= movieRepository.findById(movie_id).get();
        Theatre t= theatreRepository.findById(theatre_id).get();

        shows.setMovie(m);
        shows.setTheatre(t);
        shows.setShowSeats(generateShowSeats(shows.getTheatre().getTheatreSeats(), shows));

        for (ShowSeat seatsEntity : shows.getShowSeats()) {
            seatsEntity.setShows(shows);
        }
        shows= showRepository.save(shows);
        return shows.toResponse("Created the show.");
    }

    private List<ShowSeat> generateShowSeats(List<TheatreSeat> theatreSeats, Shows shows) {
        List<ShowSeat> showSeatsEntities = new ArrayList<>();

        for (TheatreSeat theaterSeats : theatreSeats) {

            ShowSeat showSeat =
                    ShowSeat.builder()
                            .seatNumber(theaterSeats.getSeatNumber())
                            .type(theaterSeats.getType())
                            .price(generatePrice(theaterSeats))
                            .isBooked(false)
                            .build();

            showSeatsEntities.add(showSeat);
        }

        return showSeatRepository.saveAll(showSeatsEntities);
    }

    private Integer generatePrice(TheatreSeat theaterSeats) {
        String type= theaterSeats.getType().toString();
        return switch (type) {
            case "REGULAR" -> 150;
            case "RECLINER" -> 200;
            default -> 100;
        };
    }

    public String deleteShows(Long id) {
        boolean flag = showRepository.findById(id).isPresent();
        if(!flag) return "Show with following Id doesn't exists.";
        Shows s= showRepository.findById(id).get();
        showRepository.delete(s);
        return "Show with the given ID is deleted.";
    }

    public List<ShowSeatResponse> getAvailableShows(Long id) {
        boolean flag= showRepository.findById(id).isPresent();
        if(!flag) return new ArrayList<>();

        Shows sh= showRepository.findById(id).get();
        List<ShowSeat> showSeats= sh.getShowSeats();

        showSeats= showSeats.stream().filter(s-> !s.getIsBooked()).toList();
        return showSeats.stream().map(ShowSeat::toResource).collect(Collectors.toList());
    }
}
