package com.api.todoList.Requests;

import lombok.Data;

@Data
public class TaskRequest {
    
    private Long id;
    private String title;
    private String description;
    private Boolean completed;
    private Long userId;

}
