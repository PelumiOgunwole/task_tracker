package com.example.prismtasktracker.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "task")

public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String task_name;

    @Column(nullable = false)
    private String task_desc;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate creation_date;

//    @Column(nullable = false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate update_date;

//    @Column(nullable = false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate completion_date;

    @ManyToOne(fetch = FetchType.LAZY)
// FetchType.LAZY allows deleting of foreign keys withour a constraint error
    @JoinColumn(name = "user_who_adds",referencedColumnName = "userName")
    private User user;

    public Task() {
    }

    public Task(Integer id, String task_name, String task_desc, String status, LocalDate creation_date, LocalDate update_date, LocalDate completion_date, User user) {
        this.id = id;
        this.task_name = task_name;
        this.task_desc = task_desc;
        this.status = status;
        this.creation_date = creation_date;
        this.update_date = update_date;
        this.completion_date = completion_date;
        this.user = user;
    }

    public Task(Integer id, String task_name, String task_desc, String status, LocalDate creation_date, User user) {
        this.id = id;
        this.task_name = task_name;
        this.task_desc = task_desc;
        this.status = status;
        this.creation_date = creation_date;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getTask_desc() {
        return task_desc;
    }

    public void setTask_desc(String task_desc) {
        this.task_desc = task_desc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(LocalDate creation_date) {
        this.creation_date = creation_date;
    }

    public LocalDate getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(LocalDate update_date) {
        this.update_date = update_date;
    }

    public LocalDate getCompletion_date() {
        return completion_date;
    }

    public void setCompletion_date(LocalDate completion_date) {
        this.completion_date = completion_date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", task_name='" + task_name + '\'' +
                ", task_desc='" + task_desc + '\'' +
                ", status='" + status + '\'' +
                ", creation_date=" + creation_date +
                ", update_date=" + update_date +
                ", completion_date=" + completion_date +
                ", user=" + user +
                '}';
    }
}
