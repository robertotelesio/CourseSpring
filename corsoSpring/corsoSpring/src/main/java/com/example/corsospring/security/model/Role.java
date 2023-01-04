package com.example.corsospring.security.model;

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
    private Integer id;
    private String Rname;
    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private RoleType roleName;

    @ManyToMany
    @JoinTable(name = "user_roleType",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<Role> roles = new HashSet<>();
}