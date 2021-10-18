package com.pizzashop.Pizza.Services;

import com.pizzashop.Pizza.Controllers.Requests.ToppingsRQ;
import com.pizzashop.Pizza.Models.Toppings;
import com.pizzashop.Pizza.Repositories.ToppingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service//Created for the detailed logic of the controller
public class ToppingsService {
    @Autowired//Connection to the repository
   private final ToppingsRepository toppingsRepository;

    public ToppingsService(ToppingsRepository toppingsRepository) {

        this.toppingsRepository = toppingsRepository;
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
    public List<Toppings> getAllToppings(){
        return toppingsRepository.findAll();
    }
    public Toppings getToppingsById(Long id){
        return toppingsRepository.getById(id);
    }
    public Toppings deleteToppings(Long id){
        Toppings toppings = getToppingsById(id);
        toppingsRepository.delete(toppings);
        return toppings;
    }

}

