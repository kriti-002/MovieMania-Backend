package com.demo.MovieMania.Model.Request;

import com.demo.MovieMania.Model.Domain.Enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginRequest {

    private String email;
    private String password;
    private Role role;

}
