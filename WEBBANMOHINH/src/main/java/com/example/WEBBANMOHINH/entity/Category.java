package com.example.WEBBANMOHINH.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Category {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer  id;
    @Column
    private String name;
}



