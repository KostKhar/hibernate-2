package dao;

import entity.Inventory;
import org.hibernate.SessionFactory;

public class InventoryDAO extends AbstractHibernateDao<Inventory> {
    public InventoryDAO(SessionFactory sessionFactory) {
        super(Inventory.class, sessionFactory);
    }
}
