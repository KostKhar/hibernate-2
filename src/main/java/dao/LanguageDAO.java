package dao;

import entity.Language;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class LanguageDAO extends AbstractHibernateDao<Language> {
    public LanguageDAO(SessionFactory sessionFactory) {
        super(Language.class, sessionFactory);
    }

    public Language findByName(String name) {
        return getCurrentSession()
                .createQuery("FROM Language where name = :name",
                        Language.class)
        .setParameter("name", name).uniqueResult();
    }
}
