package com.boot.bookingrestaurantapi.controllers;

import com.boot.bookingrestaurantapi.exceptions.BookingException;
import com.boot.bookingrestaurantapi.responses.BookingResponse;
import com.boot.bookingrestaurantapi.services.CancelReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/booking-restaurant/"+"/v1")
public class CancelReservationController {
    @Autowired
    CancelReservationService cancelReservationService;

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping()
    public BookingResponse<String> deleteReservation(@RequestParam String locator) throws BookingException{
        return new BookingResponse<String>("Success",String.valueOf(HttpStatus.OK),"OK",
                cancelReservationService.deleteReservation(locator));
    }
}
