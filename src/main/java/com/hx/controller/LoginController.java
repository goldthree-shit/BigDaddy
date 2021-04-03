package com.hx.controller;

import com.hx.pojo.User;
import com.hx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @RequestMapping("/begin_login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpSession session,
                        Model model) {

        User user = userService.findUserbyName(username);
        if (user == null) {
            session.setAttribute("message", "用户不存在");
            model.addAttribute("message", "用户不存在");
            return "login.html";
        } else if(!password.equals(user.getPassword())) {
            session.setAttribute("message", "用户名或者密码错误");
            model.addAttribute("message", "用户名或者密码错误");
            return "login.html";
        }
        session.setAttribute("PresentUser", user);
        return "redirect:/";
    }
}
