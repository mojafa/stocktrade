package com.example.stocktrade.models;

import java.io.Serializable;

import lombok.*;
import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Table (name = "users")
public class User implements Serializable{
    @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String username;



}