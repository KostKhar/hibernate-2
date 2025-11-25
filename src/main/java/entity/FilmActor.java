package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "film_actor", schema = "movie")
public class FilmActor {
    @EmbeddedId
    private FilmActorId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("actor_id")
    @JoinColumn(name = "actor_id")
    private Actor actor;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("film_id")
    @JoinColumn(name = "film_id")
    private Film film;

    @Column(name = "last_update", nullable = false)
    @UpdateTimestamp
    private LocalDateTime lastUpdate;

    @Embeddable
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FilmActorId implements Serializable {

        @Column(name = "actor_id")
        private Integer actorId;

        @Column(name = "film_id")
        private Integer filmId;
    }
}

