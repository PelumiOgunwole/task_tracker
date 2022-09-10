package com.example.prismtasktracker.service;

import com.example.prismtasktracker.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    public Task addTask(Task task,String userNameId);
    public List<Task> showAllTasks();
    public Optional<Task> getTaskById(Integer id);

    public void deleteTask(Integer id);
    public void updateTask(Integer id);

    List<Task> viewPendingTasks();
    List<Task> viewInProgressTasks();
    List<Task> viewDoneTasks();
}
