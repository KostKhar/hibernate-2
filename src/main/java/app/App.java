package app;

import config.PropertiesSessionFactoryProvider;
import dao.CustomerDAO;
import dao.StoreDAO;
import entity.Customer;
import entity.Store;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {

        PropertiesSessionFactoryProvider provider = new PropertiesSessionFactoryProvider();
        SessionFactory sessionFactory = provider.getSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            StoreDAO storeDAO = new StoreDAO();
            Store store =storeDAO.findAll().get(0);

            Customer customer = new Customer();
            customer.setFirstName("John");
            customer.setLastName("Doe");
            customer.setActive(true);
            customer.setEmail("testemail@yandex.ru");
            customer.setStore(store);
            CustomerDAO customerDAO = new CustomerDAO();
            customerDAO.create(customer);
            session.getTransaction().commit();

        } catch (HibernateException e) {
            System.out.println("Ошибка работы Hibernate");
        }

    }
}
