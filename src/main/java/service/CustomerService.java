package service;

import com.github.javafaker.Faker;
import dao.*;
import entity.Address;
import entity.Customer;
import entity.Store;

public class CustomerService {
    private final CustomerDAO customerDAO;

    public CustomerService(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public Customer createNewCustomer(String firstName, String lastName,  String email,
                                      Store store, Address address) {

        Customer customer = Customer.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .active(true)
                .store(store)
                .address(address)
                .build();

        customerDAO.create(customer);
        return customer;
    }

}
