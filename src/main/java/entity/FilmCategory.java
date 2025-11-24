package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "film_category", schema = "movie")
public class FilmCategory {
    @Id
    @Column(name = "film_id", insertable = false, updatable = false)
    private  Integer film_id;

    @Id
    @Column(name = "category_id", insertable = false, updatable = false)
    private Integer category_id;

    @Column(name = "last_update", nullable = false)
    @UpdateTimestamp
    private LocalDateTime lastUpdate;

    @ManyToMany
    @JoinTable(name = "film_category",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "film_id"))
    private Set<Film> films;

    @ManyToMany
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private Set<Category> categories;

}
