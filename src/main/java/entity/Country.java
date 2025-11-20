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
@Table(name = "country", schema = "movie")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    private Long country_id;


    @Column(name = "country", length = 50, nullable = false)
    private String country;

    @Column(name = "last_update", nullable = false)
    @UpdateTimestamp
    private String lastUpdate;
}
