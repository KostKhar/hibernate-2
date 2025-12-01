package app;

import config.PropertiesSessionFactoryProvider;
import dao.*;
import entity.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
            Store store = storeDAO.getById(1L);

            InventoryDAO inventoryDAO = new InventoryDAO(sessionFactory);
            List<Film> filmsInInventory = inventoryDAO.findFilmsInInventory();

            Optional<Film> filmNotRental = films.stream()
                    .filter(film -> filmsInInventory != null && !filmsInInventory.contains(film))
                    .findAny();

            Film film = filmNotRental.orElseGet(() -> {
                List<Film> availableFilms = inventoryDAO.findFilmsInInventoryWithReturnDate();
                return availableFilms.isEmpty() ? null : availableFilms.get(0);
            });

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

            Payment payment = Payment.builder()
                    .customer(customer)
                    .paymentDate(now())
                    .rental(rental)
                    .amount(BigDecimal.valueOf(110.00))
                    .staff(staff)
                    .build();

            PaymentDAO paymentDAO = new PaymentDAO(sessionFactory);
            paymentDAO.create(payment);

            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println("Hibernate Failed: " + e.getMessage());
        }

    }
}
