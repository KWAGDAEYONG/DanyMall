package com.example.demo.api;

import com.example.demo.model.User;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.staticUtility.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

@Service
public class CommonApi {

    @Autowired
    CategoryRepository categoryRepository;

    public void setCommonModel(HttpSession httpSession, Model model){
        if(SessionUtil.isLogin(httpSession)) {
            model.addAttribute("isLogin", (User)httpSession.getAttribute("loginUser"));
        }
        model.addAttribute("category",categoryRepository.findAll());
    }
}
