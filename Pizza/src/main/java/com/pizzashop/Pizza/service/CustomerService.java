package com.pizzashop.Pizza.service;

import com.pizzashop.Pizza.controller.request.CustomerRQ;
import com.pizzashop.Pizza.exceptions.CustomerNotFoundException;
import com.pizzashop.Pizza.model.Customer;
import com.pizzashop.Pizza.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service//Created for the detailed logic of the controller
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    public List<Customer> getCustomer(){
        return customerRepository.findAll();
    }
    public Customer addCustomer(CustomerRQ customerRQ){
         String firstName= customerRQ.getFirstName();
         String lastName= customerRQ.getLastName();
         int phoneNumber= customerRQ.getPhoneNumber();
         String city= customerRQ.getCity();
         String address = customerRQ.getAddress();
         Customer customer = Customer
                 .builder()
                 .firstName(firstName)
                 .lastName(lastName)
                 .phoneNumber(phoneNumber)
                 .city(city)
                 .address(address)
                 .build();
        return customerRepository.save(customer);
    }
    public Customer getCustomerById(Long id){
        return customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(
                "Customer not found"));
    }

    public Customer deleteCustomerById(Long id){
        Customer customer = getCustomerById(id);
        customerRepository.delete(customer);
        return customer;
    }
    public Customer editCustomer(Long id, Customer customer){
        Customer customerToEdit = getCustomerById(id);
        customerToEdit.setFirstName(customer.getFirstName());
        customerToEdit.setLastName(customer.getLastName());
        customerToEdit.setPhoneNumber(customer.getPhoneNumber());
        customerToEdit.setCity(customer.getCity());
        customerToEdit.setAddress(customer.getAddress());
        return customerToEdit;

    }

}


