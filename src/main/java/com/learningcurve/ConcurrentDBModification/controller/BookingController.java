package com.learningcurve.ConcurrentDBModification.controller;

import org.springframework.web.bind.annotation.RestController;

import com.learningcurve.ConcurrentDBModification.dto.BookingRequest;
import com.learningcurve.ConcurrentDBModification.entity.Movie;
import com.learningcurve.ConcurrentDBModification.service.BookMovieTicketTestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class BookingController {

    @Autowired
    private BookMovieTicketTestService movieService;

    @PostMapping(path = "/optimistic/booking", consumes = "application/json", produces = "application/json")
    public Movie bookMovieWithOptimistic(@RequestBody BookingRequest bookingRequest) {
        return movieService.bookMovieTicket(bookingRequest);
    }

    @PostMapping(path = "/pessimistic/booking", consumes = "application/json", produces = "application/json")
    public Movie bookMovieWithPessimistic(@RequestBody BookingRequest bookingRequest) {
        return movieService.bookMovieTicketWithPesimistic(bookingRequest);
    }



}
