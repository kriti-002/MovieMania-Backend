package com.demo.MovieMania.Service;
import com.demo.MovieMania.Model.Response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.Map;

@Service
public class ApiService {

    @Autowired
    private RestTemplate restTemplate;

    public String getExternalApiResponse() {
        String url = "http://127.0.0.1:5000/analyze";
        ResponseEntity<ApiResponse> response = restTemplate.postForEntity(url, null, ApiResponse.class);

        // Extract the sentiment list
        return response.getBody() != null ? response.getBody().getSentiment() : null;
    }
}

