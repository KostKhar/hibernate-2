package entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
    @JoinColumn(name = "original_language_id")
    private Language originalLanguage;

    @Setter
    @Getter
    @Column(name = "rental_duration")
    private Integer rental_duration;

    @Setter
    @Getter
    @Column(name = "rental_rate", precision = 10, scale = 2)
    private BigDecimal rental_rate;

    @Setter
    @Getter
    @Column(name = "length", nullable = false)
    private Integer length;

    @Column(name = "rating", columnDefinition = "ENUM('G', 'PG', 'PG-13', 'R', 'NC-17')")
    private String rating;


    @Setter
    @Getter
    @Convert(converter = SpecialFeaturesConverter.class)
    @Column(name = "special_features", columnDefinition = "SET('Trailers','Commentaries','Deleted Scenes','Behind the Scenes')")
    private List<SpecialFeature> specialFeatures;

    @Setter
    @Getter
    @Column(name = "last_update", nullable = false)
    @UpdateTimestamp
    private LocalDateTime lastUpdate;


    @OneToOne
    @PrimaryKeyJoinColumn(name = "film_id")
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

    public Rating getRating() {
        return Rating.fromDbValue(rating);
    }

    public void setRating(Rating rating) {
        this.rating = rating != null ? rating.getDbValue() : null;
    }

    public String toString(){
        return getTitle() + " " + getDescription();
    }


}
