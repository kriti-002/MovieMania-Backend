package com.demo.MovieMania.Controller;

import com.demo.MovieMania.Model.Domain.Theatre;
import com.demo.MovieMania.Model.Request.TheatreRequest;
import com.demo.MovieMania.Model.Response.TheatreResponse;
import com.demo.MovieMania.Service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TheatreController {

    @Autowired
    public TheatreService theatreService;

    @PostMapping("/admin/theatre/add")
    public TheatreResponse addTheatre(@RequestBody TheatreRequest t){
        return theatreService.addTheatre(t.toRequest());
    }
    @DeleteMapping("/admin/theatre/delete")
    public String deleteTheatre(@RequestParam Long id){
        return theatreService.deleteTheatre(id);
    }
    @PutMapping("admin/theatre/update")
    public TheatreResponse updateTheatre(@RequestBody TheatreRequest theatre, @RequestParam Long id){
        return theatreService.updateTheatre(theatre.toRequest(), id);
    }
}
