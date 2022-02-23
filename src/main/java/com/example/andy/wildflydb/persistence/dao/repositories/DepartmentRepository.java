package com.example.andy.wildflydb.persistence.dao.repositories;

import com.example.andy.wildflydb.persistence.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
