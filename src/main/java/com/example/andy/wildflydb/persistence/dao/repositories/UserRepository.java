package com.example.andy.wildflydb.persistence.dao.repositories;

import com.example.andy.wildflydb.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findUserByFirstNameContainingIgnoreCase(String firstName);
    List<User> findUserByLastNameContainingIgnoreCase(String lastName);

}
