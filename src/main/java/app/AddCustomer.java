package app;

import config.PropertiesSessionFactoryProvider;
import dao.AddressDAO;
import dao.CustomerDAO;
import dao.StoreDAO;
import entity.Address;
import entity.Customer;
import entity.Store;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class AddCustomer {
    public static void main(String[] args) {

        PropertiesSessionFactoryProvider provider = new PropertiesSessionFactoryProvider();


        try (SessionFactory sessionFactory = provider.getSessionFactory();
                Session session = sessionFactory.getCurrentSession()) {

            session.beginTransaction();

            StoreDAO storeDAO = new StoreDAO(session.getSessionFactory());
            Store store =storeDAO.findAll().get(0);

            AddressDAO addressDAO = new AddressDAO(session.getSessionFactory());
            Address address = addressDAO.findAll().get(0);


            Customer customer = new Customer();
            customer.setFirstName("John1");
            customer.setLastName("Doe1");
            customer.setActive(true);
            customer.setEmail("testemai1l@yandex.ru");
            customer.setStore(store);
            customer.setAddress(address);

            CustomerDAO customerDAO = new CustomerDAO(session.getSessionFactory());
            customerDAO.create(customer);

            session.getTransaction().commit();

        } catch (HibernateException e) {
            System.out.println("Hibernate Failed: " + e.getMessage());
        }

    }
}
