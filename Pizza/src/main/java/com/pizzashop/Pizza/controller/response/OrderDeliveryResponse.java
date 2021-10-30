package com.pizzashop.Pizza.controller.response;

import lombok.*;

import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDeliveryResponse {
    private Long Id;
    private String name;
    private List<PizzaResponse> pizzaResponses;
}
