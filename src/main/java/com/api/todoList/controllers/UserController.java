package com.api.todoList.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.api.todoList.models.User;
import com.api.todoList.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public User createUser(@RequestBody User user){
        userRepository.save(user);
        return user;
    }

    @GetMapping
    public List<User> getUsers() throws Exception{
        List<User> users = userRepository.findAll();
        if(users != null){
            return users;
        }else{
            throw new Exception("Lista de usuarios vacía");
        }
    }

    @GetMapping("/reversed")
    public List<User> getUsersReversed() {
        return userRepository.getUsersReversed();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) throws Exception{
        Optional<User> user = userRepository.findById(id);
        if(user != null){
            return user.get();
        }else{
            throw new Exception("User not found");
        }
    }

    @PutMapping("/edit/{id}")
    public User editUser(@RequestBody User user,@PathVariable Long id){
        user.setId(id);
        return userRepository.save(user);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id){
        Optional<User> user = userRepository.findById(id);
        if(user != null){
            userRepository.deleteById(id);
            return "User deleted successfully";
        }else{
            return "User with id: " + id + " not found";
        }
    }
    
}
