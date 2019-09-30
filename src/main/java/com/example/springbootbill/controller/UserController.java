package com.example.springbootbill.controller;

import com.example.springbootbill.entities.User;
import com.example.springbootbill.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/userLogin")
    public String login(User user, Model model, HttpSession session){
        String username = user.getUsername();
        String password = user.getPassword();
        if(username == null && "".equals(username)){
            model.addAttribute("msg","用户名不能为空");
            return "main/login";
        }
        if (password == null && "".equals(password)){
            model.addAttribute("msg","密码不能为空");
            return "main/login";
        }
        User existUser = userMapper.getUserByUsernameAndPwd(user);
        if (existUser!=null){
            session.setAttribute("loginUser",existUser);
            return "redirect:/main.html";
        }
        model.addAttribute("msg","用户名和密码不正确");
        return "main/login";
    }

    @GetMapping("/users")
    public String getUsers(User user,Model model){
        List<User> list = userMapper.getUsers(user);
        model.addAttribute("list",list);
        return "user/list";
    }
    
    @GetMapping("/user/{id}")
    public String getUserById(@RequestParam(value = "type",defaultValue = "view")String type, @PathVariable("id")Integer id, Model model){
        User user = userMapper.getUserById(id);
        model.addAttribute("user",user);
        return "user/"+type;
    }

    @GetMapping("/user")
    public String toAddPage(){
        return "user/add";
    }

    @PostMapping("/user")
    public String addUser(User user) throws ParseException {
        userMapper.addUser(user);
        return "redirect:/users";
    }

    @PutMapping("/user")
    public String updateUser(User user){
        userMapper.updateUser(user);
        return "redirect:/users";
    }

    @DeleteMapping("/user/{id}")
    public String delUser(@PathVariable("id")Integer id){
        userMapper.deleteUser(id);
        return "redirect:/users";
    }

    @GetMapping("/user/pwd")
    public String toUpdatePwdPage(){
        return "main/password";
    }

    @GetMapping("/user/pwd/{oldPassword}")
    @ResponseBody
    public Boolean checkPwd(@PathVariable("oldPassword")String oldPassword , HttpSession session){
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser.getPassword().equals(oldPassword)){
            return true;
        }
        return false;
    }

    @RequestMapping("exit")
    public String exitUser(HttpSession session){
        session.removeAttribute("loginUser");
        session.invalidate();
        return "redirect:/index.html";
    }

    @PostMapping("/user/pwd")
    public String updatePwd(HttpSession session,String password){
        User loginUser = (User) session.getAttribute("loginUser");
        loginUser.setPassword(password);
        userMapper.updateUser(loginUser);
        return "redirect:/exit";
    }
}
