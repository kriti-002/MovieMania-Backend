package com.demo.MovieMania.Controller;

import com.demo.MovieMania.Model.Domain.Shows;
import com.demo.MovieMania.Model.Request.ShowsRequest;
import com.demo.MovieMania.Model.Response.ShowSeatResponse;
import com.demo.MovieMania.Model.Response.ShowsResponse;
import com.demo.MovieMania.Service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShowController {
    @Autowired
    public ShowService showService;

    @GetMapping("/user/show/search")
    public List<ShowsResponse> search(@RequestParam(name="city",required = true) String city,
                              @RequestParam(name="theatreName",required = false) String theatre,
                              @RequestParam(name="movieName", required = false) String movie){
        return showService.search(city, theatre, movie);

    }
    @GetMapping("user/show/getAvailableSeats")
    public List<ShowSeatResponse> getAvailableShows(@RequestParam Long id){
        return showService.getAvailableShows(id);
    }
    @PostMapping("/admin/shows/add")
    public ShowsResponse addShows(@RequestBody ShowsRequest shows){
        return showService.addShows(shows.toRequest());
    }
    @DeleteMapping("/admin/shows/delete")
    public String deleteShows(@RequestParam Long id){
        return showService.deleteShows(id);
    }
}
