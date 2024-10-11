package com.demo.MovieMania.Repository;

import com.demo.MovieMania.Model.Domain.Shows;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShowRepository extends JpaRepository<Shows, Long> {
    @Query(value= "select s.* from shows s, theatre t where s.theatre_id=t.id and t.city=?1",nativeQuery = true)
    List<Shows> findByCity(String city);

    @Query(value = "select s.* from shows s, movie m , theatre t where m.id=s.movie_id and s.theatre_id=t.id and m.title=?1 and t.city=?2",nativeQuery = true)
    List<Shows> findByMovieNameAndCity(String movie, String city);

    @Query(value = "select s.* from shows s, theatre t where s.theatre_id=t.id and t.name=?1 and t.city=?2",nativeQuery = true)
    List<Shows> findByTheatreAndCity(String theatre, String city);
}
