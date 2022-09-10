package com.example.prismtasktracker.service;

import com.example.prismtasktracker.model.Task;
import com.example.prismtasktracker.model.User;
import com.example.prismtasktracker.repository.TaskRepository;
import com.example.prismtasktracker.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@SpringBootTest
class TaskServiceImplTest {
    @Mock
    TaskRepository taskRepository;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    TaskServiceImpl taskServiceImpl;
    private Task task,task2,task3,task4;
    private LocalDate localDate;
    private User user,user2,user3,user4;
    List<Task> alltasks, pendingTasks,progressTasks,doneTasks;

    @BeforeEach
    void setUp() {
        alltasks= new ArrayList<>();
        pendingTasks= new ArrayList<>();
        progressTasks = new ArrayList<>();
        doneTasks= new ArrayList<>();


        user = new User("adama","Adams Oshiomole","adama","adama");
        user2= new User("Chidi","Chidinma Okafor","chidi","chidi");
        user3 =new User("Dinah","Dinah Osayi","dinah","dinah");
        user4 = new User("Vee","Vivian Stephen","vivi","vivi");

        localDate= LocalDate.of(Integer.parseInt("2022"), Month.SEPTEMBER,12);
        task= new Task(1,"Visit","Visit Tom","Pending",localDate,user);
        task2= new Task(2,"Visit","Visit Bala","In_Progress",localDate,user2);
        task3= new Task(3,"Cook","Cook efo riro","Done",localDate,user3);
        task4= new Task(4,"Market","Buy Snacks ","Pending",localDate,user4);

        // Task repo Save() mock
        when(taskRepository.save(task)).thenReturn(task);
        when(taskRepository.save(task2)).thenReturn(task2);
        when(taskRepository.save(task3)).thenReturn(task3);
        when(taskRepository.save(task4)).thenReturn(task4);
        // Task repo Save() mock
        when(userRepository.save(user)).thenReturn(user);
        when(userRepository.save(user2)).thenReturn(user2);
        when(userRepository.save(user3)).thenReturn(user3);
        when(userRepository.save(user4)).thenReturn(user4);

        // Find  User By ID Repo
        when(userRepository.findById("adama")).thenReturn(Optional.ofNullable(user));
        // Find Task By ID Repo
        when(taskRepository.findById(1)).thenReturn(Optional.ofNullable(task));

        // All Tasks mock
        alltasks.add(task);
        alltasks.add(task2);
        alltasks.add(task3);
        alltasks.add(task4);

        when(taskRepository.findAll()).thenReturn(alltasks);

        // Pending Tasks Mock
        pendingTasks.add(task);
        pendingTasks.add(task4);
        when(taskRepository.findPendingTaskByStatus()).thenReturn(pendingTasks);

        // In Progress Tasks Mock
        progressTasks.add(task2);
        when(taskRepository.findInProgressTaskByStatus()).thenReturn(progressTasks);

        // Done Tasks Mock

        doneTasks.add(task3);
        when(taskRepository.findDoneTaskByStatus()).thenReturn(doneTasks);

        // Update task Repo
//        when(taskRepository.delete();)
    }

    @Test
    public void addTask() {
        when(taskServiceImpl.addTask(task, "adama")).thenReturn(task);
        assertEquals(task,taskServiceImpl.addTask(task, user.getUserName()));
    }

    @Test
    public void showAllTasks() {
        assertEquals(4,taskServiceImpl.showAllTasks().size());

    }

    @Test
    public void getTaskById() {
        assertEquals(Optional.ofNullable(task),taskServiceImpl.getTaskById(1));
    }

//    @Test
//    public void deleteTask() {
////        when(taskServiceImpl.deleteTask(4)).thenThrow();
////        assertTrue(taskServiceImpl.deleteTask(4));
//        taskServiceImpl.deleteTask(4);
//    }

//    @Test
//    public void updateTask() {
//
//
//    }

    @Test
    public void viewPendingTasks() {
        assertEquals(2,taskServiceImpl.viewPendingTasks().size());
    }

    @Test
    public void viewInProgressTasks() {
        assertEquals(1,taskServiceImpl.viewInProgressTasks().size());
    }

    @Test
    public void viewDoneTasks() {
        assertEquals(1,taskServiceImpl.viewDoneTasks().size());
    }
}