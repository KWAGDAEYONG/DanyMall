package com.example.demo.controller;

import com.example.demo.api.*;
import com.example.demo.model.Category;
import com.example.demo.model.Item;
import com.example.demo.model.User;
import com.example.demo.staticUtility.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    ItemApi itemApi;

    @Autowired
    UserApi userApi;

    @Autowired
    CommonApi commonApi;

    @GetMapping("/")
    public String home(HttpSession httpSession, Model model){
        commonApi.setCommonModel(httpSession,model);
        model.addAttribute("item", itemApi.findAll());
        List<Item> dd =itemApi.getNewArrivals();
        model.addAttribute("newArrivals",dd);
        return "/index";
    }



}
