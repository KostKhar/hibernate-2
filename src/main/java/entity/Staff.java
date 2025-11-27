package entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "staff", schema = "movie")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id")
    private Long staff_id;

    @Column(name = "first_name", length = 45, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 45, nullable = false)
    private String lastName;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "picture")
    private Byte[] picture;

    @Column(name = "email")
    private String email;

    @OneToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(name = "username", length = 16, nullable = false)
    private String username;

    @Column(name = "password", length = 40)
    private String password;

    @Column(name = "active", nullable = false, length = 1)
    private boolean active;

    @Column(name = "last_update", nullable = false)
    @UpdateTimestamp
    private LocalDateTime lastUpdate;

    public Staff(String lastName, String firstName, Address address,
                 Byte[] picture, String email, Store store,
                 String username, String password, boolean active) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.address = address;
        this.picture = picture;
        this.email = email;
        this.store = store;
        this.username = username;
        this.password = password;
        this.active = active;
    }
}
