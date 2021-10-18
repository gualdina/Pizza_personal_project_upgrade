package com.pizzashop.Pizza.Controllers;

import com.pizzashop.Pizza.Controllers.Requests.CustomerRQ;
import com.pizzashop.Pizza.Models.Customer;
import com.pizzashop.Pizza.Services.CustomerService;
import com.pizzashop.Pizza.exceptions.CustomerNotFoundException;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value="/api")
@Validated
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {

        this.customerService = customerService;
    }
    @GetMapping("/getCustomer")
    public List<Customer> getCustomer(){
        return customerService.getCustomer();
    }
    @GetMapping("/gerCustomerId/{id}")
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
    @PutMapping("/updateCustomer/{id}")
        public Customer editCustomer(@PathVariable Long id,@RequestBody CustomerRQ customerRQ){
        Customer currentInfo = customerService.getCustomerById(id);
        BeanUtils.copyProperties(customerRQ, currentInfo);
        return customerService.editCustomer(id, currentInfo);
    }
    @DeleteMapping("/deleteCustomer/{id}")
    public void deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomerById(id);
    }
}
