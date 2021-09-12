package com.boot.bookingrestaurantapi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="restaurants")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique = true,nullable = false)
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name="address")
    private String address;
    @Column(name="description")
    private String description;
    @Column(name="image")
    private String image;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private List<Reservation> reservations;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private List<Turn> turns;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private List<Board> boards;
}
