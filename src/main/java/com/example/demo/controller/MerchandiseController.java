package com.example.demo.controller;

import com.example.demo.api.MerchandiseApi;
import com.example.demo.model.Merchandise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MerchandiseController {

    @Autowired
    MerchandiseApi merchandiseApi;

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model){
        Merchandise merchandise = merchandiseApi.findOne(id);
        model.addAttribute("merchandise",merchandise);
        return "/merchandise/detail";
    }
}
