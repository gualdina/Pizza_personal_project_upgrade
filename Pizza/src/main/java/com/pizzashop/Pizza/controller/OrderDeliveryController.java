package com.pizzashop.Pizza.Controllers;

import com.pizzashop.Pizza.Controllers.Requests.OrderDeliveryRQ;
import com.pizzashop.Pizza.Models.OrderDelivery;
import com.pizzashop.Pizza.Services.OrderDeliveryService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@Validated
public class OrderDeliveryController {
    private final OrderDeliveryService orderDeliveryService;

    public OrderDeliveryController(OrderDeliveryService orderDeliveryService) {
        this.orderDeliveryService = orderDeliveryService;
    }
    @GetMapping("/OrderDelivery/{id}")
    public OrderDelivery getOrderDeliveryById(@PathVariable Long id){
        return orderDeliveryService.getOrderDeliveryById(id);
    }
    @PostMapping(value = "/OrderDelivery", consumes = "application/json")
    public OrderDelivery saveOrder(@RequestBody @Valid OrderDeliveryRQ orderDeliveryRQ){
        return orderDeliveryService.saveOrder(orderDeliveryRQ);
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