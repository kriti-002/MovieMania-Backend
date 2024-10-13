package com.demo.MovieMania.Model.Response;

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

    private String message;

//    private static UserLoginResponse toResponse(String token, Long expiresIn, String message) {
//        return UserLoginResponse.builder()
//                .expiresIn(expiresIn)
//                .token(token)
//                .message(message)
//                .build();
//    }
}
