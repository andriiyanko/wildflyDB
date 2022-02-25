package com.example.andy.wildflydb.persistence.dao.services.interfaces;

import com.example.andy.wildflydb.persistence.model.User;

import java.util.List;

public interface IUserService {
    List<User> findAllUsers();
    List<User> findUserByFirstName(String firstName);
    List<User> findUserByLastName(String lastName);
    User saveUser(User user);
    User updateUser (Integer id, User userRequest);
    void deleteUserById(Integer id);

}
