package com.example.andy.wildflydb.controllers;


import com.example.andy.wildflydb.persistence.dao.services.interfaces.IDepartmentService;
import com.example.andy.wildflydb.persistence.model.Department;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class DepartmentController {
    private IDepartmentService departmentService;

    public DepartmentController(IDepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/departments")
    public ResponseEntity<List<Department>> getAllDepartments() {
        log.info("Inside getAllDepartments method of DepartmentController");
        try {
            List<Department> departments = departmentService.findAllDepartments();
            if (departments.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(departments, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/departments/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable("id") Integer id){
        log.info("Inside getDepartmentById method of DepartmentController");
        Department department = departmentService.findDepartmentById(id);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @GetMapping("/departments/name/{name}")
    public ResponseEntity<List<Department>> getDepartmentByName(@PathVariable("name") String name){
        log.info("Inside getDepartmentByName method of DepartmentController");
        List<Department> departments = departmentService.findDepartmentByName(name);
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @GetMapping("/departments/address/{address}")
    public ResponseEntity<List<Department>> getDepartmentByAddress(@PathVariable("address") String address){
        log.info("Inside getDepartmentByAddress method of DepartmentController");
        List<Department> departments = departmentService.findDepartmentByAddress(address);
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @PostMapping("/departments")
    public ResponseEntity<Department> createDepartment( @Valid @RequestBody Department department){
        log.info("Inside createDepartment method of DepartmentController");
        Department newDepartment = departmentService.saveDepartment(new Department(department.getName(), department.getAddress(), department.getCode()));
        return new ResponseEntity<>(newDepartment, HttpStatus.CREATED);
    }

    @PutMapping("/departments/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable("id") Integer id, @Valid @RequestBody Department department){
        Department updateDepartment = departmentService.updateDepartment(id, department);
        return new ResponseEntity<>(updateDepartment, HttpStatus.OK);
    }

    @DeleteMapping("/departments/{id}")
    public ResponseEntity<HttpStatus> deleteDepartmentById(@PathVariable("id") Integer id){
        try {
            departmentService.deleteDepartmentById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}