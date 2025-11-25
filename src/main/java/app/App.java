package app;

import config.PropertiesSessionFactoryProvider;
import entity.Customer;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class App {
    public static void main(String[] args) {

        PropertiesSessionFactoryProvider provider = new PropertiesSessionFactoryProvider();
        SessionFactory sessionFactory = provider.getSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Customer customer = new Customer();
            customer.setFirstName("John");
            customer.setLastName("Doe");
            customer.setActive(true);
            customer.
            session.save(customer);


        } catch (HibernateException e) {
            System.out.println("Ошибка работы Hibernate");
        }

    }
}
