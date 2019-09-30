package com.example.springbootbill.controller;


import com.example.springbootbill.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @PostMapping("/login")
    public String login(User user, Model model, HttpSession session){
        String username = user.getUsername();
        String password = user.getPassword();
        if ("tom".equals(username) && "123".equals(password)){
            session.setAttribute("loginUser" , user);
            return "redirect:/main.html";
        }
        model.addAttribute("msg","用户名和密码不正确");
        return "main/login";
    }



}
