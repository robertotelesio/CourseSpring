package com.example.corsospring.repository;

import com.example.corsospring.model.Role;
import com.example.corsospring.model.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Role getRoleById(long id);

    Optional<Role> findByRoleType(RoleType roleType);
}
