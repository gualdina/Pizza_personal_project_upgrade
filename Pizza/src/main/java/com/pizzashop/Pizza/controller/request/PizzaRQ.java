package com.pizzashop.Pizza.Controllers.Requests;

import com.pizzashop.Pizza.Models.PizzaType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PizzaRQ {
    @Enumerated
    private PizzaType type;
    @Column(unique = true)
    private String name;
    private char size;
    @Min(value = 1)
    @Max(value = 9)
    private int quantity;
}
