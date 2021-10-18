package com.pizzashop.Pizza.Controllers;

import com.pizzashop.Pizza.Controllers.Requests.PizzaRQ;
import com.pizzashop.Pizza.Models.Pizza;
import com.pizzashop.Pizza.Services.PizzaService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
public class PizzaController {
    PizzaService pizzaService;

    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }
    @GetMapping("/getPizza")
    public List<Pizza> getPizzas(){
        return pizzaService.getPizzas();
    }
    @GetMapping("/getPizzaById")
    public Pizza getPizzaById(@PathVariable Long id){
        return pizzaService.getPizzaById(id);
    }
    @PostMapping(value = "/createPizza", consumes = "application/json")
    public Pizza addPizza(@RequestBody @Valid PizzaRQ pizzaRQ){
        return pizzaService.addPizza(pizzaRQ);
    }
    @PostMapping(value = "/addingToppingsPizzas", consumes = "application/json")
    public Pizza addToppingsToPizza(@RequestBody @Valid Long pizzaId, Long toppingsId){
        return pizzaService.addToppingsToPizza(pizzaId, toppingsId);
    }
    @PutMapping("updatePizza/{id}")
    public void updatePizza(@PathVariable Long id,@RequestBody @Valid PizzaRQ pizzaToUpdate){
        pizzaService.updatePizza(id, pizzaToUpdate);
    }
    @DeleteMapping("/deleteToppingsPizz/{id}")
    public void removeToppingsFromPizza(@PathVariable Long pizzaId, Long toppingsId){
        pizzaService.removeToppingsFromPizza(pizzaId, toppingsId);
    }
    @DeleteMapping("/deletePizza/{id}")
    public void deletePizza(@PathVariable Long id){
        pizzaService.deletePizza(id);
    }
}
