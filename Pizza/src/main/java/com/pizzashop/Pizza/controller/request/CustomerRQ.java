package com.pizzashop.Pizza.Controllers.Requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CustomerRQ {
    private String firstName;
    private String lastName;
    private int phoneNumber;
    private String city;
    private String address;
}
