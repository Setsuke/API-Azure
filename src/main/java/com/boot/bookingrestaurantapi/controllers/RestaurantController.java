package com.boot.bookingrestaurantapi.controllers;

import com.boot.bookingrestaurantapi.exceptions.BookingException;
import com.boot.bookingrestaurantapi.jsons.CreateRestaurantRest;
import com.boot.bookingrestaurantapi.jsons.RestaurantRest;
import com.boot.bookingrestaurantapi.responses.BookingResponse;
import com.boot.bookingrestaurantapi.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path="/booking-restaurant"+"/v1")
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/restaurant/{restaurantId}")
    public BookingResponse<RestaurantRest> getRestaurantById(@PathVariable Long restaurantId)throws BookingException{
        return new BookingResponse<>("Succes",String.valueOf(HttpStatus.OK),"OK",
                restaurantService.getRestaurantById(restaurantId));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/restaurants")
    public BookingResponse<List<RestaurantRest>> getRestaurants() throws BookingException{
        return new BookingResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
                restaurantService.getRestaurants());
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/restaurants")
    public BookingResponse<RestaurantRest> createRestaurants(@RequestBody @Valid CreateRestaurantRest createRestaurant) throws BookingException{
        return new BookingResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
                restaurantService.createRestaurant(createRestaurant));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/restaurantsFound")
    public BookingResponse<List<RestaurantRest>> getFindRestaurants() throws BookingException {
        return new BookingResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
                restaurantService.getFindRestaurants());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/restaurants/{turnName}")
    public BookingResponse<List<RestaurantRest>> getFindRestaurantsByTurnName(@PathVariable String turnName) throws BookingException {
        return new BookingResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
                restaurantService.getFindRestaurantsByTurnName(turnName));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/restaurantsNative/{turnName}")
    public BookingResponse<List<RestaurantRest>> getFindRestaurantsByNative(@PathVariable String turnName) throws BookingException {
        return new BookingResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
                restaurantService.getFindRestaurantsByNative(turnName));
    }
}
