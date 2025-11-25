package dao;

import entity.Customer;

public class CustomerDAO extends AbstractHibernateDao<Customer> {
    public CustomerDAO() {
        super(Customer.class);
    }
}
