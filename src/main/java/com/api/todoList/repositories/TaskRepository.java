package com.api.todoList.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.todoList.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long>{
    @Query(value="SELECT * FROM Tasks WHERE user_id = :userId",nativeQuery=true)
    List<Task> getTasksReversed(@Param("userId") Long userId);
}
