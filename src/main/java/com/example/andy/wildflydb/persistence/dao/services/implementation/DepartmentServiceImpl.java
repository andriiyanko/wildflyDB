package com.example.andy.wildflydb.persistence.dao.services.implementation;

import com.example.andy.wildflydb.exceptions.ResourceNotFoundException;
import com.example.andy.wildflydb.persistence.dao.repositories.DepartmentRepository;
import com.example.andy.wildflydb.persistence.dao.services.interfaces.IDepartmentService;
import com.example.andy.wildflydb.persistence.model.Department;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
@Builder
public class DepartmentServiceImpl implements IDepartmentService {

    private DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<Department> findAllDepartments() {
        log.info("Inside findAllDepartments method of DepartmentServiceImpl");
        List<Department> departments = departmentRepository.findAll();
        if (departments.isEmpty()){
            return Collections.emptyList();
        }
        else{
            return departments;
        }
    }

    @Override
    public Department findDepartmentById(Integer id) {
        log.info("Inside findDepartmentById method of DepartmentServiceImpl");
        return departmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found Department with id " + id));
    }

    @Override
    public List<Department> findDepartmentByName(String name) {
        log.info("Inside findDepartmentByName method of DepartmentServiceImpl");
        List<Department> departments = departmentRepository.findDepartmentByNameContainingIgnoreCase(name);
        if (departments.isEmpty()){
            throw new ResourceNotFoundException("Not found Department by name " + name);
        }
        else {
            return departments;
        }
    }

    @Override
    public List<Department> findDepartmentByAddress(String address) {
        log.info("Inside findDepartmentByAddress method of DepartmentServiceImpl");
        List<Department> departments = departmentRepository.findDepartmentByAddressContainingIgnoreCase(address);
        if (departments.isEmpty()){
            throw new ResourceNotFoundException("Not found Department by address " + address);
        }
        else {
            return departments;
        }
    }

    @Override
    public Department saveDepartment(Department department) {
        log.info("Inside saveDepartment method of DepartmentServiceImpl");
        return departmentRepository.save(department);
    }
}
