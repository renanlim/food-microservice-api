package com.cefet.ms_order.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "orders")
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "idOrder")
public class OrderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idOrder;

    @Column(name = "idRestaurant")
    private String idRestaurant;

    @Column(name = "idCustomer")
    private String idCustomer;

    private String status;
    private String payment;
    private String observation;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = false)
    @JsonManagedReference
    private List<OrderItemModel> items = new ArrayList<>();
}
