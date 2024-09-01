package com.cefet.ms_customer.model;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "customer")
@Table(name = "customer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "idCustomer")
public class CustomerModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idCustomer;
    private String name;
    private String address;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String password;

}