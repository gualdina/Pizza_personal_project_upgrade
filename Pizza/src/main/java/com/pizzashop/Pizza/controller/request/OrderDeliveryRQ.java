package com.pizzashop.Pizza.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OrderDeliveryRQ {
    @NotNull(message = "Name required" )
    private String name;
}
