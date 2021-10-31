package com.pizzashop.Pizza.controller.response;

import com.pizzashop.Pizza.model.PizzaType;
import lombok.*;

import java.util.List;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PizzaResponse {
    private long id;
    private PizzaType type;
    private String name;
    private char size;
    private int quantity;
    private List<ToppingsResponse> toppingsResponses;
}
