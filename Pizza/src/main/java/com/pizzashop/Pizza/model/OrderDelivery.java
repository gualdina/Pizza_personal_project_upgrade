package com.pizzashop.Pizza.Models;

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
    @OneToMany//created for the customer connection with order
    @JoinColumn(name = "orderDelivery_id")
    private Set<Customer> customers;
    @ManyToMany//connection to insert and remove pizzas in the order
    @JoinTable( name ="orderDelivery_pizza_mapping",
            joinColumns = { @JoinColumn(name = "orderDelivery_id" ) },
            inverseJoinColumns = { @JoinColumn(name = "pizza_id") }
    )
    private List<Pizza> addedPizzas = new ArrayList<>();
    public void addingPizzas(Pizza pizza) {
        addedPizzas.add(pizza);
    }
    public void removingPizzas(Pizza pizza) {
        addedPizzas.remove(pizza);
    }
}
