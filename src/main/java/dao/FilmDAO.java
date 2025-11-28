package dao;

import entity.Film;
import org.hibernate.SessionFactory;

public class FilmDAO extends AbstractHibernateDao<Film> {
    private final SessionFactory sessionFactory;
    private ActorDAO actorDAO;
    private CategoryDAO categoryDAO;
    private LanguageDAO languageDAO;

    public FilmDAO(SessionFactory sessionFactory) {
        super(Film.class, sessionFactory);
        this.sessionFactory = sessionFactory;
    }
}
