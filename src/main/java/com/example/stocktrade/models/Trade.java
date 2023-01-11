package com.example.stocktrade.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;



@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;

    // @JoinColumn(name="id")
     @ManyToOne
     private User user;
    private String symbol;
    private Integer shares;
    private Float price;

    @Column(name = "date_created", updatable = false, nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    @Temporal(value = TemporalType.DATE)
    private Date dateCreated;

    @Column(name = "date_updated", columnDefinition = "DATETIME ON UPDATE CURRENT_TIMESTAMP")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date dateUpdated;

    @PrePersist
    void dateCreatedAt() {
        this.dateCreated = new Date();
    }

    @PreUpdate
    void dateUpdatedAt() {
        this.dateUpdated = new Date();
    }
}
