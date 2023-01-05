package com.example.corsospring.controller;

import com.example.corsospring.exception.ResourceNotFoundException;
import com.example.corsospring.model.Course;
import com.example.corsospring.model.Role;
import com.example.corsospring.model.User;
import com.example.corsospring.payload.request.SignupRequest;
import com.example.corsospring.payload.response.MessageResponse;
import com.example.corsospring.repository.CourseRepository;
import com.example.corsospring.repository.RoleRepository;
import com.example.corsospring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    AuthController authController;


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
    @GetMapping("/user/{id}")
    // Look for a user from his id
    public ResponseEntity<?> getUserByID(@PathVariable("id") long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            // Custom output message
            return new ResponseEntity<>("The user ID " + id +
                    " does not exist in this db.", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") long id) {
        userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User ID " + id + " not found.")
        );

        userRepository.deleteById(id);

        return new ResponseEntity<>("The user with the ID " + id +
                " has been successfully been deleted", HttpStatus.OK);

}
    @PutMapping("/user/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User userRequest) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User ID " + id +
                        " not found")
        );
        if (userRequest.getUsername() != null) {
            user.setUsername(userRequest.getUsername());
        }

        if (userRequest.getEmail() != null) {
            user.setEmail(userRequest.getEmail());
        }

        if (userRequest.getPassword() != null) {
            user.setPassword(userRequest.getPassword());
        }

        return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
    }

    @GetMapping("/user/{id}/courses")
    public Set<Course> getCoursesForUser(@PathVariable("id") long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User with ID " + id + " not found.")
        );
        return user.getCourses();
    }

    @PostMapping("/userAddCourse/{id}/course")
    public ResponseEntity<?> createCourseUser(@PathVariable Long id, @RequestBody Course course) {
        User user = userRepository.getReferenceById(id);

        Course courseU = courseRepository.getReferenceById(course.getId());

        Set<User> userSet = new HashSet<>();
        userSet.add(user);
        courseU.setUsers(userSet);

        Course courseA = courseRepository.save(courseU);
        return new ResponseEntity<>(courseA,HttpStatus.CREATED);
    }



    @PostMapping("/userAddRole/{id}/role")
    public ResponseEntity<?> createRoleUser(@PathVariable Long id, @RequestBody Role role) {
        User userU = userRepository.getReferenceById(id);

        Role roleR = roleRepository.save(role);

        Set<Role> roleSet = new HashSet<>();
        roleSet.add(roleR);
        userU.setRoles(roleSet);

        User userA = userRepository.save(userU);
        return new ResponseEntity<>(userA,HttpStatus.CREATED);
    }

    @PostMapping("/user/add/mod")
    public ResponseEntity<?> createUserAdmin(@Valid @RequestBody SignupRequest signUpRequest) {
        authController.registerUser(signUpRequest);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }






}
