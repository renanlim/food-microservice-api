package com.cefet.ms_item.model;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "item")
@Table(name = "item")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "idItem")
public class ItemModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idItem;
    @JoinColumn(name = "idRestaurant")
    private String idRestaurant;
    private String name;
    private Float price;
    private String description;

}
