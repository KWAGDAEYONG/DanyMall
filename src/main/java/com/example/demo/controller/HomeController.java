package com.example.demo.controller;

import com.example.demo.api.MerchandiseApi;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    MerchandiseApi merchandiseApi;

    @GetMapping("/")
    public String home(HttpSession httpSession, Model model){

        if(httpSession.getAttribute("loginUser")!=null) {
            model.addAttribute("isLogin", (User)httpSession.getAttribute("loginUser"));
        }
        model.addAttribute("merchandise", merchandiseApi.findAll());

        return "/index";
    }
    @GetMapping("/login")
    public String login(){
        return "/user/login";
    }
    @GetMapping("/signup")
    public String signUp(){
        return "/user/signup";
    }
}
