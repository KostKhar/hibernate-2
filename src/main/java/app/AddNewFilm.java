package app;

import config.PropertiesSessionFactoryProvider;
import dao.*;
import entity.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.time.LocalDateTime.now;

public class AddNewFilm {

    public static void main(String[] args) {
        PropertiesSessionFactoryProvider provider = new PropertiesSessionFactoryProvider();


        try (SessionFactory sessionFactory = provider.getSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {
             session.beginTransaction();

             FilmDAO filmDAO = new FilmDAO(sessionFactory);
             Set<Actor> actors = new HashSet<Actor>();
            actors.add(Actor.builder()
                    .firstName("John").lastName("Travolta").build());
            actors.add(Actor.builder()
                    .firstName("Nicolas").lastName("Cage").build());

            Set<String> categories = Set.of("Action", "Thriller");


             Film film = filmDAO.createNewFilm("Без лица", "На карусели.... ",
                     "1997", "English",
                     "Russian", 2, 6.9,
                     2, "G",
                     Set.of(SpecialFeature.valueOf("Trailers"),SpecialFeature.valueOf("Commentaries")),
                     actors, categories);

            System.out.println(film.toString());

            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println("Hibernate Failed: " + e.getMessage());
        }

    }
}
