package app;

import config.PropertiesSessionFactoryProvider;
import dao.*;
import entity.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
            Inventory inventory = Inventory.builder()
                    .film(film)
                    .store(store)
                    .build();
            inventoryDAO.create(inventory);

            StaffDAO staffDAO = new StaffDAO(sessionFactory);
            Staff staff = staffDAO.getById(1L);

            RentalDAO rentalDAO = new RentalDAO(sessionFactory);
            Rental rental = Rental.builder()
                    .customer(customer)
                    .inventory(inventory)
                    .staff(staff)
                    .rentalDate(LocalDateTime.of(2023, 3, 8, 14, 14))
                    .build();
            rentalDAO.create(rental);

            rental.setReturnDate(now());
            rentalDAO.update(rental);

            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println("Hibernate Failed: " + e.getMessage());
        }

    }
}
