package entity;

import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "payment", schema = "movie")
public class Rental {
    @Id
    @Column(name = "rental_id")
    private Integer rental_id;


    @Column(name = "rental_date", nullable = false)
    @Timestamp
    private Date rental_date;

    @OneToMany
    @JoinColumn(name = "inventory_id", nullable = false)
    private Set<Inventory> inventory;

    @OneToMany
    @JoinColumn(name = "customer_id", nullable = false)
    private Set<Customer> customer;


    @Column(name = "return_date", nullable = false)
    @Timestamp
    private Date return_date;

    @OneToMany
    @JoinColumn(name = "staff_id", nullable = false)
    private Set<Staff> staff;

    @Column(name = "last_update", nullable = false)
    @UpdateTimestamp
    private LocalDateTime lastUpdate;
}
