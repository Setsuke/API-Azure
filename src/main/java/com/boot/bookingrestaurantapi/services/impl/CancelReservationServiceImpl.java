package com.boot.bookingrestaurantapi.services.impl;

import com.boot.bookingrestaurantapi.entities.Reservation;
import com.boot.bookingrestaurantapi.exceptions.BookingException;
import com.boot.bookingrestaurantapi.exceptions.InternalServerErrorException;
import com.boot.bookingrestaurantapi.exceptions.NotFountException;
import com.boot.bookingrestaurantapi.repositories.ReservationRepository;
import com.boot.bookingrestaurantapi.services.CancelReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CancelReservationServiceImpl implements CancelReservationService {

    @Autowired
    private ReservationRepository reservationRepository;


    @Override
    public String deleteReservation(String locator) throws BookingException {
        reservationRepository.findByLocator(locator)
                .orElseThrow(()-> new NotFountException("LOCATOR__NOT_FOUND","LOCATOR__NOT_FOUND"));
        try {
            reservationRepository.deleteByLocator(locator);
        }catch (final Exception e){
            throw new InternalServerErrorException("INTERNAL_ERROR","INTERNAL_ERROR");
        }
        return null;
    }
}
