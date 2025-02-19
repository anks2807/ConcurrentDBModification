package com.learningcurve.ConcurrentDBModification.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class BookingRequest {

    @JsonProperty("movie_id")
    int movieId;

    @JsonProperty("seat_number")
    Integer seatNumber;

}
