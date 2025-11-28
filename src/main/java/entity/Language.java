package entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "language", schema = "movie")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id", unique = true, nullable = false)
    private Integer language_id;

    @Column(name = "name", nullable = false, columnDefinition = "CHAR(20)")
    private String name;

    @Column(name = "last_update", nullable = false)
    @UpdateTimestamp
    private LocalDateTime lastUpdate;

    @OneToMany(mappedBy = "language")
    private Set<Film> films;

    @OneToMany(mappedBy = "originalLanguage")
    private Set<Film> filmsAsOriginal;
}
