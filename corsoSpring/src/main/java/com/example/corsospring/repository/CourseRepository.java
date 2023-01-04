package com.example.corsospring.repository;

import com.example.corsospring.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long>{
    Course getCourseById(long id);



}
