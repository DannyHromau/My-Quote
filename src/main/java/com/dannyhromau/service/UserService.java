package com.dannyhromau.service;

import com.dannyhromau.model.User;
import com.dannyhromau.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

//    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public boolean saveUser(User user){

//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        boolean isSaved = userRepository.findByLogin(user.getEmail()) == null;
        if (isSaved) {
            userRepository.save(user);
        }
        return isSaved;

    }

    public boolean updateUser(User updatedUser){
        User user;
        boolean isUpdated = false;
        Optional<User> userOpt = userRepository.findById(updatedUser.getId());
        if (userOpt.isPresent()){
            user = userOpt.get();
            user.setLogin(updatedUser.getLogin());
            user.setEmail(updatedUser.getEmail());
            userRepository.save(user);
            isUpdated = true;
        }
        return isUpdated;
    }

    public boolean deleteUser(int id){
        boolean isDeleted = false;
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()){
            userRepository.deleteById(id);
            isDeleted = true;
        }
        return isDeleted;
    }

    public User getUserByLogin(String login){
        return userRepository.findByLogin(login);
    }

    public List<User> getUsers(){
        List<User> users = new ArrayList<>();
        for (User user : userRepository.findAll()){
            users.add(user);
        }
        return users;
    }
}
