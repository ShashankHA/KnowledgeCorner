package com.learning.kc.service;


import com.learning.kc.model.User;
import com.learning.kc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    public User register(User user){
        return userRepository.save(user);
    }

    public boolean isUserExists(String name){
        Long count = userRepository.getCount(name);
        return count!=0;
    }

    public boolean doLogin(User user){
        String actaulPassword = userRepository.getPassword(user.getUserName());
        return actaulPassword.equals(user.getPassword());
    }

}
