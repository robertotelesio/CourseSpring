package com.example.corsospring.controller;

import com.example.corsospring.exception.ResourceNotFoundException;
import com.example.corsospring.model.Course;
import com.example.corsospring.model.Exam;
import com.example.corsospring.model.User;
import com.example.corsospring.repository.CourseRepository;
import com.example.corsospring.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class ExamController {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    ExamRepository examRepository;

    @GetMapping("/exam")
    public ResponseEntity<List<Exam>> getExam () {
        List<Exam> examArrayList = new ArrayList<Exam>();
        examRepository.findAll().forEach(examArrayList::add);
        if (examArrayList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(examArrayList, HttpStatus.OK);

    }
        @PostMapping("/addExam")
        public ResponseEntity<Exam> createExam(@RequestBody Exam exam) {
            Exam _exam = examRepository.save(exam);
            return new ResponseEntity<>(_exam, HttpStatus.CREATED);
        }
    @DeleteMapping("/exam/delete/{id}")
    public ResponseEntity<HttpStatus> deleteExam(@PathVariable("id") long id) {
        examRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/exam/put/{id}")
    public ResponseEntity<Exam> updateExam(@PathVariable("id") long id, @RequestBody Exam examRequest) {
        Exam exam = examRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("courseId " + id + "not found"));

        exam.setCourse(examRequest.getCourse());
        exam.setDay(examRequest.getDay());
        exam.setMonth(examRequest.getMonth());
        exam.setYear(examRequest.getYear());


        return new ResponseEntity<>(examRepository.save(exam), HttpStatus.OK);
    }
    @GetMapping("/exam/{valutazione}")
    public ResponseEntity<?> getExamByValutazione(@PathVariable("valutazione") int valutazione) {
        Set<Exam> exams= examRepository.findByValutazione(valutazione).orElseThrow(
                () -> new ResourceNotFoundException("Exam with Vote " + valutazione + " not found.")
        );
        return new ResponseEntity<>(exams , HttpStatus.OK);
    }

}



