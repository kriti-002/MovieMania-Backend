package com.demo.MovieMania.Repository;

import com.demo.MovieMania.Model.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByMobile(String mobile);
    boolean existsByEmail(String email);
    boolean existsByPassword(String password);

    Optional<User> findByEmail(String email);
}
