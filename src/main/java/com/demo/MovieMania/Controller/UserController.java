package com.demo.MovieMania.Controller;

import com.demo.MovieMania.Model.Domain.User;
import com.demo.MovieMania.Model.Request.UserLoginRequest;
import com.demo.MovieMania.Model.Request.UserRequest;
import com.demo.MovieMania.Model.Response.UserLoginResponse;
import com.demo.MovieMania.Model.Response.UserResponse;
import com.demo.MovieMania.Service.AuthenticationService;
import com.demo.MovieMania.Service.JwtService;
import com.demo.MovieMania.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/admin/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public UserResponse addUser(@RequestBody UserRequest userRequest){
        return userService.addUser(userRequest);
    }
    @PostMapping("/login")
    public UserLoginResponse login(@RequestBody UserLoginRequest userLoginRequest){
        return userService.login(userLoginRequest);
    }
    @DeleteMapping("/delete")
    public String deleteUser(@RequestParam Long id){
        return userService.deleteUser(id);
    }
    @GetMapping("/get")
    public UserResponse getUser(@RequestParam Long id){
        return userService.getUser(id);
    }
    @GetMapping("/me")
    public UserResponse authenticatedUser() {
        return userService.authenticateUser();
    }
    @PutMapping("/update")
    public UserResponse updateUser(@RequestParam Long id, @RequestBody UserRequest u){
        return userService.updateUserDetails(id, u);
    }

}
