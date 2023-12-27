package com.jbdl58.moviemania.Repository;

import com.jbdl58.moviemania.Model.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByMobile(String mobile);

    boolean existsByEmail(String email);

    boolean existsByPassword(String password);
}
