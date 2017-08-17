package com.example.demo.controller;

import com.example.demo.api.CartApi;
import com.example.demo.api.ItemApi;
import com.example.demo.api.MerchandiseApi;
import com.example.demo.api.UserApi;
import com.example.demo.model.Item;
import com.example.demo.model.Merchandise;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@Controller
public class ItemController {

    @Autowired
    ItemApi itemApi;

    @Autowired
    UserApi userApi;

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model, HttpSession httpSession){
        Item item = itemApi.findOne(id);
        model.addAttribute("item",item);

        userApi.setModelFromLoginUserSession(httpSession,model);

        item.setColorList();
        item.setSizeList();
        return "/merchandise/detail";
    }


}
