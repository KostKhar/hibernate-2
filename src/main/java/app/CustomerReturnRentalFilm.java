package app;

import config.PropertiesSessionFactoryProvider;
import dao.*;
import entity.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import service.InventoryService;
import service.RentalService;

import java.time.LocalDateTime;
import java.util.List;

import static java.time.LocalDateTime.now;

public class CustomerReturnRentalFilm {

    public static void main(String[] args) {
        PropertiesSessionFactoryProvider provider = new PropertiesSessionFactoryProvider();


        try (SessionFactory sessionFactory = provider.getSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {

            session.beginTransaction();

            CustomerDAO customerDAO = new CustomerDAO(sessionFactory);
            Customer customer = customerDAO.findAll().get(0);

            FilmDAO filmDAO = new FilmDAO(sessionFactory);
            List<Film> films = filmDAO.findAll();
            Film film = films.get(films.size() - 1);

            StoreDAO storeDAO = new StoreDAO(sessionFactory);
            Store store = storeDAO.findAll().get(0);

            InventoryDAO inventoryDAO = new InventoryDAO(sessionFactory);
            Inventory inventory = new InventoryService(inventoryDAO).createInventory( film, store);

            StaffDAO staffDAO = new StaffDAO(sessionFactory);
            Staff staff = staffDAO.getById(1);

            RentalDAO rentalDAO = new RentalDAO(sessionFactory);
            Rental rental =  new RentalService(rentalDAO).buildRental(customer, inventory,  staff);

            rental.setReturnDate(now());
            Rental rentalToUpdate = rentalDAO.update(rental);

            Rental rentalAfterUodate = rentalDAO.getById(rentalToUpdate.getRental_id());
            System.out.printf(rentalAfterUodate.toString());

            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println("Hibernate Failed: " + e.getMessage());
        }

    }
}
