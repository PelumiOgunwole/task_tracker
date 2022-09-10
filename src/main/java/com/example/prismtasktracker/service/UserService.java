package com.example.prismtasktracker.service;

import com.example.prismtasktracker.model.Task;
import com.example.prismtasktracker.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public User userRegister(User user);
//    public void userLogIn();
public Optional<User> findUserById(String id);
}
