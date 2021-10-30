package com.pizzashop.Pizza.controller.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ToppingsResponse {
    private long Id;
    private String sauce;
    public String toppingName;
}
