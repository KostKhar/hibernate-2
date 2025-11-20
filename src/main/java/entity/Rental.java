package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jdk.jfr.Timestamp;
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
@Table(name = "payment", schema = "movie")
public class Rental {
    @Id
    @Column(name = "rental_id")
    private Integer rental_id;

    @Column(name = "rental_date", nullable = false)
    @Timestamp
    private Date rental_date;

    @Column(name = "inventory_id",nullable = false)
    private Integer inventory_id;

    @Column(name = "customer_id", nullable = false)
    private Integer customer_id;


    @Column(name = "return_date", nullable = false)
    @Timestamp
    private Date return_date;

    @Column(name = "staff_id", nullable = false)
    private Integer staff_id;

    @Column(name = "last_update", nullable = false)
    @UpdateTimestamp
    private Date lastUpdate;
}
