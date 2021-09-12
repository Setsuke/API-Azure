package com.boot.bookingrestaurantapi.services.impl;

import com.boot.bookingrestaurantapi.entities.Restaurant;
import com.boot.bookingrestaurantapi.exceptions.BookingException;
import com.boot.bookingrestaurantapi.exceptions.InternalServerErrorException;
import com.boot.bookingrestaurantapi.exceptions.NotFountException;
import com.boot.bookingrestaurantapi.jsons.CreateRestaurantRest;
import com.boot.bookingrestaurantapi.jsons.RestaurantRest;
import com.boot.bookingrestaurantapi.repositories.RestaurantRepository;
import com.boot.bookingrestaurantapi.services.RestaurantService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;

    public static final ModelMapper modelMapper = new ModelMapper();


    @Override
    public RestaurantRest getRestaurantById(Long restaurantID) throws BookingException {
        return modelMapper.map(getRestaurantEntity(restaurantID), RestaurantRest.class);
    }

    @Override
    public List<RestaurantRest> getRestaurants() throws BookingException {
        final List<Restaurant> restaurantsEntity=restaurantRepository.findAll();

        return restaurantsEntity.stream().map(service->modelMapper.map(service,RestaurantRest.class))
                .collect(Collectors.toList());
    }

    @Override
    public RestaurantRest createRestaurant(CreateRestaurantRest createRestaurantRest) throws BookingException {
        Restaurant restaurantEntity;
        Restaurant restaurant= new Restaurant();
        restaurant.setName(createRestaurantRest.getName());
        restaurant.setAddress(createRestaurantRest.getAddress());
        restaurant.setDescription(createRestaurantRest.getDescription());
        restaurant.setImage(createRestaurantRest.getImage());

        try{
            restaurantEntity=restaurantRepository.save(restaurant);
        }catch (final Exception e){

            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","INTERNAL_SERVER_ERROR");
        }

        return modelMapper.map(getRestaurantEntity(restaurantEntity.getId()),RestaurantRest.class);
    }

    @Override
    public List<RestaurantRest> getFindRestaurants() throws BookingException {
        final List<Restaurant> restaurants = restaurantRepository.findRestaurants();
        return restaurants.stream().map(service -> modelMapper.map(service,RestaurantRest.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<RestaurantRest> getFindRestaurantsByTurnName(String turnName) throws BookingException {
        final List<Restaurant> restaurants = restaurantRepository.findRestaurantsByTurnName(turnName);
        return restaurants.stream().map(service -> modelMapper.map(service,RestaurantRest.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<RestaurantRest> getFindRestaurantsByNative(String turnName) throws BookingException {
        final List<Restaurant> restaurants = restaurantRepository.findRestaurantsByNative(turnName);
        return restaurants.stream().map(service -> modelMapper.map(service,RestaurantRest.class))
                .collect(Collectors.toList());
    }

    private Restaurant getRestaurantEntity(Long restaurantId)throws BookingException{
        return restaurantRepository.findById(restaurantId)
                                    .orElseThrow(()->new NotFountException("SNOT-404-1","RESTAURANT_NOT_FOUND"));
    }

}
