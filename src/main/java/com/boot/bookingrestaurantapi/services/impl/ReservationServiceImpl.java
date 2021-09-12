package com.boot.bookingrestaurantapi.services.impl;

import com.boot.bookingrestaurantapi.entities.Reservation;
import com.boot.bookingrestaurantapi.entities.Restaurant;
import com.boot.bookingrestaurantapi.entities.Turn;
import com.boot.bookingrestaurantapi.exceptions.BookingException;
import com.boot.bookingrestaurantapi.exceptions.InternalServerErrorException;
import com.boot.bookingrestaurantapi.exceptions.NotFountException;
import com.boot.bookingrestaurantapi.jsons.CreateReservationRest;
import com.boot.bookingrestaurantapi.jsons.ReservationRest;
import com.boot.bookingrestaurantapi.repositories.ReservationRepository;
import com.boot.bookingrestaurantapi.repositories.RestaurantRepository;
import com.boot.bookingrestaurantapi.repositories.TurnRepository;
import com.boot.bookingrestaurantapi.services.ReservationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private TurnRepository turnRepository;

    public static final ModelMapper modelMapper = new ModelMapper();

    @Override
    public ReservationRest getReservationById(Long reservationId) throws BookingException {
        return modelMapper.map(getReservationEntity(reservationId),ReservationRest.class);
    }

    @Override
    public String createReservation(CreateReservationRest createReservationRest) throws BookingException {
        final Restaurant restaurant =  restaurantRepository.findById(createReservationRest.getRestaurantId())
                                                                .orElseThrow(() -> new NotFountException("SNOT-404-1","RESTAURANT_NOT_FOUND"));
        final Turn turn = turnRepository.findById(createReservationRest.getTurnId())
                                            .orElseThrow(()->new NotFountException("SNOT-404-1","TURN_NOT_FOUND"));
        if(reservationRepository.findByTurnAndRestaurantId(turn.getName(),restaurant.getId()).isPresent()){
          throw new NotFountException("RESERVATION_EXIST","RESERVATION_EXIST");
        }

        String locator = generatorLocator(restaurant,createReservationRest);

        Reservation reservation = new Reservation();
        reservation.setLocator(locator);
        reservation.setDate(createReservationRest.getDate());
        reservation.setPerson(createReservationRest.getPerson());
        reservation.setTurn(turn.getName());
        reservation.setRestaurant(restaurant);
        try{
            reservationRepository.save(reservation);
        }catch (final Exception e){
            throw new InternalServerErrorException("INTERNAL_ERROR","INTERNAL_ERROR");
        }
        return locator;
    }

    private String generatorLocator(Restaurant restaurant, CreateReservationRest createReservationRest) throws BookingException{
        return restaurant.getName()+createReservationRest.getTurnId();
    }
    private Reservation getReservationEntity(Long reservationId) throws BookingException{
        return reservationRepository.findById(reservationId)
                .orElseThrow(()-> new NotFountException("SNOT-404-1","RESERVATION_NOT_FOUND"));
    }
}
