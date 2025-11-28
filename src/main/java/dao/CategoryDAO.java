package dao;

import entity.Category;
import org.hibernate.SessionFactory;

public class CategoryDAO extends AbstractHibernateDao<Category> {
    public CategoryDAO(SessionFactory sessionFactory) {
        super(Category.class, sessionFactory);
    }

    public Category findByName(String name) {
        return getCurrentSession()
                .createQuery("FROM Category where name = :name",
                        Category.class)
                .setParameter("name", name).uniqueResult();
    }
}
