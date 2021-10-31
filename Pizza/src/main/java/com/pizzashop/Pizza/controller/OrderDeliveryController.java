package com.pizzashop.Pizza.controller;

import com.pizzashop.Pizza.controller.request.OrderDeliveryRQ;
import com.pizzashop.Pizza.controller.request.PizzaRQ;
import com.pizzashop.Pizza.controller.response.OrderDeliveryResponse;
import com.pizzashop.Pizza.controller.response.PizzaResponse;
import com.pizzashop.Pizza.model.OrderDelivery;
import com.pizzashop.Pizza.model.Pizza;
import com.pizzashop.Pizza.service.OrderDeliveryService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
public class OrderDeliveryController {
    private final OrderDeliveryService orderDeliveryService;

    public OrderDeliveryController(OrderDeliveryService orderDeliveryService) {
        this.orderDeliveryService = orderDeliveryService;
    }
    //get order delivery by id
    @GetMapping("/OrderDelivery/{id}")
    public OrderDeliveryResponse getOrderDeliveryById(@PathVariable(value = "id") Long id) {
        OrderDelivery orderDelivery = orderDeliveryService.getOrderDeliveryById(id);
        List<PizzaResponse> pizzaResponse = new ArrayList<>();
        OrderDeliveryResponse orderDeliveryResponse = new OrderDeliveryResponse(orderDelivery.getId(),
                orderDelivery.getName(),
                pizzaResponse);
        return orderDeliveryResponse;
    }

    @PostMapping(value = "/OrderDelivery")
    public OrderDeliveryResponse addOrder(@RequestBody OrderDeliveryRQ orderDeliveryRQ){
        OrderDelivery newOrderDelivery = OrderDelivery
                .builder()
                .name(orderDeliveryRQ.getName())
                .build();
        orderDeliveryService.addOrder(newOrderDelivery);
        OrderDeliveryResponse orderDeliveryResponse = new OrderDeliveryResponse();
        orderDeliveryResponse.setId(newOrderDelivery.getId());
        orderDeliveryResponse.setName(newOrderDelivery.getName());
        return orderDeliveryResponse;
    }
    //add pizzas to order
    @PostMapping(value = "/Pizza-OrderDelivery")
    public OrderDeliveryResponse addPizzaToOrder(@RequestBody List<PizzaRQ>  pizzaRQS, Long id){
            List<Pizza> pizza =  new ArrayList<>();
             for (PizzaRQ pizzaRQ : pizzaRQS) {pizza.add(Pizza
                     .builder()
                     .type(pizzaRQ.getType())
                     .name(pizzaRQ.getName())
                     .size(pizzaRQ.getSize())
                     .quantity(pizzaRQ.getQuantity())
                     .build());
             }
        OrderDelivery orderDelivery = orderDeliveryService.addPizzaToOrder(pizza,id);
        List<PizzaResponse> pizzaResponses = new ArrayList<>();
        OrderDeliveryResponse orderDeliveryResponse = new OrderDeliveryResponse(
                orderDelivery.getId(),
                orderDelivery.getName(),pizzaResponses);
        for(Pizza pizzas1 : orderDelivery.getAddedPizzas()){
            PizzaResponse pizzaResp = PizzaResponse.builder()
                    .id(pizzas1.getId())
                    .type(pizzas1.getType())
                    .name(pizzas1.getName())
                    .size(pizzas1.getSize())
                    .quantity(pizzas1.getQuantity())
                    .build();
            orderDeliveryResponse.getPizzaResponses().add(pizzaResp);
        }
        return orderDeliveryResponse;
    }
    @DeleteMapping("/OrderDelivery/{id}")
    public void deleteOrderDelivery(@PathVariable Long id){
        orderDeliveryService.deleteOrderDelivery(id);
    }
}