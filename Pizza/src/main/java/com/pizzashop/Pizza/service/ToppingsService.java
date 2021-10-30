package com.pizzashop.Pizza.service;

import com.pizzashop.Pizza.controller.request.ToppingsRQ;
import com.pizzashop.Pizza.model.Toppings;
import com.pizzashop.Pizza.repository.ToppingsRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service//Created for the detailed logic of the controller
public class ToppingsService {
   //Connection to the repository
   private final ToppingsRepository toppingsRepository;

    public ToppingsService(ToppingsRepository toppingsRepository) {

        this.toppingsRepository = toppingsRepository;
    }
    public List<Toppings> getAllToppings(){
        return toppingsRepository.findAll();
    }
    public Toppings getToppingsById(Long id){
        return toppingsRepository.getById(id);
    }
    public Toppings addToppings(ToppingsRQ toppingsRQ){
        String toppingName = toppingsRQ.getToppingName();
        String sauce = toppingsRQ.getSauce();
        Toppings toppings = Toppings
                .builder()
                .toppingName(toppingName)
                .sauce(sauce)
                .build();
        return toppingsRepository.save(toppings);
    }
    public Toppings deleteToppings(Long id){
        Toppings toppings = getToppingsById(id);
        toppingsRepository.delete(toppings);
        return toppings;
    }
}

