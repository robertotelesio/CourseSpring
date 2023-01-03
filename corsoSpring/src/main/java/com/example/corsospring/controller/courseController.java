package com.example.corsospring.controller;


import com.example.corsospring.model.Course;
import com.example.corsospring.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
public class courseController {

    @Autowired
    CourseRepository courseRepository;

    @GetMapping("/course")
    public ResponseEntity<List<Course>> getStudents (){
        List<Course> coursesArrayList = new ArrayList<Course>();
        courseRepository.findAll().forEach(coursesArrayList::add);
        if (coursesArrayList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(coursesArrayList, HttpStatus.OK);
    }

    @PostMapping("/addCourse")
    public ResponseEntity<Course> createTutorial(@RequestBody Course course) {
        Course _course = courseRepository.save(course);
        return new ResponseEntity<>(_course, HttpStatus.CREATED);
    }

    @DeleteMapping("/course/{id}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable("id") long id) {
        courseRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/course/{id}")
            public ResponseEntity<Course> updateTag(@PathVariable("id") long id, @RequestBody Course courseRequest) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("courseId " + id + "not found"));

        course.setNomeCorso(courseRequest.getNomeCorso());
        course.setDescrizione(courseRequest.getDescrizione());

        return new ResponseEntity<>(courseRepository.save(course), HttpStatus.OK);
    }


}
