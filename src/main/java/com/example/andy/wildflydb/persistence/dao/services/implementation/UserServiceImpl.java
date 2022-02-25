package com.example.andy.wildflydb.persistence.dao.services.implementation;

import com.example.andy.wildflydb.exceptions.ResourceNotFoundException;
import com.example.andy.wildflydb.persistence.dao.repositories.UserRepository;
import com.example.andy.wildflydb.persistence.dao.services.interfaces.IUserService;
import com.example.andy.wildflydb.persistence.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements IUserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAllUsers() {
        log.info("Inside findAllUser method of UserServiceImpl");
        List<User> users = userRepository.findAll();
        if (users.isEmpty()){
            return Collections.emptyList();
        }
        else {
            return users;
        }
    }

    @Override
    public List<User> findUserByFirstName(String firstName) {
        log.info("Inside findUserByFirstName method of UserServiceImpl");
        List<User> users = userRepository.findUserByFirstNameContainingIgnoreCase(firstName);
        if (users.isEmpty()){
            throw new ResourceNotFoundException("Not found User with firstName " + firstName);
        }
        else {
            return users;
        }
    }


    @Override
    public List<User> findUserByLastName(String lastName) {
        log.info("Inside findUserByFirstNameOrLastName method of UserServiceImpl");
        List<User> users = userRepository.findUserByLastNameContainingIgnoreCase(lastName);
        if (users.isEmpty()){
            throw new ResourceNotFoundException("Not found User with last name " + lastName);
        }
        else {
            return users;
        }
    }

    @Override
    public User saveUser(User user) {
        log.info("Inside saveUser method of UserServiceImpl");
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Integer id, User userRequest) {
        log.info("Inside updateUser method of UserServiceImpl");
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found User with id " + id));
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());

        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(Integer id) {
        log.info("Inside deleteUserById method of UserServiceImpl");
        userRepository.deleteById(id);
    }
}
