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
public class RegisterController {
    @Autowired
    UserService userService;

    @RequestMapping("/begin_register")
    public String beginRegister(@RequestParam("username") String username,
                                @RequestParam("password") String password,
                                HttpSession session,
                                Model model) {
        User user = userService.findUserbyName(username);
        if(user != null) {
            model.addAttribute("isExist","用户已存在，请更改用户名");
            return "register.html";
        }

        User PresentUser = new User(null,username,password,null);
        userService.saveUser(PresentUser);

        User userbyName = userService.findUserbyName(PresentUser.getUserName());
        session.setAttribute("PresentUser", userbyName);
        return "redirect:/";
    }
}
