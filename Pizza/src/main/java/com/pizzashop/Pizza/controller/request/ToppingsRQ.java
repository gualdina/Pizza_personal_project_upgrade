package com.pizzashop.Pizza.controller.request;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ToppingsRQ {
    private String sauce;
    public String toppingName;
}
