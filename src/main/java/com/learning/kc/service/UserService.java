package com.learning.kc.service;


import com.learning.kc.model.SessionInfo;
import com.learning.kc.model.User;
import com.learning.kc.repository.SessionInfoRepository;
import com.learning.kc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SessionInfoRepository sessionInfoRepository;

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

    public String authorize(String sessionId){
        String username = sessionInfoRepository.getUsername(sessionId);
        if(username != null){
            return username;
        }
        return null;
    }

    public Cookie setCookie(String userName) {
        UUID sessionId = UUID.randomUUID();
        SessionInfo sessionInfo = new SessionInfo(userName,sessionId.toString());
        sessionInfoRepository.save(sessionInfo);
        Cookie cookie = new Cookie("sessionId",sessionId.toString());
        cookie.setMaxAge(10*60);  // 10 mins
        return cookie;
    }
}
