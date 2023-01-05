package com.example.corsospring.business;

import com.example.corsospring.exception.ResourceNotFoundException;
import com.example.corsospring.model.Role;
import com.example.corsospring.model.RoleType;
import com.example.corsospring.model.User;
import com.example.corsospring.payload.request.SignupRequest;
import com.example.corsospring.repository.RoleRepository;
import com.example.corsospring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

//@Service
public class register {
    }

//
//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    RoleRepository roleRepository;
//
//    @Autowired
//    PasswordEncoder encoder;
//
//    public User registerUserService(@Valid @RequestBody SignupRequest signUpRequest) {
//        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
//            throw new ResourceNotFoundException("Error: Username is already taken!");
//        }
//
//        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
//            throw new ResourceNotFoundException("Error: Email is already in use!");
//        }
//
//        // Create new user's account
//        User user = new User(signUpRequest.getUsername(),
//                signUpRequest.getEmail(),
//                encoder.encode(signUpRequest.getPassword()));
//
//        Set<String> strRoles = signUpRequest.getRole();
//        Set<Role> roles = new HashSet<>();
//
//        if (strRoles == null) {
//            Role userRole = roleRepository.findByRoleType(RoleType.ROLE_USER)
//                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//            roles.add(userRole);
//        } else {
//            strRoles.forEach(role -> {
//                switch (role) {
//                    case "admin" -> {
//                        Role adminRole = roleRepository.findByRoleType(RoleType.ROLE_ADMIN)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(adminRole);
//                    }
//                    case "mod" -> {
//                        Role modRole = roleRepository.findByRoleType(RoleType.ROLE_MODERATOR)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(modRole);
//                    }
//                    default -> {
//                        Role userRole = roleRepository.findByRoleType(RoleType.ROLE_USER)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(userRole);
//                    }
//                }
//            });
//        }
//
//        user.setRoles(roles);
//        userRepository.save(user);
//        return user;
//    }
//}
//
