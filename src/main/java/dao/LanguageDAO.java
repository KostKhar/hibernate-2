package dao;

import entity.Language;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class LanguageDAO extends AbstractHibernateDao<Language> {
    public LanguageDAO(SessionFactory sessionFactory) {
        super(Language.class, sessionFactory);
    }

    public Language findByName(String name) {
        Query<Language> query = getCurrentSession()
                .createQuery("Select distinct name FROM Language where Language.name = :name",
                        Language.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }
}
