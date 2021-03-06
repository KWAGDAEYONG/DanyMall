package com.example.demo.controller;

import com.example.demo.api.CommonApi;
import com.example.demo.api.ItemApi;
import com.example.demo.api.MerchandiseApi;
import com.example.demo.model.Merchandise;
import com.example.demo.model.Sold;
import com.example.demo.staticUtility.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
@Controller
public class MerchandiseController {

    @Autowired
    MerchandiseApi merchandiseApi;

    @Autowired
    ItemApi itemApi;

    @Autowired
    CommonApi commonApi;


    @GetMapping("/buy/{id}")
    public String buy(@PathVariable Long id, String color, String size,int qty ,Model model, HttpSession httpSession, RedirectAttributes redirectAttributes){
        commonApi.setCommonModel(httpSession,model);
        if(!SessionUtil.isLogin(httpSession)){
            return "redirect:/login";
        }
        if("".equals(color)||"".equals(size)){
            redirectAttributes.addAttribute("buyResult","칼라와 사이즈를 선택해주세요");
            return "redirect:/detail/"+id;
        }
        Merchandise target = merchandiseApi.getBuyTarget(id, color,size);

        if (target == null  || !target.release(qty)) {
            model.addAttribute("buyFail","재고가 없습니다.");
            return "/merchandise/order-complete";
        }

        model.addAttribute("sold",merchandiseApi.buy(target, httpSession, qty));

        return "/merchandise/order-complete";
    }
}
