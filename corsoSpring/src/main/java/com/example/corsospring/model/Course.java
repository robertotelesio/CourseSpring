package com.example.corsospring.model;

import jakarta.persistence.*;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@Entity
@Table(name = "course")
public class Course {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;


        @Column(name = "nomeCorso")
        private String nomeCorso;


        @Column(name = "descrizione")
        private String descrizione;



}
