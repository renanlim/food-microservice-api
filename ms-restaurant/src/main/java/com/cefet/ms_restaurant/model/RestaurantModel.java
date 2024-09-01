package com.cefet.ms_restaurant.model;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "restaurant")
@Table(name = "restaurant")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "idRestaurant")
public class RestaurantModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idRestaurant;
    private String name;
    private String address;
    private String cellphone;
    private String openingHours;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String password;

}
