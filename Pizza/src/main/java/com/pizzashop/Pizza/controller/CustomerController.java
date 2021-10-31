package com.pizzashop.Pizza.controller;

import com.pizzashop.Pizza.controller.request.CustomerRQ;
import com.pizzashop.Pizza.controller.response.CustomerResponse;
import com.pizzashop.Pizza.exceptions.CustomerNotFoundException;
import com.pizzashop.Pizza.model.Customer;
import com.pizzashop.Pizza.service.CustomerService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value="/api")
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
        public CustomerResponse editCustomer(@PathVariable(value = "id") Long id, @RequestBody CustomerRQ customerRQ){
        Customer customer = customerService.editCustomer(id,customerRQ);
        CustomerResponse customerResponse = new CustomerResponse();
                customerResponse.setId(customer.getId());
                customerResponse.setFirstName(customer.getFirstName());
                customerResponse.setLastName(customer.getLastName());
                customerResponse.setPhoneNumber(customer.getPhoneNumber());
                customerResponse.setCity(customer.getCity());
                customerResponse.setAddress(customer.getAddress());
        return customerResponse;
    }
    @DeleteMapping("/Customer/{id}")
    public void deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomerById(id);
    }
}
