package com.pizzashop.Pizza.controller;

import com.pizzashop.Pizza.controller.request.PizzaRQ;
import com.pizzashop.Pizza.controller.request.ToppingsRQ;
import com.pizzashop.Pizza.controller.response.PizzaResponse;
import com.pizzashop.Pizza.controller.response.ToppingsResponse;
import com.pizzashop.Pizza.model.Pizza;
import com.pizzashop.Pizza.model.Toppings;
import com.pizzashop.Pizza.service.PizzaService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
public class PizzaController {
    private final PizzaService pizzaService;

    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }
    //Get all pizzas
    @GetMapping("/Pizzas")
    public List<Pizza> getPizzas(){
        return pizzaService.getPizzas();
    }
    //get pizza by id
    @GetMapping("/Pizza/{Id}")
    public PizzaResponse getPizzaById(@PathVariable(value = "id") Long id){
        Pizza pizza = pizzaService.getPizzaById(id);
        List<ToppingsResponse> toppingsResp = new ArrayList<>();
        PizzaResponse pizzaResponse = new PizzaResponse(pizza.getId(),
                pizza.getType(),
                pizza.getName(),
                pizza.getSize(),
                pizza.getQuantity(), toppingsResp);
        for (Toppings toppings : pizza.getCompletingTopping()) {
            ToppingsResponse toppingResp = new ToppingsResponse(
                    toppings.getId(),
                    toppings.getSauce(),
                    toppings.getToppingName());
            pizzaResponse.getToppingsResponses().add(toppingResp);
        }
        return pizzaResponse;
    }
    @PostMapping(value = "/create-Pizza", consumes = "application/json")
    public PizzaResponse addPizza(@RequestBody @Valid PizzaRQ pizzaRQ){
        Pizza newPizza = Pizza
                .builder()
                .name(pizzaRQ.getName())
                .type(pizzaRQ.getType())
                .size(pizzaRQ.getSize())
                .quantity(pizzaRQ.getQuantity())
                .build();
        pizzaService.addPizza(newPizza);
        PizzaResponse pizzaResponse = new PizzaResponse();
        pizzaResponse.setId(newPizza.getId());
        pizzaResponse.setName(newPizza.getName());
        pizzaResponse.setType(newPizza.getType());
        pizzaResponse.setSize(newPizza.getSize());
        pizzaResponse.setQuantity(newPizza.getQuantity());
        return pizzaResponse;
    }
    //add toppings to pizza
    @PostMapping(value = "/Adding-Toppings-Pizzas/{id}")
    public PizzaResponse addToppingsToPizza(@RequestBody List<ToppingsRQ> toppingsRQS, Long id){
        List<Toppings> toppings = new ArrayList<>();
        for (ToppingsRQ toppingsRQ : toppingsRQS) {
            toppings.add(Toppings
                    .builder()
                    .sauce(toppingsRQ.getSauce())
                    .toppingName(toppingsRQ.getToppingName())
                    .build());
        }
        Pizza pizza = pizzaService.addToppingsToPizza(toppings,id);
        List<ToppingsResponse> toppingsResponses = new ArrayList<>();
        PizzaResponse pizzaResponse = new PizzaResponse(
                pizza.getId(),
                pizza.getType(),
                pizza.getName(),
                pizza.getSize(),
                pizza.getQuantity(), toppingsResponses);
        for (Toppings topping : pizza.getCompletingTopping()) {
            ToppingsResponse toppingResp = new ToppingsResponse(
                    topping.getId(),
                    topping.getSauce(),
                    topping.getToppingName());
            pizzaResponse.getToppingsResponses().add(toppingResp);
        }
        return pizzaResponse;

    }
    @PutMapping(value = "/Pizza/{id}")
    public PizzaResponse updatePizza(@PathVariable(value = "id") Long id, @RequestBody PizzaRQ pizzaRQ){
        Pizza pizza = pizzaService.updatePizza(id, pizzaRQ);
        PizzaResponse pizzaResponse = new PizzaResponse();
        pizzaResponse.setId(pizza.getId());
        pizzaResponse.setName(pizza.getName());
        pizzaResponse.setType(pizza.getType());
        pizzaResponse.setSize(pizza.getSize());
        pizzaResponse.setQuantity(pizza.getQuantity());
        return pizzaResponse;
    }
    @DeleteMapping("/Pizza/{id}")
    public void deletePizza(@PathVariable Long id){
        pizzaService.deletePizza(id);
    }
}
