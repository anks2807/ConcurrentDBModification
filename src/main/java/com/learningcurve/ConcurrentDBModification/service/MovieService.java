package com.learningcurve.ConcurrentDBModification.service;

import com.learningcurve.ConcurrentDBModification.dto.BookingRequest;
import com.learningcurve.ConcurrentDBModification.entity.Movie;
import com.learningcurve.ConcurrentDBModification.repository.MovieRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Movie bookMovieTicket(BookingRequest bookingRequest) {
        Movie movie = movieRepository.findByMovieIdAndSeatNumber(bookingRequest.getMovieId(), bookingRequest.getSeatNumber());
        log.info(Thread.currentThread().getName() + " accessed the ticket for movieId: " + bookingRequest.getMovieId() + " and seatNumber: " + bookingRequest.getSeatNumber());
        movie.setBooked(Boolean.TRUE);
        Movie bookedMovie = movieRepository.save(movie);
        log.info(Thread.currentThread().getName() + " booked the ticket for movieId: " + bookingRequest.getMovieId() + " and seatNumber: " + bookingRequest.getSeatNumber());
        return bookedMovie;
    }


    @Transactional
    public Movie bookMovieTicketWithPessimistic(BookingRequest bookingRequest) {
        Movie movie = movieRepository.findMovie(bookingRequest.getMovieId(), bookingRequest.getSeatNumber());
        log.info(Thread.currentThread().getName() + " accessed the ticket for movieId: " + bookingRequest.getMovieId() + " and seatNumber: " + bookingRequest.getSeatNumber());
        if (movie.getBooked()) {
            log.info(Thread.currentThread().getName() + " could not book the ticket for movieId: " + bookingRequest.getMovieId() + " and seatNumber: " + bookingRequest.getSeatNumber());
            throw new RuntimeException("Seat is already booked");
        }
        movie.setBooked(Boolean.TRUE);
        Movie bookedMovie = movieRepository.save(movie);
        log.info(Thread.currentThread().getName() + " booked the ticket for movieId: " + bookingRequest.getMovieId() + " and seatNumber: " + bookingRequest.getSeatNumber());
        return bookedMovie;
    }
    
}
