package com.pizzashop.Pizza.controller;

import com.pizzashop.Pizza.controller.request.OrderDeliveryRQ;
import com.pizzashop.Pizza.model.OrderDelivery;
import com.pizzashop.Pizza.controller.response.OrderDeliveryResponse;
import com.pizzashop.Pizza.service.OrderDeliveryService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;


@RestController
@RequestMapping("/api")
@Validated
public class OrderDeliveryController {
    private final OrderDeliveryService orderDeliveryService;

    public OrderDeliveryController(OrderDeliveryService orderDeliveryService) {
        this.orderDeliveryService = orderDeliveryService;
    }
    //get order delivery  by id
    @GetMapping("/OrderDelivery/{id}")
    public OrderDeliveryResponse getOrderDeliveryById(@PathVariable(value = "id") Long id){
        OrderDelivery orderDelivery = orderDeliveryService.getOrderDeliveryById(id);
        List<PizzaResponse> pizzaResponse= new ArrayList<>();
        OrderDeliveryResponse orderDeliveryResponse = new OrderDeliveryResponse(orderDelivery.getId(),
                orderDelivery.getName(),
                pizzaResponses);
    }
    @PostMapping(value = "/OrderDelivery", consumes = "application/json")
    public ResponseEntity<OrderDeliveryResponse> saveOrder(@RequestBody OrderDeliveryRQ orderDeliveryRQ){
        return ResponseEntity.ok(orderDeliveryService.saveOrder(orderDeliveryRQ));
    }
    @PostMapping(value = "/Pizza-OrderDelivery", consumes = "application/json")
    public OrderDelivery addPizzaToOrder(@RequestBody @Valid Long  orderDeliveryId, Long pizzaId){
        return orderDeliveryService.addPizzaToOrder(orderDeliveryId, pizzaId);
    }
    @DeleteMapping("/Pizza-OrderDelivery")
    public void removePizzaFromOrder(@PathVariable Long orderDeliveryId, Long pizzaId){
        orderDeliveryService.removePizzaFromOrder(orderDeliveryId, pizzaId);
    }
    @DeleteMapping("/OrderDelivery/{id}")
    public void deleteOrderDelivery(@PathVariable Long id){
        orderDeliveryService.deleteOrderDelivery(id);
    }
}
