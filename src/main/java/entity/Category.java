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
@Table(name = "category", schema = "test")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Integer category_id;

    @Column(name = "name", length = 25, nullable = false)
    private String name;

    @Column(name = "last_update", nullable = false)
    @UpdateTimestamp
    private String lastUpdate;
}
