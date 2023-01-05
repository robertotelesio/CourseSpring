package com.example.corsospring.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "exams")
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "valutazione")
    private int valutazione;

    @Column(name = "day")
    private int day;

    @Column(name = "month")
    private int month;

    @Column(name = "year")
    private int year;
    @ManyToOne(cascade ={CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.PERSIST})
    @JoinTable(name = "exam_course",
            joinColumns = @JoinColumn(name = "exam_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Course course;
}
