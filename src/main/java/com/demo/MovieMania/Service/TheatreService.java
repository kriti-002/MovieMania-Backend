package com.demo.MovieMania.Service;

import com.demo.MovieMania.Model.Domain.Enums.Seat;
import com.demo.MovieMania.Model.Domain.Theatre;
import com.demo.MovieMania.Model.Domain.TheatreSeat;
import com.demo.MovieMania.Model.Response.TheatreResponse;
import com.demo.MovieMania.Repository.TheatreRepository;
import com.demo.MovieMania.Repository.TheatreSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheatreService {
    @Autowired
    public TheatreRepository theatreRepository;
    @Autowired
    public TheatreSeatRepository theatreSeatRepository;

    public TheatreResponse addTheatre(Theatre t) {
        t.getTheatreSeats().addAll(getTheaterSeats());

        for (TheatreSeat seatsEntity : t.getTheatreSeats()) {
            seatsEntity.setTheatre(t);
        }

        t = theatreRepository.save(t);
        return t.toResponse("Added the Theatre");
    }
    public String deleteTheatre(Long id){
        boolean flag = theatreRepository.findById(id).isPresent();
        if(!flag) return "Theatre with following Id doesn't exists.";
        Theatre t= theatreRepository.findById(id).get();
        theatreRepository.delete(t);
        return "Theatre with the given ID is deleted.";
    }

    public TheatreResponse updateTheatre(Theatre updated, Long id){
        boolean flag = theatreRepository.findById(id).isPresent();
        if(!flag) return TheatreResponse.builder().message("Theatre with the following ID doesn't exists.").build();
        Theatre t = theatreRepository.findById(id).get();

        if(updated.getCity() != null) t.setCity(updated.getCity());
        if(updated.getName() != null) t.setCity(updated.getName());
        if(updated.getAddress() != null) t.setAddress(updated.getAddress());

        theatreRepository.save(t);
        return t.toResponse("Updated the Theatre");
    }
    private List<TheatreSeat> getTheaterSeats() {

        List<TheatreSeat> seats = new ArrayList<>();

        seats.add(getTheaterSeat("1A", Seat.REGULAR));
        seats.add(getTheaterSeat("1B", Seat.REGULAR));
        seats.add(getTheaterSeat("1C", Seat.REGULAR));
        seats.add(getTheaterSeat("1D", Seat.REGULAR));
        seats.add(getTheaterSeat("1E", Seat.REGULAR));

        seats.add(getTheaterSeat("2A", Seat.RECLINER));
        seats.add(getTheaterSeat("2B", Seat.RECLINER));
        seats.add(getTheaterSeat("2C", Seat.RECLINER));
        seats.add(getTheaterSeat("2D", Seat.RECLINER));
        seats.add(getTheaterSeat("2E", Seat.RECLINER));

        seats = theatreSeatRepository.saveAll(seats);

        return seats;
    }

    private TheatreSeat getTheaterSeat(String seatNumber, Seat seatType) {
        return TheatreSeat.builder().seatNumber(seatNumber).type(seatType).build();
    }
}
