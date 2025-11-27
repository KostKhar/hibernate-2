package dao;

import entity.Film;
import entity.Inventory;
import org.hibernate.SessionFactory;

import java.util.List;

public class InventoryDAO extends AbstractHibernateDao<Inventory> {
    public InventoryDAO(SessionFactory sessionFactory) {
        super(Inventory.class, sessionFactory);
    }

    public List<Film> findFilmsInInventory() {
        return getCurrentSession()
                .createQuery("SELECT DISTINCT i.film FROM Inventory i", Film.class)
                .getResultList();
    }

    public List<Film> findFilmsInInventoryWithReturnDate() {
        return getCurrentSession()
                .createQuery("SELECT DISTINCT i.film FROM Inventory i where Rental.returnDate != null", Film.class)
                .getResultList();
    }
}
