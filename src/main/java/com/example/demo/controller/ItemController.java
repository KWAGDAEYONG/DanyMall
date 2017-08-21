package com.example.demo.controller;

import com.example.demo.api.*;
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
    CommonApi commonApi;

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model, HttpSession httpSession,String buyResult){
        model.addAttribute("item",itemApi.getDetail(id));
        if(!"".equals(buyResult)) {
            model.addAttribute("buyResult", buyResult);
        }
        commonApi.setCommonModel(httpSession,model);
        return "/merchandise/detail";
    }
}
