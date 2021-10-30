package com.pizzashop.Pizza.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
@Entity
@Table (name = "orderDelivery")
public class OrderDelivery {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "orderDelivery_id" )
    private Long Id;
    @NotNull(message = "Name required" )
    private String name;
   //created for the customer connection with
    @ManyToOne
    @JoinColumn(name = "customers_customer_id")
    private Customer customers;
    @ManyToMany//connection to insert and remove pizzas in the order
    @JoinTable(
            name ="orderDelivery_pizza_mapping",
            joinColumns = { @JoinColumn(name = "orderDelivery_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "pizza_id", referencedColumnName = "id") })
    List<Pizza> addedPizzas = new ArrayList<Pizza>();
}
