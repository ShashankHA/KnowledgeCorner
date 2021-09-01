package com.learning.kc.endpoints;

import com.learning.kc.model.RegistrationInfo;
import com.learning.kc.model.ResponseDTO;
import com.learning.kc.model.User;
import com.learning.kc.service.UserService;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/ping")
    public String ping(){
        return "pong";
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> register(@Validated(RegistrationInfo.class) @RequestBody User user){
        ResponseDTO res = new ResponseDTO();
        if(userService.isUserExists(user.getUserName())){
            res.setCode("101");// 101 means user exists
            res.setResponse("User already exists");
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        else {
            User u = userService.register(user);
            res.setCode("103"); // Registration successfull
            res.setResponse(u);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }

    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody @Valid User user, HttpServletResponse response) {
        ResponseDTO res = new ResponseDTO();
        HttpStatus status = HttpStatus.OK ;
        if(!userService.isUserExists(user.getUserName())){
            res.setCode("102");// 102 means user does not exists
            res.setResponse("User does not exists");
            status = HttpStatus.OK;
        }
        else {
            boolean result = userService.doLogin(user);
            if(result) {
                res.setCode("103");// 103 means user logged in
                res.setResponse("User login successfull");
                status = HttpStatus.OK;
                Cookie cookie = userService.setCookie(user.getUserName());
                response.addCookie(cookie);
            }
            else{
                res.setCode("104");// 104 means login failure
                res.setResponse("User login failed");
                status = HttpStatus.OK;
            }
        }
        return new ResponseEntity<>(res, status);

    }

    @GetMapping("/getCookie")
    public String sendCookie(HttpServletResponse httpServletResponse){
        Cookie cookie = new Cookie("JSESSION", "1234567");
        cookie.setMaxAge(30);
        //add cookie to response
        httpServletResponse.addCookie(cookie);
        return "Cookie added";

    }

    @GetMapping("/authorizeUser")
    public ResponseDTO authorization(@CookieValue(value = "sessionId", required=false) String sessionId) {
        ResponseDTO res = new ResponseDTO();
        if(sessionId == null){
            res.setCode("100");  // unauthorized!
            res.setResponse("Unauthorized user");
        }
        else{
            String user = userService.authorize(sessionId);
            if(user!=null){
                res.setCode("200");
                res.setResponse("user");
            }
            else {
                res.setCode("100");  // unauthorized!
                res.setResponse("Unauthorized user");
            }
        }
        return res;
    }

}
