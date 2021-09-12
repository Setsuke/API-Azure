package com.boot.bookingrestaurantapi.exceptions;

import com.boot.bookingrestaurantapi.dtos.ErrorDto;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

public class NotFountException  extends BookingException{

    public NotFountException(String code, String message) {
        super(code, HttpStatus.NOT_FOUND.value(), message);
    }

    public NotFountException(String code,  String message, ErrorDto data) {
        super(code, HttpStatus.NOT_FOUND.value(), message, Arrays.asList(data));
    }
}
