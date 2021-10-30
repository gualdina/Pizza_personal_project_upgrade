package com.pizzashop.Pizza.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
@Entity
@Table
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id")
    private long Id;
    private String firstName;
    private String lastName;
    private int phoneNumber;
    private String city;
    private String address;
    @OneToMany(mappedBy = "customers")//to connect customer to order
    private List<OrderDelivery> orderToDelivery;
}
