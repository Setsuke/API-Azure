package com.boot.bookingrestaurantapi.jsons;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRestaurantRest {
    private String name;
    private String address;
    private String description;
    private String image;
}
