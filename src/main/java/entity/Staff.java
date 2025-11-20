package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "film", schema = "movie")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id")
    private Long staff_id;

    @Column(name = "first_name", length = 45, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 45, nullable = false)
    private String lastName;

    @Column(name = "address_id", length = 50, nullable = false)
    private String address_id;

    @Column(name = "picture")
    private Byte[] picture;

    @Column(name = "email")
    private String email;

    @Column(name = "store_id")
    private Long store_id;

    @Column(name = "username", length = 16, nullable = false)
    private String username;

    @Column(name = "password", length = 40)
    private String password;

    @Column(name = "active", nullable = false, length = 1)
    private boolean active;

    @Column(name = "last_update", nullable = false)
    @UpdateTimestamp
    private String lastUpdate;

}
