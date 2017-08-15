package com.example.demo.controller;

import com.example.demo.api.UserApi;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    UserApi userApi;

    @PostMapping("/login")
    public String loginProcess(String userId, String password, HttpSession httpSession, Model model){
        String result = userApi.login(userId, password, httpSession);
        if(!"success".equals(result)) {
            model.addAttribute("isFail",result);
            return "/user/login";
        }

        return "redirect:/";

    }

    @PostMapping("/signupProcess")
    public String signupProcess(User user){
        userApi.signUp(user);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession){
        userApi.logout(httpSession);
        return "redirect:/";
    }
}
