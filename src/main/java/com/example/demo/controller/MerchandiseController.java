package com.example.demo.controller;

import com.example.demo.api.ItemApi;
import com.example.demo.api.MerchandiseApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MerchandiseController {

    @Autowired
    MerchandiseApi merchandiseApi;

    @Autowired
    ItemApi itemApi;

    @GetMapping("/buy/{id}")
    public String buy(@PathVariable Long id){
        System.out.println(merchandiseApi.findByItem(itemApi.findOne(id)));
        return "redirect:/";
    }
}
