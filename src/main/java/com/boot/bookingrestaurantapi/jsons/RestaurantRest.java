package com.boot.bookingrestaurantapi.jsons;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RestaurantRest {
    private Long id;
    private String name;
    private String address;
    private String description;
    private String image;
    private List<TurnRest> turns;

}
