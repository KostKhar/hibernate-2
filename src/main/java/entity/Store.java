package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.UpdateTimestamp;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "store", schema = "movie")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long store_id;

    @Column(name = "manager_staff_id", nullable = false)
    private Byte manager_staff_id;

    @Column(name = "address_id", length = 50, nullable = false)
    private String address_id;

    @Column(name = "last_update", nullable = false)
    @UpdateTimestamp
    private String lastUpdate;
}
