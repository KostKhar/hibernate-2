package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "customer", schema = "test")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private int customer_id;

    @Column(name = "store_id", nullable = false)
    private int store_id;

    @Column(name = "first_name", length = 45, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 45, nullable = false)
    private String lastName;

    @Column(name = "email", length = 50, nullable = false)
    private String email;

    @Column(name = "address_id", nullable = false)
    private Integer address_id;

    @Column(name = "active", nullable = false, length = 1)
    private boolean active;

    @Column(name = "create_date", nullable = false)
    @CreationTimestamp
    private Date create_date;

    @Column(name = "last_update", nullable = false)
    @UpdateTimestamp
    private Date lastUpdate;

}
