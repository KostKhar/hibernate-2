package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "film_category", schema = "movie")
public class FilmCategory {
    @Id
    @Column(name = "film_id")
    private int film_id;


    @Column(name = "category_id")
    private int category_id;


    @Column(name = "last_update", nullable = false)
    @UpdateTimestamp
    private Date lastUpdate;


}
