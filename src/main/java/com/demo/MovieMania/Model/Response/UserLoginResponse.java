package com.demo.MovieMania.Model.Response;

import com.demo.MovieMania.Model.Domain.Enums.Role;
import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLoginResponse {

    private String token;

    private long expiresIn;

    private Role role;

    private String message;

//    private static UserLoginResponse toResponse(String token, Long expiresIn, String message) {
//        return UserLoginResponse.builder()
//                .expiresIn(expiresIn)
//                .token(token)
//                .message(message)
//                .build();
//    }
}
