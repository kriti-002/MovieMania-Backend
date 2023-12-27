package com.jbdl58.moviemania.Controller.Error;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorController {
    @GetMapping("/error")
    public String get(){
        return "error";
    }
}
