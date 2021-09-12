package com.boot.bookingrestaurantapi.services;

import com.boot.bookingrestaurantapi.exceptions.BookingException;

public interface CancelReservationService {
    String deleteReservation(String locator) throws BookingException;
}
