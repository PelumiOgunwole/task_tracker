package com.example.prismtasktracker.service;

import com.example.prismtasktracker.exceptions.UserNotFoundException;
import com.example.prismtasktracker.model.Task;
import com.example.prismtasktracker.model.User;
import com.example.prismtasktracker.repository.TaskRepository;
import com.example.prismtasktracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService{
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Task addTask(Task task,String userNameId) {

        Optional<User> user = userRepository.findById(userNameId);
        if(user.isPresent()) {
            // get the User Object
            task.setUser(user.get());

            return taskRepository.save(task);
        }
        else throw new UserNotFoundException("User not found");
    }

    @Override
    public List<Task> showAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Optional<Task> getTaskById(Integer id) {
        return taskRepository.findById(id);
    }

    @Override
    public void deleteTask(Integer id) {
        taskRepository.deleteById(id);
    }

    @Override
    public void updateTask(Integer id) {
        taskRepository.findById(id);

    }

    @Override
    public List<Task> viewPendingTasks(){
        return taskRepository.findPendingTaskByStatus();
    }

    @Override
    public List<Task> viewInProgressTasks() {
        return taskRepository.findInProgressTaskByStatus();
    }

    @Override
    public List<Task> viewDoneTasks() {
        return taskRepository.findDoneTaskByStatus();
    }

}
