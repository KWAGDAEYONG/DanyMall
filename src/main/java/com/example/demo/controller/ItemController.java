package com.example.demo.controller;

import com.example.demo.api.*;
import com.example.demo.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ItemController {

    @Autowired
    ItemApi itemApi;

    @Autowired
    CommonApi commonApi;

    @Autowired
    MerchandiseApi merchandiseApi;

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model, HttpSession httpSession,String buyResult){
        model.addAttribute("item",itemApi.getDetail(id));
        if(!"".equals(buyResult)) {
            model.addAttribute("buyResult", buyResult);
        }
        commonApi.setCommonModel(httpSession,model);
        return "/merchandise/detail";
    }

    @GetMapping("/{id}")
    public String category(@PathVariable Long id, Model model, HttpSession httpSession){
        commonApi.setCommonModel(httpSession, model);
        List<Item> items = id==2290? itemApi.findAll():itemApi.getItemsByCategory(id);
        for(Item item:items){
            item.setReviewStar();
            item.setReviewCount();
            item.setAverage();
        }
        model.addAttribute("categoryItem",items);

        return "/merchandise/merchandises";
    }

    @GetMapping("/search")
    public String searchItem(String price, String weather, String style, String gender, Model model){
        model.addAttribute("categoryItem",itemApi.search(price, weather, style, gender));
        return "/merchandise/merchandises";
    }
}
