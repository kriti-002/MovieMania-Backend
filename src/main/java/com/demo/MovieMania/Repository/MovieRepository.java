package com.demo.MovieMania.Repository;

import com.demo.MovieMania.Model.Domain.Enums.Genre;
import com.demo.MovieMania.Model.Domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    public Movie getMovieByTitle(String title);
    public List<Movie> getMovieByLengthOfMovie(Time time);
    public List<Movie> getMovieByGenre(Genre genre);

    @Query(value = "SELECT * FROM movie m WHERE m.ratings >= ?1",
            nativeQuery = true)
    public List<Movie> getMovieByRating(Double rating);

}
