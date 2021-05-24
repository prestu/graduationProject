package com.prestu.controller;

import com.prestu.pojo.User;
import com.prestu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user1")

public class UserController {

    @Autowired
    UserService userService;
    @RequestMapping("/update")
    public String update(User user)
    {
        boolean update = userService.update(user);
        return "ok";
    }

}
