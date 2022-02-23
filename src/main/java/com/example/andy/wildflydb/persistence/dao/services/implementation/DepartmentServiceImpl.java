package com.example.andy.wildflydb.persistence.dao.services.implementation;

import com.example.andy.wildflydb.persistence.dao.repositories.DepartmentRepository;
import com.example.andy.wildflydb.persistence.dao.services.interfaces.IDepartmentService;
import com.example.andy.wildflydb.persistence.model.Department;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DepartmentServiceImpl implements IDepartmentService {

    private DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<Department> findAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        if (departments.isEmpty()){
            return Collections.emptyList();
        }
        else{
            return departments;
        }
    }
}
