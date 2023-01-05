package com.example.corsospring.controller;

import com.example.corsospring.exception.ResourceNotFoundException;
import com.example.corsospring.model.RoleType;
import com.example.corsospring.model.User;
import com.example.corsospring.repository.RoleRepository;
import com.example.corsospring.model.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class RoleController {
    private final RoleRepository roleRepository;

    public RoleController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @GetMapping("/role")
    public ResponseEntity<List<Role>> getRoles (){
        List<Role> rolesArrayList = new ArrayList<Role>();
        roleRepository.findAll().forEach(rolesArrayList::add);
        if (rolesArrayList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(rolesArrayList, HttpStatus.OK);
    }
    @PostMapping("/addRole")
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        System.out.println(role.getRname());
        Role _role = roleRepository.save(role);
        return new ResponseEntity<>(_role, HttpStatus.CREATED);
    }

    @DeleteMapping("/role/{id}")
    public ResponseEntity<HttpStatus> deleteRole(@PathVariable("id") long id) {
        roleRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @PutMapping("/role/{id}")
//    public ResponseEntity<Role> updateRole(@PathVariable("id") long id, @RequestBody Role roleRequest) {
//        Role role = roleRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("RoleId " + id + "not found"));
//
//        role.setRname(roleRequest.getRname());
//        role.setRoleType(roleRequest.getRoleType());
//        Role result = roleRepository.save(role);
//
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }
    @GetMapping("/role/{roleType}/users")
    public Set<User> getUsersForCourse(@PathVariable("roleType") RoleType roleType) {
        Role role = roleRepository.findByRoleType(roleType).orElseThrow(
                () -> new ResourceNotFoundException("No user with " + roleType + " found.")
        );
        return role.getUsers();
    }




}
