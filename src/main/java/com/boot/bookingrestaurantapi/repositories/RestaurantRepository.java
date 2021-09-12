package com.boot.bookingrestaurantapi.repositories;

import com.boot.bookingrestaurantapi.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {
    @Override
    Optional<Restaurant> findById(Long reservationId);

    Optional<Restaurant> findByName(String nameRestaurant);

    @Query("SELECT Rest FROM Restaurant Rest")
    List<Restaurant> findRestaurants();

    @Query("SELECT Rest from Restaurant Rest JOIN Rest.turns x where x.name=:turnName")
    List<Restaurant> findRestaurantsByTurnName(@Param("turnName") String turnName);

    @Query(value = "SELECT a.id, a.address, a.description, a.image, a.name FROM public.restaurants a inner join public.turns b on a.id=b.restaurant_id where b.name=?1 "
            ,nativeQuery = true)
    List<Restaurant> findRestaurantsByNative(@Param("turnName") String turnName);
}
