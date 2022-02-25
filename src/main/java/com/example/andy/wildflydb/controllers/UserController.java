package com.example.andy.wildflydb.controllers;

import com.example.andy.wildflydb.persistence.dao.services.interfaces.IDepartmentService;
import com.example.andy.wildflydb.persistence.dao.services.interfaces.IUserService;
import com.example.andy.wildflydb.persistence.model.Department;
import com.example.andy.wildflydb.persistence.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {
    private IUserService userService;
    private IDepartmentService departmentService;

    public UserController(IUserService userService, IDepartmentService departmentService) {
        this.userService = userService;
        this.departmentService = departmentService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        log.info("Inside getAllUser method of UserController");
        try {
            List<User> users = userService.findAllUsers();
            if (users.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            else {
                return new ResponseEntity<>(users, HttpStatus.OK);
            }
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/firstName/{firstName}")
    public ResponseEntity<List<User>> getAllUsersByFirstName(@PathVariable("firstName") String firstName){
        log.info("Inside getAllUsersByFirstName method of UserController");
        List<User> users = userService.findUserByFirstName(firstName);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/users/lastName/{lastName}")
    public ResponseEntity<List<User>> getAllUsersByLastName(@PathVariable("lastName") String lastName){
        log.info("Inside getAllUsersByFirstOrLastName method of UserController");
        List<User> users = userService.findUserByLastName(lastName);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/departments/{id}/users")
    public ResponseEntity<User> createUser(@PathVariable("id") Integer departmentId, @Valid @RequestBody User userRequest){
        log.info("Inside createUser method of UserController");
        Department department = departmentService.findDepartmentById(departmentId);
        User newUser = new User(userRequest.getFirstName(), userRequest.getLastName(), userRequest.getEmail());
        newUser.setDepartment(department);
        userService.saveUser(newUser);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Integer id, @Valid @RequestBody User userRequest){
        log.info("Inside updateUser method of UserController");
        User user = userService.updateUser(id, userRequest);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUserById(@PathVariable("id") Integer id){
        try {
            userService.deleteUserById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
