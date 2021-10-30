package com.pizzashop.Pizza.controller;

import com.pizzashop.Pizza.controller.request.ToppingsRQ;
import com.pizzashop.Pizza.model.Toppings;
import com.pizzashop.Pizza.service.ToppingsService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
public class ToppingsController {
    ToppingsService toppingsService;

    public ToppingsController(ToppingsService toppingsService) {
        this.toppingsService = toppingsService;
    }
    //Get all toppings
    @GetMapping("/toppings")
    public List<Toppings> getAllToppings(){
        return toppingsService.getAllToppings();
    }
    //Get toppings by id
    @GetMapping("/getToppings/{id}")
    public Toppings getToppingsById(@PathVariable Long id){
        return toppingsService.getToppingsById(id);
    }
    //save toppings
    @PostMapping(value = "/savingToppings", consumes = "application/json")
    public Toppings addToppings(@RequestBody @Valid ToppingsRQ toppingsRQ){
        return toppingsService.addToppings(toppingsRQ);
    }
    @DeleteMapping("/deletingToppings/{id}")
    public void deleteToppings(@PathVariable Long id){
        toppingsService.deleteToppings(id);
    }
}
