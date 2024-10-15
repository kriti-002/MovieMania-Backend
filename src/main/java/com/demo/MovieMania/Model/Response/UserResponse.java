package com.demo.MovieMania.Model.Response;

import com.demo.MovieMania.Model.Domain.Enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserResponse {
    private Integer age;
    private String name;
    private String email;
    private String password;
    private String mobile;
    private Role role;
    private String message;
    private List<MovieResponse> favourite;

}
