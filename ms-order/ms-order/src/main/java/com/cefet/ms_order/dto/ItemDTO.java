package com.cefet.ms_order.dto;

import lombok.*;

@Getter
@Setter
public class ItemDTO {
    private String idItem;
    private String idRestaurant;
    private String name;
    private Float price;
    private String description;
}
