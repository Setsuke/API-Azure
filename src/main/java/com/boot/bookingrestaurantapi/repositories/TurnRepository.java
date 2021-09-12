package com.boot.bookingrestaurantapi.repositories;

import com.boot.bookingrestaurantapi.entities.Turn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurnRepository extends JpaRepository<Turn, Long> {
}
