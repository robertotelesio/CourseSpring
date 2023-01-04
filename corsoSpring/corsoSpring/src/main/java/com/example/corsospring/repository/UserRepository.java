package com.example.corsospring.repository;

import com.example.corsospring.model.Course;
import com.example.corsospring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User,Long>{
    User getUserById(long id);
}
