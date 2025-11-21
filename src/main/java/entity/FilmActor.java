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
@Table(name = "film_actor", schema = "movie")
public class FilmActor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "actor_id")
    private Integer actor_id;

    @Column(name = "film_id", nullable = false)
    private Integer film_id;

    @Column(name = "last_update", nullable = false)
    @UpdateTimestamp
    private String lastUpdate;
}
