package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "film", schema = "test")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private int film_id;

    @Column(name = "title", nullable = false, length = 120)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "release_year")
    private Date release_year;

    @Column(name = "language_id",  nullable = false)
    private Byte language_id;

    @Column(name = "original_language_id")
    private Byte original_language_id;

    @Column(name = "rental_duration")
    private Byte rental_duration;

    @Column(name = "rental_rate")
    private Double rental_rate;

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


    @Column(name = "last_update", nullable = false)
    @UpdateTimestamp
    private Date lastUpdate;
}
