package com.demo.MovieMania.Model.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TheatreResponse {
    private String message;
    private String city;
    private String Address;
    private String name;
}
