package com.example.stocktrade.models;

import java.io.Serializable;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import lombok.*;
import jakarta.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Table (name = "users")
public class User implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long Id;
    private String username;


    @OneToMany(mappedBy = "user", cascade = CascadeType.MERGE, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = "user", allowSetters = true)

    private List<Trade> trades = new ArrayList<>();



}