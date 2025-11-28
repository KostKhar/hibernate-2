package app;

import config.PropertiesSessionFactoryProvider;
import dao.ActorDAO;
import dao.CategoryDAO;
import dao.FilmDAO;
import dao.LanguageDAO;
import entity.Actor;
import entity.Film;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import service.FilmService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static entity.SpecialFeature.DELETED_SCENES;
import static entity.SpecialFeature.TRAILERS;

public class AddNewFilm {

    public static void main(String[] args) {
        PropertiesSessionFactoryProvider provider = new PropertiesSessionFactoryProvider();


        try (SessionFactory sessionFactory = provider.getSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            FilmDAO filmDAO = new FilmDAO(sessionFactory);
            ActorDAO actorDAO = new ActorDAO(sessionFactory);
            CategoryDAO categoryDAO = new CategoryDAO(sessionFactory);
            LanguageDAO languageDAO = new LanguageDAO(sessionFactory);

            FilmService filmService = new FilmService(filmDAO, actorDAO, categoryDAO, languageDAO);

            Set<Actor> actors = new HashSet<Actor>();
            actors.add(Actor.builder()
                    .firstName("John").lastName("Travolta").build());
            actors.add(Actor.builder()
                    .firstName("Nicolas").lastName("Cage").build());

            Set<String> categories = Set.of("Action", "Thriller");


            Film film = filmService.createNewFilm("Без лица", "На карусели.... ",
                    LocalDate.of(1997, 11, 3), "English",
                    "Russian", 2, BigDecimal.valueOf(6.97),
                    2, "G",
                    Set.of(TRAILERS, DELETED_SCENES),
                    actors, categories);

            System.out.println(film.toString());

            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println("Hibernate Failed: " + e.getMessage());
        }

    }
}
