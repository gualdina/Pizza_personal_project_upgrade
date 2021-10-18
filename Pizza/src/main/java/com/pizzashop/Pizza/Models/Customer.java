package com.pizzashop.Pizza.Models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
@Entity
@Table(name = "customer")
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
    @ManyToOne//to connect customer to order
    @JoinColumn(name = "orderDelivery_Id")
    private OrderDelivery orderDelivery;
}
