package com.example.demo.controller;

import com.example.demo.api.CartApi;
import com.example.demo.api.MerchandiseApi;
import com.example.demo.api.UserApi;
import com.example.demo.model.Merchandise;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@Controller
public class MerchandiseController {

    @Autowired
    MerchandiseApi merchandiseApi;

    @Autowired
    CartApi cartApi;

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model){
        Merchandise merchandise = merchandiseApi.findOne(id);
        model.addAttribute("merchandise",merchandise);
        merchandise.setColorList();
        merchandise.setSizeList();
        return "/merchandise/detail";
    }

    @GetMapping("/putCart/{id}")
    public String putCart(@PathVariable Long id, HttpSession httpSession){
        Merchandise target = merchandiseApi.findOne(id);
        User loginUser = (User)httpSession.getAttribute("loginUser");
        if(loginUser==null){
            return "redirect:/login";
        }
        merchandiseApi.addMerchandiseIntoCart(loginUser.getCart(),target);
        return "redirect:/";
    }
}
