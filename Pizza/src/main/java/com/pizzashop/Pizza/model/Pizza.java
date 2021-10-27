package com.pizzashop.Pizza.Models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
@Entity
@Table(name = "pizza")
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pizza_id" )
    private long id;
    @Enumerated
    private PizzaType type;
    @Column(unique = true)
    private String name;
    private char size;
    @Min(value = 1)
    @Max(value = 9)
    private int quantity;
    @ManyToMany(mappedBy = "addedPizzas")//created to complete/create the order
    private List<OrderDelivery> creationOfOrder = new ArrayList<>();
    @ManyToMany//created to complete pizza with toppings
    @JoinTable( name ="pizza_toppings_mapping",
            joinColumns = { @JoinColumn(name = "pizza_id" ) },
            inverseJoinColumns = { @JoinColumn(name = "toppings_id") }
    )
    private List<Toppings> completingTopping = new ArrayList<>();

    public void removingToppings(Toppings toppings) {
        completingTopping.remove(toppings);
    }

    public void addingToppings(Toppings toppings) {
        completingTopping.add(toppings);
    }
}