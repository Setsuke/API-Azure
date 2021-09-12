package com.boot.bookingrestaurantapi.controller;

import com.boot.bookingrestaurantapi.controllers.RestaurantController;
import com.boot.bookingrestaurantapi.exceptions.BookingException;
import com.boot.bookingrestaurantapi.jsons.RestaurantRest;
import com.boot.bookingrestaurantapi.jsons.TurnRest;
import com.boot.bookingrestaurantapi.responses.BookingResponse;
import com.boot.bookingrestaurantapi.services.RestaurantService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RestaurantControllerTest {
    private static final Long RESTAURANT_ID = 1L;
    private static final String NAME = "Buger";
    private static final String DESCRIPTION = "Todo tipo de hamburguesas";
    private static final String ADDRES = "Av.Galindo";
    private static final String IMAGE = "www.image.com";

    private static final String SUCCES_STATUS = "Succes";
    private static final String SUCCES_CODE = "200 OK";
    private static final String OK = "OK";

    private static final List<TurnRest> TURN_LIST = new ArrayList<>();
    private static final RestaurantRest RESTAURANT_REST = new RestaurantRest();
    private static final List<RestaurantRest> RESTAURANT_REST_LIST = new ArrayList<>();

    @Mock
    private RestaurantService restaurantService;

    @InjectMocks
    private RestaurantController restaurantController;

    @Before
    public void init() throws BookingException{
        MockitoAnnotations.initMocks(this);

        RESTAURANT_REST.setId(RESTAURANT_ID);
        RESTAURANT_REST.setName(NAME);
        RESTAURANT_REST.setDescription(DESCRIPTION);
        RESTAURANT_REST.setAddress(ADDRES);
        RESTAURANT_REST.setImage(IMAGE);
        RESTAURANT_REST.setTurns(TURN_LIST);

        Mockito.when(restaurantService.getRestaurantById(RESTAURANT_ID))
                .thenReturn(RESTAURANT_REST);
    }
    @Test
    public void getRestaurantByIdTest() throws BookingException{
        BookingResponse<RestaurantRest> response = restaurantController.getRestaurantById(RESTAURANT_ID);

        assertEquals(response.getStatus(),SUCCES_STATUS);
        assertEquals(response.getCode(),SUCCES_CODE );
        assertEquals(response.getMessage(),OK);
        assertEquals(response.getData(),RESTAURANT_REST);

    }
}
