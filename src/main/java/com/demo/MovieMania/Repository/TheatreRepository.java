package com.demo.MovieMania.Repository;

import com.demo.MovieMania.Model.Domain.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheatreRepository extends JpaRepository<Theatre, Long> {
}
