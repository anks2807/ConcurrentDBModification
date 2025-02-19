package com.learningcurve.ConcurrentDBModification.controller;

import com.learningcurve.ConcurrentDBModification.entity.Movie;
import com.learningcurve.ConcurrentDBModification.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping
    public Movie createMovie(@RequestBody Movie movie) {
        return movieService.saveMovie(movie);
    }
}
