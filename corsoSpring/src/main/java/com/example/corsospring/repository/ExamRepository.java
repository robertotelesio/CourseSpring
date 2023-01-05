package com.example.corsospring.repository;

import com.example.corsospring.model.Exam;
import com.example.corsospring.model.Role;
import com.example.corsospring.model.RoleType;
import com.example.corsospring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface ExamRepository extends JpaRepository<Exam,Long> {



    Optional<Set<Exam>> findByValutazione(int valutazione);
}
