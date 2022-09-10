package com.example.prismtasktracker.repository;

import com.example.prismtasktracker.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Integer> {
    @Query(
        value = "SELECT * from task   WHERE status='Pending'",
        nativeQuery = true
    )
    List<Task> findPendingTaskByStatus();

    @Query(
            value = "SELECT * from task   WHERE status='In_Progress'",
            nativeQuery = true
    )
    List<Task> findInProgressTaskByStatus();

    @Query(
            value = "SELECT * from task   WHERE status='Done'",
            nativeQuery = true
    )
    List<Task> findDoneTaskByStatus();

}
