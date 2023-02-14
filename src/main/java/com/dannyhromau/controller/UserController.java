package com.dannyhromau.controller;

import com.dannyhromau.dto.UserDTO;
import com.dannyhromau.model.User;
import com.dannyhromau.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/registration")
    public boolean createUser(@RequestBody UserDTO userDTO) {
        User user = new User(userDTO.getLogin(), userDTO.getPassword(), userDTO.getEmail(), userDTO.getLocalDate());
        return userService.saveUser(user);
    }

    @DeleteMapping("/accounts/{id}")
    public boolean deleteUser(@PathVariable int id){
        return userService.deleteUser(id);
    }

    @PutMapping("/accounts/")
    public boolean updateUser(User user){
        return userService.updateUser(user);
    }


}
