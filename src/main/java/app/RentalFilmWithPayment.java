package app;

import config.PropertiesSessionFactoryProvider;
import dao.*;
import entity.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import service.InventoryService;
import service.RentalService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.time.LocalDateTime.now;

public class RentalFilmWithPayment {
    public static void main(String[] args) {
        PropertiesSessionFactoryProvider provider = new PropertiesSessionFactoryProvider();


        try (SessionFactory sessionFactory = provider.getSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {

            session.beginTransaction();

            CustomerDAO customerDAO = new CustomerDAO(sessionFactory);
            List<Customer> customers = customerDAO.findAll();
            Customer customer = customers.get(customers.size() - 1);

            FilmDAO filmDAO = new FilmDAO(sessionFactory);
            List<Film> films = filmDAO.findAll();

            StoreDAO storeDAO = new StoreDAO(sessionFactory);
            Store store = storeDAO.getById(1);

            InventoryDAO inventoryDAO = new InventoryDAO(sessionFactory);
            List<Film> filmsInInventory = inventoryDAO.findFilmsInInventory();

            Optional<Film> filmNotRental = films.stream()
                    .filter(film -> filmsInInventory != null && !filmsInInventory.contains(film))
                    .findAny();

            Film film = filmNotRental.orElseGet(() -> {
                List<Film> availableFilms = inventoryDAO.findFilmsInInventoryWithReturnDate();
                return availableFilms.isEmpty() ? null : availableFilms.get(0);
            });

            Inventory inventory = new InventoryService(inventoryDAO).createInventory( film, store);

            StaffDAO staffDAO = new StaffDAO(sessionFactory);
            Staff staff = staffDAO.getById(1);

            RentalDAO rentalDAO = new RentalDAO(sessionFactory);
            Rental rental = new RentalService(rentalDAO).buildRental(customer, inventory,  staff);

            Payment payment = Payment.builder()
                    .customer(customer)
                    .paymentDate(now())
                    .rental(rental)
                    .amount(BigDecimal.valueOf(110.00))
                    .staff(staff)
                    .build();

            PaymentDAO paymentDAO = new PaymentDAO(sessionFactory);
            Payment payToCreate = paymentDAO.create(payment);

            Payment payAfterCreate = paymentDAO.getById(payToCreate.getPayment_id());
            System.out.println(payAfterCreate.toString());

            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println("Hibernate Failed: " + e.getMessage());
        }

    }
}
