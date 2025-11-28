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

            String email = faker.internet().emailAddress();
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();

            CustomerService customerService = new CustomerService(customerDAO, storeDAO, addressDAO);
             customerService.createNewCustomer(firstName,
                   lastName, email, store, address);
            sessionFactory.getCurrentSession().getTransaction().commit();

            sessionFactory.getCurrentSession().beginTransaction();
            Customer customer = customerDAO.findCustomerByFullName(firstName, lastName);
            System.out.println(customer.toString());
            sessionFactory.getCurrentSession().getTransaction().commit();

        } catch (HibernateException e) {
            System.out.println("Hibernate Failed: " + e.getMessage());
        }

    }
}
