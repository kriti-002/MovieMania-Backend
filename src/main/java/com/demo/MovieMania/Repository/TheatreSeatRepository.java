package com.demo.MovieMania.Repository;

import com.demo.MovieMania.Model.Domain.TheatreSeat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheatreSeatRepository extends JpaRepository<TheatreSeat, Long> {
}
