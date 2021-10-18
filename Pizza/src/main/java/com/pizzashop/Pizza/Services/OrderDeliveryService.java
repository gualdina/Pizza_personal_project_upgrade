package com.pizzashop.Pizza.Services;

import com.pizzashop.Pizza.Controllers.Requests.OrderDeliveryRQ;
import com.pizzashop.Pizza.Models.Customer;
import com.pizzashop.Pizza.Models.OrderDelivery;
import com.pizzashop.Pizza.Models.Pizza;
import com.pizzashop.Pizza.Repositories.OrderDeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service//Created for the detailed logic of the controller
public class OrderDeliveryService {

    @Autowired//Connection to the repository and to the pizza service
    private final OrderDeliveryRepository orderDeliveryRepository;
    private final PizzaService pizzaService;

    public OrderDeliveryService(OrderDeliveryRepository orderDeliveryRepository, PizzaService pizzaService) {
        this.orderDeliveryRepository = orderDeliveryRepository;
        this.pizzaService = pizzaService;
    }
    public OrderDelivery getOrderDeliveryById(Long id){

        return orderDeliveryRepository.getById(id);
    }
    public OrderDelivery deleteOrderDelivery(Long id){
        OrderDelivery orderDelivery = getOrderDeliveryById(id);
        orderDeliveryRepository.delete(orderDelivery);
        return orderDelivery;
    }
    public OrderDelivery saveOrder(OrderDeliveryRQ orderDeliveryRQ) {
        String name = orderDeliveryRQ.getName();
        OrderDelivery orderDelivery = OrderDelivery
                .builder()
                .name(name)
                .build();
        return orderDeliveryRepository.save(orderDelivery);
    }
    public OrderDelivery addPizzaToOrder(Long orderDeliveryId, Long pizzaId){
        OrderDelivery orderDelivery = getOrderDeliveryById(orderDeliveryId);
        Pizza pizza = pizzaService.getPizzaById(pizzaId);
        orderDelivery.addingPizzas(pizza);
        return orderDelivery;
    }
    public OrderDelivery removePizzaFromOrder(Long orderDeliveryId, Long pizzaId){
        OrderDelivery orderDelivery = getOrderDeliveryById(orderDeliveryId);
        Pizza pizza = pizzaService.getPizzaById(pizzaId);
        orderDelivery.removingPizzas(pizza);
        return orderDelivery;
    }

}