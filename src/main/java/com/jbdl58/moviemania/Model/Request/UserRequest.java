package com.jbdl58.moviemania.Model.Request;

import com.jbdl58.moviemania.Model.Domain.User;
import com.jbdl58.moviemania.Model.Enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {
    private Integer age;

    private String email;

    private String mobile;

    private String name;

    private String password;

    private Role role;

    public User toUser() {
        return User.builder()
                .id(UUID.randomUUID().toString())
                .age(this.age)
                .email(this.email)
                .mobile(this.mobile)
                .name(this.name)
                .password(this.password)
                .role(this.role)
                .build();
    }
}
