package com.pizzashop.Pizza.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CustomerRQ {
    private Long id;
    private String firstName;
    private String lastName;
    private int phoneNumber;
    private String city;
    private String address;
}
