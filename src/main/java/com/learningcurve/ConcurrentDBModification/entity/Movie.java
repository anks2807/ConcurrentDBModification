package com.learningcurve.ConcurrentDBModification.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Data;

@Entity
@Table(name = "movie")
@Data
public class Movie {

    @Id
    @Column(name = "movie_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movieId;

    @Column(name = "movie_name", nullable = false)
    private String movieName;

    @Column(name = "duration", nullable = false)
    private Integer duration;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "seat_number")
    private Integer seatNumber;

    @Column(name = "booked")
    private Boolean booked = Boolean.FALSE;

    @Version
    Integer version;



}
