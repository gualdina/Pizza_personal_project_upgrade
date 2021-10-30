package com.pizzashop.Pizza.controller;

import com.pizzashop.Pizza.controller.request.CustomerRQ;
import com.pizzashop.Pizza.controller.response.CustomerResponse;
import com.pizzashop.Pizza.exceptions.CustomerNotFoundException;
import com.pizzashop.Pizza.model.Customer;
import com.pizzashop.Pizza.service.CustomerService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value="/api")
@Validated
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {

        this.customerService = customerService;
    }
    @GetMapping("/Customer")
    public List<Customer> getCustomer(){
        return customerService.getCustomer();
    }
    @GetMapping("/Customer/{id}")
    public Customer getCustomerById(@PathVariable Long id){
        return customerService.getCustomerById(id);
    }
    @PostMapping(value = "customer", consumes = "application/json")
    public Customer addCustomer(@RequestBody CustomerRQ customerRQ){
        if (customerRQ.getCity().equalsIgnoreCase("Madrid")){
            throw new CustomerNotFoundException("No Pizza Shop available in your City");
        }
        return customerService.addCustomer(customerRQ);
    }
    @PutMapping("/Customer/{id}")
        public ResponseEntity<CustomerResponse> editCustomer(@PathVariable Long id, @RequestBody CustomerRQ customerRQ){
        final Customer newInfo = customerService.editCustomer(id,customerRQ.getId());
        final var responseBody = new CustomerResponse(newInfo.getId(), newInfo.getFirstName(), newInfo.getLastName(), newInfo.getPhoneNumber(), newInfo.getCity(), newInfo.getAddress());
        return ResponseEntity.ok(responseBody);
    }
    @DeleteMapping("/Customer/{id}")
    public void deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomerById(id);
    }
}
