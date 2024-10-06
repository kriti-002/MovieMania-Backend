package com.demo.MovieMania.Repository;

import com.demo.MovieMania.Model.Domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query(value = "select avg(ratings) from review where movie_id=?",nativeQuery = true)
    Double getAverageReview(Long id);
}
