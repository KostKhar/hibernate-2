package service;

import dao.RentalDAO;
import entity.Customer;
import entity.Inventory;
import entity.Rental;
import entity.Staff;

import java.time.LocalDateTime;

public class RentalService {
    private RentalDAO rentalDAO;

    public RentalService(RentalDAO rentalDAO) {
        this.rentalDAO = rentalDAO;
    }

    public Rental buildRental(Customer customer, Inventory inventory, Staff staff) {
        Rental rental =   Rental.builder()
                .customer(customer)
                .inventory(inventory)
                .staff(staff)
                .rentalDate(LocalDateTime.of(2023, 3, 8, 14, 14))
                .build();
        rentalDAO.create(rental);
        return rental;
    }

}
