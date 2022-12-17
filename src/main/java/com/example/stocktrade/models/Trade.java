package com.example.stocktrade.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "date_created",updatable = false,nullable = false,columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    @Temporal(value = TemporalType.TIMESTAMP)
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-mm-dd hh:mm:ss")
    private Date dateCreated;


    @Column(name = "date_updated",columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    @Temporal(value = TemporalType.TIMESTAMP)
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-mm-dd hh:mm:ss")
    private Date dateUpdated;

    void dateCreatedAt() {
        this.dateCreated = new Date();
    }
    @PrePersist
    void dateUpdatedAt(){
        this.dateUpdated = new Date();
    }

}
