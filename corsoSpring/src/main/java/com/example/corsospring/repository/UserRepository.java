package com.example.corsospring.repository;

import com.example.corsospring.model.Course;
import com.example.corsospring.model.Role;
import com.example.corsospring.model.RoleType;
import com.example.corsospring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User,Long>{
    User getUserById(long id);

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);


}
