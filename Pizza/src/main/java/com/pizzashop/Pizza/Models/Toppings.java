package com.pizzashop.Pizza.Models;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
@Entity
@Table(name="toppings")
public class Toppings {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "toppings_id" )
    private long Id;
    private String sauce;
    public String toppingName;
    @ManyToMany(mappedBy = "completingTopping")//created to create pizza with the toppings selected
    private List<Pizza> completePizza = new ArrayList<>();
}
