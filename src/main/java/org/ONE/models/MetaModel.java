package org.ONE.models;


import jakarta.persistence.*;

@Table
@Entity(name = "meta")
public class MetaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
