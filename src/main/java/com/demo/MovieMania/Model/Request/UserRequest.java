package com.demo.MovieMania.Model.Request;

import com.demo.MovieMania.Model.Domain.Enums.Role;
import com.demo.MovieMania.Model.Domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRequest {

    private Integer age;
    private String name;
    private String email;
    private String password;
    private String mobile;
    private Role role;

    public User toRequest(){
        return User.builder()
                .age(age)
                .email(email)
                .mobile(mobile)
                .password(password)
                .name(name)
                .role(role)
                .build();
    }
    public User toSignUpRequest(String encodedPassword){
        return User.builder()
                .age(age)
                .email(email)
                .mobile(mobile)
                .password(encodedPassword)
                .name(name)
                .role(role)
                .build();
    }
}
