package com.demo.MovieMania.Model.Request;

import com.demo.MovieMania.Model.Domain.Theatre;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TheatreRequest {

    @NotNull(message = "Theatre Name should not be null.")
    private String name;

    @NotNull(message = "City should not be null.")
    private String city;

    @NotNull(message = "Address should not be null.")
    private String address;

    public Theatre toRequest(){
        return Theatre.builder()
                .city(city)
                .address(address)
                .name(name)
                .build();
    }
}
