package com.prestu.controller;

import com.prestu.pojo.User;
import com.prestu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author 范成恒
 */
@Controller
@RequestMapping("/user")
public class LoginController {

    private String msg;
    @Autowired
    UserService userService;

    @RequestMapping("/index")
    public String index(@RequestParam(value = "username",required = false) String username, @RequestParam(value = "password" ,required = false) String password, Model model, HttpSession session) {
        String name = username;
        if (name==null) {
            name = "-1";
        }
        User byName = userService.findByName(name);
        if ((byName!=null && byName.getPassword().equals(password))) {
            assert byName != null;
            session.setAttribute("number",byName.getNumber());
            session.setAttribute("username",username);
            session.setAttribute("password",password);
            session.setAttribute("id",byName.getId());
            session.setAttribute("number",byName.getNumber());
            session.setAttribute("sex",byName.getSex());
            session.setAttribute("email",byName.getEmail());
            return "index";
        }

        if(password == null && username==null) {
            msg = "不好意思，您未登录，请您先登录!!!";
        }
        else {
            msg = "用户名或密码错误，请重新登录!!! ";
        }
        model.addAttribute("msg",msg);
        return "login";
    }

    @RequestMapping("/register")
    public String register(User user ,Model model) {
        User byName = userService.findByName(user.getUsername());
        if (byName!=null) {
            msg = "用户名已存在，请重新注册!!!";
            model.addAttribute("msg",msg);
            return "/register";
        }
        boolean add = userService.add(user);
        msg = "恭喜注册成功，请登录!!!";
        model.addAttribute("msg",msg);
        return "login";
    }
    @RequestMapping("/logout")
    public void signOut(HttpServletRequest request ,HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("loginUser");
        request.getRequestDispatcher("/user/login").forward(request,response);
        System.out.println("退出登录!!!");
    }


}
