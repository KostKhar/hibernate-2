package app;

import config.PropertiesSessionFactoryProvider;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class CustomerReturnRentalFilm {

    public static void main(String[] args) {
        PropertiesSessionFactoryProvider provider = new PropertiesSessionFactoryProvider();


        try (SessionFactory sessionFactory = provider.getSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {

            session.beginTransaction();


            session.getTransaction().commit();

        } catch (HibernateException e) {
            System.out.println("Hibernate Failed: " + e.getMessage());
        }

    }
}
