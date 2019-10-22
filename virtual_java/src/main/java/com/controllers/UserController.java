package com.controllers;

import com.common.ServiceResponse;
import com.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user/")
public class UserController {

    @Autowired
    UserService userService;
//    登录
    @PostMapping("login.do")
    public ServiceResponse login(String username,String password){
        ServiceResponse login = userService.login(username, password);
        return null;
    }

}
