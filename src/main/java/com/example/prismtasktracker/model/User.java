package com.example.prismtasktracker.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table
public class User {
    @Id
    @Column(unique = true,length = 256 ,nullable = false)
    private String userName;

    @Column(nullable = false)
    private  String name;

    @Column(length = 256)
    private String password;

    @Column(length = 256)
    private String c_password;

    public User() {
    }

    public User(String userName, String name, String password, String c_password) {
        this.userName = userName;
        this.name = name;
        this.password = password;
        this.c_password = c_password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getC_password() {
        return c_password;
    }

    public void setC_password(String c_password) {
        this.c_password = c_password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", c_password='" + c_password + '\'' +
                '}';
    }
}
