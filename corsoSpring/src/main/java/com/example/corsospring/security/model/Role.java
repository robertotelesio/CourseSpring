package com.example.corsospring.security.model;

import com.example.corsospring.model.User;
import com.example.corsospring.security.RoleType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String Rname;
    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private RoleType roleType;

    @ManyToMany
    @JoinTable(name = "user_roleType",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users = new HashSet<>();
}