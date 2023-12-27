package com.jbdl58.moviemania.Service;

import com.jbdl58.moviemania.Model.Domain.User;
import com.jbdl58.moviemania.Model.Request.UserRequest;
import com.jbdl58.moviemania.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public String addUser(UserRequest userRequest) {
        if(userRepository.existsByMobile(userRequest.getMobile()) || userRepository.existsByEmail(userRequest.getEmail()) || userRepository.existsByPassword(userRequest.getPassword())) return "User already exists";
        User u= userRequest.toUser();
        userRepository.save(u);
        return "User Added";
    }

    public String getUser(String id) {
        Optional<User> optionalUser= userRepository.findById(id);
        return optionalUser.map(user -> "hello " + user.getName()).orElse("No such user present");
    }

    public String deleteUser(String id) {
        Optional<User> optionalUser= userRepository.findById(id);
        userRepository.deleteById(id);
        return optionalUser.map(user -> "hello " + user.getName()).orElse("No such user present");
    }
}
