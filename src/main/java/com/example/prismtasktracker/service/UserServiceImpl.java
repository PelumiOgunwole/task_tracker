package com.example.prismtasktracker.service;

import com.example.prismtasktracker.model.User;
import com.example.prismtasktracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Override
    public User userRegister(User user) {
        // To find if the user already exists, if so save the user details, so that he isn't registered
        Optional<User> d_user = userRepository.findById(user.getUserName());

        if(d_user.isEmpty())
        {
            System.out.println("Record: " +d_user);
            return userRepository.save(user);
//        return true;
        }
//        return false;
        else{
            System.out.println("Already existing");
            return null;}
    }

    public Optional<User> findUserById(String id){
        return userRepository.findById(id);
    }



}
