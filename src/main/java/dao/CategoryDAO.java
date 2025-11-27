package dao;

import entity.Actor;
import entity.Category;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class CategoryDAO extends AbstractHibernateDao<Category>{
    public CategoryDAO(SessionFactory sessionFactory) {
        super(Category.class, sessionFactory);
    }

    public Category findAByName(String name) {
        Query<Category> query =  getCurrentSession()
                .createQuery("Select distinct name FROM Category where Category.name = :name",
                        Category.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }
}
