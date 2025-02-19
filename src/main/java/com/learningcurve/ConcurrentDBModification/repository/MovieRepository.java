package com.learningcurve.ConcurrentDBModification.repository;

import com.learningcurve.ConcurrentDBModification.entity.Movie;

import jakarta.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    
    public Movie findByMovieIdAndSeatNumber(Integer movieId, Integer seatNumber);


    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(value = "SELECT m FROM Movie m WHERE m.movieId = :movieId AND m.seatNumber = :seatNumber")
    public Movie findMovie(Integer movieId, Integer seatNumber);
}
