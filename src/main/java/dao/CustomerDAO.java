package dao;

import entity.Customer;

import java.util.Set;

public class CustomerDAO extends AbstractHibernateDao<Customer> {
    public CustomerDAO() {
        super(Customer.class);
    }

}
