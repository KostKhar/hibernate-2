package dao;

import entity.Actor;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class ActorDAO extends AbstractHibernateDao<Actor> {
    public ActorDAO(SessionFactory sessionFactory) {
        super(Actor.class, sessionFactory);
    }

    public Actor findActorByFullName(String firstName, String lastName) {
        return getCurrentSession()
                .createQuery("FROM Actor where firstName = :firstname and lastName = :lastname", Actor.class)
        .setParameter("firstname", firstName)
        .setParameter("lastname", lastName)
                .uniqueResult();
    }
}
