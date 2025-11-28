package dao;

import entity.Actor;
import entity.Customer;
import org.hibernate.SessionFactory;

public class CustomerDAO extends AbstractHibernateDao<Customer> {
    public CustomerDAO(SessionFactory sessionFactory) {
        super(Customer.class, sessionFactory);
    }

    public Customer findCustomerByFullName(String firstName, String lastName) {
        return getCurrentSession()
                .createQuery("FROM Customer where firstName = :firstname and lastName = :lastname", Customer.class)
                .setParameter("firstname", firstName)
                .setParameter("lastname", lastName)
                .uniqueResult();
    }


}
