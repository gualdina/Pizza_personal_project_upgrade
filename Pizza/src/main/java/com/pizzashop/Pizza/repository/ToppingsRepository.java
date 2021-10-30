package com.pizzashop.Pizza.repository;

import com.pizzashop.Pizza.model.Toppings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository//to connect with DB
public interface ToppingsRepository extends JpaRepository<Toppings, Long>{
}
