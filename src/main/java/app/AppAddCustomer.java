package app;

import com.github.javafaker.Faker;
import config.PropertiesSessionFactoryProvider;
import dao.AddressDAO;
import dao.CustomerDAO;
import dao.StoreDAO;
import entity.Address;
import entity.Customer;
import entity.Store;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import service.CustomerService;


public class AppAddCustomer {
    public static void main(String[] args) {

        PropertiesSessionFactoryProvider provider = new PropertiesSessionFactoryProvider();
        Faker faker = new Faker();

        try (SessionFactory sessionFactory = provider.getSessionFactory()) {

            sessionFactory.getCurrentSession().beginTransaction();
            AddressDAO addressDAO = new AddressDAO(sessionFactory);
            StoreDAO storeDAO = new StoreDAO(sessionFactory);
            CustomerDAO customerDAO = new CustomerDAO(sessionFactory);

            Address address = addressDAO.findAll().get(0);
            Store store = storeDAO.findAll().get(0);

            CustomerService customerService = new CustomerService(customerDAO, storeDAO, addressDAO);
            Customer customer = customerService.createNewCustomer(faker.name().firstName(),
                    faker.name().lastName(), faker.internet().emailAddress(), store, address);
            sessionFactory.getCurrentSession().getTransaction().commit();

            System.out.println(customer.toString());
        } catch (HibernateException e) {
            System.out.println("Hibernate Failed: " + e.getMessage());
        }

    }
}
