package com.boot.bookingrestaurantapi.services;

import com.boot.bookingrestaurantapi.entities.Restaurant;
import com.boot.bookingrestaurantapi.exceptions.BookingException;
import com.boot.bookingrestaurantapi.jsons.CreateRestaurantRest;
import com.boot.bookingrestaurantapi.jsons.RestaurantRest;

import java.util.List;

public interface RestaurantService {
    RestaurantRest getRestaurantById(Long restaurantId) throws BookingException;
    List<RestaurantRest> getRestaurants() throws BookingException;
    RestaurantRest createRestaurant(CreateRestaurantRest createRestaurantRest) throws BookingException;

    List<RestaurantRest> getFindRestaurants() throws BookingException;
    List<RestaurantRest> getFindRestaurantsByTurnName(String turnName) throws BookingException;

    List<RestaurantRest> getFindRestaurantsByNative(String turnName) throws BookingException;
}
