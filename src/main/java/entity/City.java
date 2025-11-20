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
@Table(name = "city", schema = "test")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private Long city_id;

    @Column(name = "country_id", length = 50, nullable = false)
    private Long country_id;

    @Column(name = "city", length = 50, nullable = false)
    private String city;

    @Column(name = "last_update", nullable = false)
    @UpdateTimestamp
    private String lastUpdate;

}
