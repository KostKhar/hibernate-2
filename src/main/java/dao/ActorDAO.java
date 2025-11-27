package dao;

import entity.Actor;
import entity.Film;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ActorDAO extends AbstractHibernateDao<Actor> {
    public ActorDAO(SessionFactory sessionFactory) {
        super(Actor.class, sessionFactory);
    }

    public List<Actor> findActorByName(String lastName) {
        Query<Actor> query =  getCurrentSession()
                .createQuery("Select distinct lastName FROM Actor where Actor.lastName = :name", Actor.class);
        query.setParameter("name", lastName);
       return query.getResultList();
    }
}
