package com.api.todoList.models;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    @Value("${completed:false}")
    private Boolean completed;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "userId",referencedColumnName = "id")
    private User user;

    public Long getUserId() {
        if (user != null) {
            return user.getId();
        } else {
            return null;
        }
    }
    
    public void setUserId(Long userId) {
        if (user == null) {
            user = new User();
        }
        user.setId(userId);
    }
    
}
