package com.pizzashop.Pizza.Repositories;

import com.pizzashop.Pizza.Models.Toppings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository//to connect with DB
public interface ToppingsRepository extends JpaRepository<Toppings, Long>{
}