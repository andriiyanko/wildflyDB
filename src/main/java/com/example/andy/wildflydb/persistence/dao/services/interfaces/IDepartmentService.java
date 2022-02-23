package com.example.andy.wildflydb.persistence.dao.services.interfaces;

import com.example.andy.wildflydb.persistence.model.Department;

import java.util.List;

public interface IDepartmentService {
    List<Department> findAllDepartments();
    Department findDepartmentById(Integer id);
    List<Department> findDepartmentByName(String name);
    List<Department> findDepartmentByAddress(String address);
    Department saveDepartment(Department department);
    Department updateDepartment(Integer id, Department department);
}
