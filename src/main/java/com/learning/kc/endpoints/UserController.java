package com.learning.kc.endpoints;

import com.learning.kc.model.ResponseDTO;
import com.learning.kc.model.User;
import com.learning.kc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController("/user")
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> register(@Valid @RequestBody User user){
        ResponseDTO res = new ResponseDTO();
        if(userService.isUserExists(user.getUserName())){
            res.setCode("101");// 101 means user exists
            res.setResponse("User already exists");
            return new ResponseEntity<>(res, HttpStatus.CONFLICT);
        }
        else {
            User u = userService.register(user);
            res.setCode("200");
            res.setResponse(u);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }

    }

}
