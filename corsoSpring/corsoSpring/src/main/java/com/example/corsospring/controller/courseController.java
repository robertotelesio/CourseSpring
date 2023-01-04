package com.example.corsospring.controller;


import com.example.corsospring.model.Course;
import com.example.corsospring.model.User;
import com.example.corsospring.repository.CourseRepository;
import com.example.corsospring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/api")
public class courseController {

    @Autowired
    CourseRepository courseRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/course")
    public ResponseEntity<List<Course>> getCourse (){
        List<Course> coursesArrayList = new ArrayList<Course>();
        courseRepository.findAll().forEach(coursesArrayList::add);
        if (coursesArrayList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(coursesArrayList, HttpStatus.OK);
    }

    @PostMapping("/addCourse")
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        Course _course = courseRepository.save(course);
        return new ResponseEntity<>(_course, HttpStatus.CREATED);
    }

    @DeleteMapping("/course/{id}")
    public ResponseEntity<HttpStatus> deleteCourse(@PathVariable("id") long id) {
        courseRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/course/{id}")
            public ResponseEntity<Course> updateCourse(@PathVariable("id") long id, @RequestBody Course courseRequest) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("courseId " + id + "not found"));

        course.setNomeCorso(courseRequest.getNomeCorso());
        course.setDescrizione(courseRequest.getDescrizione());

        return new ResponseEntity<>(courseRepository.save(course), HttpStatus.OK);
    }

    @GetMapping("getcourse/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        Course course = courseRepository.getCourseById(id);
        if (course == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(course, HttpStatus.OK);
    }
    @PostMapping("/user/{id}/course")
    public ResponseEntity<Course> CreateCourseUser(@PathVariable Long id ,@RequestBody Course course) {
        User user = userRepository.getReferenceById(id);
        Set<User> userSet = new HashSet<>();
        userSet.add(user);
        course.setUsers(userSet);
        Course _course = courseRepository.save(course);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
