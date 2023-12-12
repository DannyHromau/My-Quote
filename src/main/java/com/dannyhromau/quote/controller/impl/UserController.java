package com.dannyhromau.quote.controller.impl;

import com.dannyhromau.quote.api.dto.UserDto;
import com.dannyhromau.quote.model.User;
import com.dannyhromau.quote.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @PostMapping("/registration")
    public boolean createUser(@RequestBody UserDto userDTO) {
        User user = new User(userDTO.getLogin(), userDTO.getPassword(), userDTO.getEmail(), userDTO.getLocalDate());
        return userServiceImpl.saveUser(user);
    }

    @DeleteMapping("/accounts/{id}")
    public boolean deleteUser(@PathVariable int id){
        return userServiceImpl.deleteUser(id);
    }

    @PutMapping("/accounts/")
    public boolean updateUser(User user){
        return userServiceImpl.updateUser(user);
    }


}
