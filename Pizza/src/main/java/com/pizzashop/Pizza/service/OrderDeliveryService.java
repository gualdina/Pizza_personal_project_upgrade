package com.pizzashop.Pizza.service;

import com.pizzashop.Pizza.model.OrderDelivery;
import com.pizzashop.Pizza.model.Pizza;
import com.pizzashop.Pizza.repository.OrderDeliveryRepository;
import com.pizzashop.Pizza.repository.PizzaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service//Created for the detailed logic of the controller
public class OrderDeliveryService {

    //Connection to the repository and to the pizza service
    private final OrderDeliveryRepository orderDeliveryRepository;
    private final PizzaRepository pizzaRepository;

    public OrderDeliveryService(OrderDeliveryRepository orderDeliveryRepository, PizzaRepository pizzaRepository) {
        this.orderDeliveryRepository = orderDeliveryRepository;
        this.pizzaRepository = pizzaRepository;
    }
    public OrderDelivery getOrderDeliveryById(Long id){
        return orderDeliveryRepository.getById(id);
    }
    public void deleteOrderDelivery(Long id){
        orderDeliveryRepository.deleteById(id);
    }
    public OrderDelivery addOrder(OrderDelivery newOrder) {
        return orderDeliveryRepository.save(newOrder);
    }
    public OrderDelivery addPizzaToOrder(List<Pizza> pizzaList, Long id){
        OrderDelivery orderDelivery = this.getOrderDeliveryById(id);
        List<OrderDelivery> orderDeliveries = new ArrayList<>();
        orderDeliveries.add(orderDelivery);
        orderDelivery.setAddedPizzas(pizzaList);
        for (Pizza pizzas: pizzaList){
            pizzas.setCreatingOrder(orderDeliveries);
            pizzaRepository.save(pizzas);
        }
        return orderDelivery;
    }

}