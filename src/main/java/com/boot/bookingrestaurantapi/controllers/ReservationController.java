package com.boot.bookingrestaurantapi.controllers;

import com.boot.bookingrestaurantapi.exceptions.BookingException;
import com.boot.bookingrestaurantapi.jsons.CreateReservationRest;
import com.boot.bookingrestaurantapi.jsons.ReservationRest;
import com.boot.bookingrestaurantapi.responses.BookingResponse;
import com.boot.bookingrestaurantapi.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/booking-restaurant/"+"/v1")
public class ReservationController {
    @Autowired
    ReservationService reservationService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/reservation/{reservationId}")
    public BookingResponse<ReservationRest> getReservationById(@PathVariable Long reservationId) throws BookingException{
        return new BookingResponse<ReservationRest>("success",String.valueOf(HttpStatus.OK),"OK",
                    reservationService.getReservationById(reservationId));
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/reservation")
    public BookingResponse<String> CreateReservation(@RequestBody CreateReservationRest reservation) throws BookingException{
        return new BookingResponse<String>("success",String.valueOf(HttpStatus.OK),"OK",
                reservationService.createReservation(reservation));
    }

}
