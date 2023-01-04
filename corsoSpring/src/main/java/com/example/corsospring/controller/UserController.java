package com.example.corsospring.controller;

import com.example.corsospring.model.Course;
import com.example.corsospring.model.User;
import com.example.corsospring.repository.CourseRepository;
import com.example.corsospring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/user")
    public ResponseEntity<List<User>> getUsers (){
        List<User> usersArrayList = new ArrayList<User>();
        userRepository.findAll().forEach(usersArrayList::add);
        if (usersArrayList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(usersArrayList, HttpStatus.OK);
    }
    @PostMapping("/addUser")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User _user = userRepository.save(user);
        return new ResponseEntity<>(_user, HttpStatus.CREATED);
    }

}
