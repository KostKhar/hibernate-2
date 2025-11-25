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
@Table(name = "film_category", schema = "movie")
public class FilmCategory {
    @EmbeddedId
    private FilmCategoryId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("film_id")
    @JoinColumn(name = "film_id", insertable = false, updatable = false)
    private Film film;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("category_id")
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private Category category;

    @Column(name = "last_update", nullable = false)
    @UpdateTimestamp
    private LocalDateTime lastUpdate;

    @Embeddable
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class FilmCategoryId implements Serializable {
        @Column(name = "film_id", insertable = false, updatable = false)
        private Integer film_id;

        @Column(name = "category_id", insertable = false, updatable = false)
        private Integer category_id;
    }

}
