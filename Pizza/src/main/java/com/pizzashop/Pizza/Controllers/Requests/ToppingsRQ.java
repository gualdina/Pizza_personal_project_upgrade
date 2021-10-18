package com.pizzashop.Pizza.Controllers.Requests;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ToppingsRQ {
    private String sauce;
    public String toppingName;
}
