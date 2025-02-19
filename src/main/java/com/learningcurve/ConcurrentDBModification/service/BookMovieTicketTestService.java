package com.learningcurve.ConcurrentDBModification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learningcurve.ConcurrentDBModification.dto.BookingRequest;
import com.learningcurve.ConcurrentDBModification.entity.Movie;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookMovieTicketTestService {


    @Autowired
    private MovieService movieService;

    public Movie bookMovieTicket(BookingRequest bookingRequest) {
        final Movie[] movie = {null};
        Thread thread1 = new Thread(() -> {
            movie[0] = movieService.bookMovieTicket(bookingRequest);
        });

        Thread thread2 = new Thread(() -> {
            movie[0] = movieService.bookMovieTicket(bookingRequest);
        });
        
        thread1.start();
        log.info("Thread" + thread1.getName()+ "started!!!");
        thread2.start();
        log.info("Thread" + thread2.getName()+ "started!!!");
        return movie[0];
    }

    public Movie bookMovieTicketWithPesimistic(BookingRequest bookingRequest) {
        final Movie[] movie = {null};
        Thread thread1 = new Thread(() -> {
            movie[0] = movieService.bookMovieTicketWithPessimistic(bookingRequest);
        });

        Thread thread2 = new Thread(() -> {
            movie[0] = movieService.bookMovieTicketWithPessimistic(bookingRequest);
        });
        
        thread1.start();
        
        log.info(thread1.getName()+ "started!!!");
        thread2.start();
        
        log.info(thread2.getName()+ "started!!!");
        try {
            thread1.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return movie[0];
    }

}
