package com.boot.bookingrestaurantapi.repositories;

import com.boot.bookingrestaurantapi.entities.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
