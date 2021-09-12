package com.boot.bookingrestaurantapi.jsons;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class ReservationRest {
    private String locator;
    private Long restaurantId;
    private Date date;
    private Long person;
    private Long turnId;
}
