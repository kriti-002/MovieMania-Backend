package com.demo.MovieMania.Service;

import com.demo.MovieMania.Model.Domain.Enums.Genre;
import com.demo.MovieMania.Model.Domain.Movie;
import com.demo.MovieMania.Model.Response.MovieResponse;
import com.demo.MovieMania.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {
    @Autowired
    public MovieRepository movieRepository;

    public MovieResponse addMovie(Movie m){
        movieRepository.save(m);
        return m.toResponse("Movie Added Successfully.");
    }
    public List<MovieResponse> getMovieByGenre(Genre genre){
        return movieRepository.getMovieByGenre(genre).stream().map(Movie::toResponse).collect(Collectors.toList());
    }
    public MovieResponse getMovieByTitle(String title){
        return movieRepository.getMovieByTitle(title).toResponse("Got the movie with the title " + title);
    }
    public List<MovieResponse> getAllMovies(){
        return movieRepository.findAll().stream().map(Movie::toResponse).collect(Collectors.toList());
    }
    public List<MovieResponse> getMovieByRating(Double ratings){
        return movieRepository.getMovieByRating(ratings).stream().map(Movie::toResponse).collect(Collectors.toList());
    }
    public List<MovieResponse> getMovieByLength(Time time){
        return movieRepository.getMovieByLengthOfMovie(time).stream().map(Movie::toResponse).collect(Collectors.toList());
    }


    public MovieResponse updateMovie(Long id, Movie m) {
        boolean flag = movieRepository.findById(id).isPresent();
        if(!flag) return null;
        Movie p= movieRepository.findById(id).get();

        if(m.getLengthOfMovie() != null) p.setLengthOfMovie(m.getLengthOfMovie());
        if(m.getGenre() != null) p.setGenre(m.getGenre());
        if(m.getDate_of_release() != null) p.setDate_of_release(m.getDate_of_release());
        if(m.getTitle() != null) p.setTitle(m.getTitle());
        if(!m.getMovie_casts().isEmpty()) p.setMovie_casts(m.getMovie_casts());

        movieRepository.save(p);
        return p.toResponse("Update the movie with given credentials.");

    }
    public String deleteMovie(Long id){
        boolean flag = movieRepository.findById(id).isPresent();
        if(!flag) return "Movie with following Id doesn't exists.";
        Movie p= movieRepository.findById(id).get();
        movieRepository.delete(p);
        return "Movie with the given ID is deleted.";
    }
}
