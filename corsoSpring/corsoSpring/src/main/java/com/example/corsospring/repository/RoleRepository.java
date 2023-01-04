package com.example.corsospring.repository;

import com.example.corsospring.model.User;
import com.example.corsospring.security.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
