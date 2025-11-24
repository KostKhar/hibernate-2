package entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "film", schema = "movie")
public class Film {

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id", unique = true, nullable = false)
    private int film_id;

    @Setter
    @Getter
    @Column(name = "title", nullable = false, length = 128)
    private String title;

    @Setter
    @Getter
    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Setter
    @Getter
    @Column(name = "release_year")
    private LocalDate release_year;

    @Setter
    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id")
    private Language language;

    @Setter
    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id")
    private Language original_language;

    @Setter
    @Getter
    @Column(name = "rental_duration")
    private Byte rental_duration;

    @Setter
    @Getter
    @Column(name = "rental_rate")
    private Double rental_rate;

    @Setter
    @Getter
    @Column(name = "length", nullable = false)
    private Integer length;

    @Column(name = "rating", columnDefinition = "ENUM('G', 'PG', 'PG-13', 'R', 'NC-17')")
    private String rating;

    public Rating getRating() {
        return Rating.fromDbValue(rating);
    }

    public void setRating(Rating rating) {
        this.rating = rating != null ? rating.getDbValue() : null;
    }

    public String getRatingValue() {
        return rating;
    }

    @Column(name = "special_features", columnDefinition = "ENUM('Trailers', 'Commentaries', 'Deleted Scenes', 'Behind the Scenes')")
    private String specialFeatures;

    public SpecialFeature getSpecialFeatures() {
        return SpecialFeature.fromDbValue(specialFeatures);
    }

    public void setSpecialFeatures(SpecialFeature feature) {
        this.specialFeatures = feature != null ? feature.getDbValue() : null;
    }

    @Setter
    @Getter
    @OneToMany(mappedBy = "film")
    private Set<FilmActor> filmActors = new HashSet<>();

    @Setter
    @Getter
    @OneToMany(mappedBy = "film",  cascade = CascadeType.ALL)
    private Set<FilmActor> filmCategory = new HashSet<>();

    @Setter
    @Getter
    @Column(name = "last_update", nullable = false)
    @UpdateTimestamp
    private LocalDateTime lastUpdate;

    @OneToOne
    @JoinTable(
            name = "film_text",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "film_id")
    )
    private FilmText filmText;


    @ManyToMany
    @JoinTable(name = "film_actor",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private Set<Actor> actors;

    @ManyToMany
    @JoinTable(name = "film_category",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> category;

}
