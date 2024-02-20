package com.api.todoList.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.todoList.models.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query( value = "SELECT * FROM Users ORDER BY id DESC", nativeQuery = true)
    List<User> getUsersReversed();

}
