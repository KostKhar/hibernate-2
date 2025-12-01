package service;

import com.github.javafaker.Faker;
import dao.*;
import entity.Address;
import entity.Customer;
import entity.Store;

public class CustomerService {
    private final CustomerDAO customerDAO;
    private final StoreDAO storeDAO;
    private final AddressDAO addressDAO;

    public CustomerService(CustomerDAO customerDAO, StoreDAO storeDAO,
                           AddressDAO addressDAO) {
        this.customerDAO = customerDAO;
        this.storeDAO = storeDAO;
        this.addressDAO = addressDAO;
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
