package com.pizzashop.Pizza.controller.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {
    private long Id;
    private String firstName;
    private String lastName;
    private int phoneNumber;
    private String city;
    private String address;
    private OrderDeliveryResponse orderDeliveryResponse;
}
