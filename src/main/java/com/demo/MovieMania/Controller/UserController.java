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
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/auth/add")
    public UserResponse addUser(@RequestBody UserRequest userRequest){
        User registeredUser = authenticationService.signup(userRequest);
        if(registeredUser != null) return registeredUser.toResponse("Signed Up");
        return UserResponse.builder().message("Please check the guidelines before creating the account.").build();
    }
    @PostMapping("/auth/login")
    public UserLoginResponse login(@RequestBody UserLoginRequest userLoginRequest){
        User authenticatedUser = authenticationService.authenticate(userLoginRequest);
        //System.out.println(authenticatedUser.getId() + " " + authenticatedUser.getName());

        HashMap<String, String> map= new HashMap<>();
        map.put("Id", authenticatedUser.getId().toString());
        map.put("Email", authenticatedUser.getEmail());
        map.put("Role", authenticatedUser.getRole().toString());

        String jwtToken = jwtService.generateToken(map, authenticatedUser);

        return UserLoginResponse.builder()
                .token(jwtToken)
                .expiresIn(jwtService.getExpirationTime())
                .message("I got the response.")
                .role(authenticatedUser.getRole())
                .build();
    }
    @DeleteMapping("/admin/delete")
    public String deleteUser(@RequestParam Long id){
        return userService.deleteUser(id);
    }
    @GetMapping("/admin/get")
    public UserResponse getUser(@RequestParam Long id){
        return userService.getUser(id);
    }
    @GetMapping("/admin/me")
    public UserResponse authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        return currentUser.toResponse("I got my details.");
    }
    @PutMapping("/admin/update")
    public UserResponse updateUser(@RequestParam Long id, @RequestBody UserRequest u){
        return userService.updateUserDetails(id, u);
    }

}
