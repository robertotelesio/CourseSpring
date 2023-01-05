package com.example.corsospring.repository;

import com.example.corsospring.model.Exam;
import com.example.corsospring.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<Exam,Long> {
}
