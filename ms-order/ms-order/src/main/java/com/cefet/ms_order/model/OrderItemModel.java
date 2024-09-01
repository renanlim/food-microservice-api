package com.cefet.ms_order.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "idOrderItem")
public class OrderItemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idOrderItem;

    @ManyToOne
    @JoinColumn(name = "idOrder", nullable = false)
    @JsonBackReference
    private OrderModel order;

    @Column(name = "idItem")
    private String idItem;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Float price;

    @Column(name = "description")
    private String description;

    @Column(name = "quantity")
    private Integer quantity;
}
