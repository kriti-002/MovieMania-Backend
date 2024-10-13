package com.demo.MovieMania.Service;

import com.demo.MovieMania.Model.Domain.User;
import com.demo.MovieMania.Model.Request.UserLoginRequest;
import com.demo.MovieMania.Model.Request.UserRequest;
import com.demo.MovieMania.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    public User signup(UserRequest input) {
        String email= input.getEmail(), password= input.getPassword(), mobile= input.getMobile();
        String encodedPassword= passwordEncoder.encode(password);
        System.out.println(userService.mobileCheck(mobile) + " " + userService.passwordCheck(password)
        + " " + userService.emailCheck(email) + " " + userRepository.existsByPassword(encodedPassword));

        if(!userService.mobileCheck(mobile) ||
                !userService.passwordCheck(password) ||
                userRepository.existsByPassword(encodedPassword) ||
                !userService.emailCheck(email)) return null;

        User user = input.toSignUpRequest(encodedPassword);
        return userRepository.save(user);
    }

    public User authenticate(UserLoginRequest input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );
        return userRepository.findByEmail(input.getEmail()).orElseThrow();
    }
}
