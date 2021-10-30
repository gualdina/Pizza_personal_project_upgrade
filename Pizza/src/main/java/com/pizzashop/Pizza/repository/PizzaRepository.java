package com.pizzashop.Pizza.repository;

import com.pizzashop.Pizza.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository//to connect with DB
public interface PizzaRepository extends JpaRepository<Pizza, Long> {

}
