package com.jbdl58.moviemania.Controller.User;

import com.jbdl58.moviemania.Model.Request.UserRequest;
import com.jbdl58.moviemania.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public String addUser(@RequestBody UserRequest userRequest){
        return userService.addUser(userRequest);
    }
    @GetMapping("/get")
    public String getUser(@RequestBody String id){
        return userService.getUser(id);
    }
    @DeleteMapping("/delete")
    public String deleteUser(@RequestBody String id){
        return userService.deleteUser(id);
    }
}
