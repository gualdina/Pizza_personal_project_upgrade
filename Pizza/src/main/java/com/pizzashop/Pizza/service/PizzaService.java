package com.pizzashop.Pizza.service;

import com.pizzashop.Pizza.controller.request.PizzaRQ;
import com.pizzashop.Pizza.model.Pizza;
import com.pizzashop.Pizza.model.PizzaType;
import com.pizzashop.Pizza.model.Toppings;
import com.pizzashop.Pizza.repository.PizzaRepository;
import com.pizzashop.Pizza.repository.ToppingsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service//Created for the detailed logic of the controller
public class PizzaService {
    //Connection to the repository and to the order deliver service
    private final PizzaRepository pizzaRepository;
    private final ToppingsRepository toppingsRepository;

    public PizzaService(PizzaRepository pizzaRepository, ToppingsRepository toppingsRepository) {
        this.pizzaRepository = pizzaRepository;
        this.toppingsRepository = toppingsRepository;
    }
    public Pizza addPizza(Pizza newPizza) {
        return pizzaRepository.save(newPizza);
    }
    public List<Pizza> getPizzas(){
        return pizzaRepository.findAll();
    }
    public Pizza getPizzaById(Long id){
        return pizzaRepository.getById(id);
    }
    public void deletePizza(Long id){ pizzaRepository.deleteById(id);}

    public Pizza updatePizza(Long id, PizzaRQ pizzaRQ){//do not use requestes in the service bc shoudl not change according to the cst request
        Pizza pizzaToEdit = this.getPizzaById(id);
        pizzaToEdit.setType(pizzaRQ.getType());
        pizzaToEdit.setName(pizzaRQ.getName());
        pizzaToEdit.setSize(pizzaRQ.getSize());
        pizzaToEdit.setQuantity(pizzaRQ.getQuantity());
        return pizzaToEdit;
    }
    public Pizza addToppingsToPizza(List<Toppings> toppings, Long id){
        Pizza pizza = this.getPizzaById(id);
        List<Pizza> pizzas = new ArrayList<>();
        pizzas.add(pizza);
        pizza.setCompletingTopping(toppings);
        for (Toppings toppings1: toppings){
            toppings1.setCompletePizza(pizzas);
            toppingsRepository.save(toppings1);
        }
        return pizza;
    }
}
