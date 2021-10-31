package com.pizzashop.Pizza.controller.response;

import lombok.*;

import java.util.List;

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
    private List<OrderDeliveryResponse> orderDeliveryResponse;
}
