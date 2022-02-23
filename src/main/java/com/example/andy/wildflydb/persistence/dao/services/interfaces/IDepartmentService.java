package com.example.andy.wildflydb.persistence.dao.services.interfaces;

import com.example.andy.wildflydb.persistence.model.Department;

import java.util.List;

public interface IDepartmentService {
    List<Department> findAllDepartments();
}
