package com.example.prismtasktracker.service;

import com.example.prismtasktracker.model.User;
import com.example.prismtasktracker.repository.UserRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    private User user;

    @Before
    public void setUp() throws Exception {
        user= new User("Brad","Transversy","password","password");
        when(userRepository.save(user)).thenReturn(user);

    }

    @Test
    public void userRegister() {
        doReturn(user).when(userRepository).save(user);

//        User returned_user = userService.userRegister(user);
//        assertNotNull(returned_user);
//        when(userServiceImpl.userRegister(user)).thenReturn(user);
//        assertEquals(user,userServiceImpl.userRegister(user));
    }

    @Test
    public void findUserById() {
    }
}