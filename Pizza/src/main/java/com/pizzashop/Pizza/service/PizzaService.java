package com.pizzashop.Pizza.Services;

import com.pizzashop.Pizza.Controllers.Requests.PizzaRQ;
import com.pizzashop.Pizza.Models.Pizza;
import com.pizzashop.Pizza.Models.PizzaType;
import com.pizzashop.Pizza.Models.Toppings;
import com.pizzashop.Pizza.Repositories.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service//Created for the detailed logic of the controller
public class PizzaService {
    //Connection to the repository and to the order deliver service
    private final PizzaRepository pizzaRepository;
    private final ToppingsService toppingsService;

    public PizzaService(PizzaRepository pizzaRepository, ToppingsService toppingsService) {
        this.pizzaRepository = pizzaRepository;
        this.toppingsService = toppingsService;
    }
    public Pizza addPizza(PizzaRQ pizzaRQ){
        String name = pizzaRQ.getName();
        PizzaType type = pizzaRQ.getType();
        char size = pizzaRQ.getSize();
        int quantity = pizzaRQ.getQuantity();
        Pizza pizza = Pizza
                .builder()
                .name(name)
                .type(type)
                .size(size)
                .quantity(quantity)
                .build();
        return pizzaRepository.save(pizza);
    }
    public List<Pizza> getPizzas(){
        return pizzaRepository.findAll();
    }
    public Pizza getPizzaById(Long id){
        return pizzaRepository.getById(id);
    }
    public Pizza deletePizza(Long id){
        Pizza pizza= getPizzaById(id);
        pizzaRepository.delete(pizza);
        return pizza;
    }
    public Pizza updatePizza(Long id, PizzaRQ pizzaRQ){
        Pizza pizzaToEdit = getPizzaById(id);
        pizzaToEdit.setType(pizzaRQ.getType());
        pizzaToEdit.setName(pizzaRQ.getName());
        pizzaToEdit.setSize(pizzaRQ.getSize());
        pizzaToEdit.setQuantity(pizzaRQ.getQuantity());
        return pizzaToEdit;
    }
    public Pizza addToppingsToPizza(Long pizzaId, Long toppingsId){
        Pizza pizza = getPizzaById(pizzaId);
        Toppings toppings = toppingsService.getToppingsById(toppingsId);
        pizza.addingToppings(toppings);
        return pizza;
    }
    public Pizza removeToppingsFromPizza(Long pizzaId, Long toppingsId){
        Pizza pizza = getPizzaById(pizzaId);
        Toppings toppings = toppingsService.getToppingsById(toppingsId);
        pizza.removingToppings(toppings);
        return pizza;
    }

}
