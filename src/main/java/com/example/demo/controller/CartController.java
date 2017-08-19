package com.example.demo.controller;

import com.example.demo.api.CartApi;
import com.example.demo.api.CommonApi;
import com.example.demo.api.ItemApi;
import com.example.demo.api.UserApi;
import com.example.demo.model.Cart;
import com.example.demo.model.Item;
import com.example.demo.model.User;
import com.example.demo.staticUtility.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@Controller
public class CartController {

    @Autowired
    ItemApi itemApi;

    @Autowired
    CartApi cartApi;

    @Autowired
    UserApi userApi;

    @Autowired
    CommonApi commonApi;

    @GetMapping("/putCart/{id}")
    public String putCart(@PathVariable Long id, HttpSession httpSession,Model model){
        Item target = itemApi.findOne(id);
        User loginUser = (User)httpSession.getAttribute("loginUser");
        if(!SessionUtil.isLogin(httpSession)){
            commonApi.setCommonModel(httpSession,model);
            model.addAttribute("loginFail","로그인을 해주세요");
            return "/user/login";
        }
        cartApi.addItemIntoCart(loginUser.getCart(),target);
        return "redirect:/";
    }

    @GetMapping("/myCart/{id}")
    public String myCart(@PathVariable Long id, Model model, HttpSession httpSession){
        commonApi.setCommonModel(httpSession,model);
        User loginUser = userApi.findOne(id);
        if(!SessionUtil.isLogin(httpSession)){
            commonApi.setCommonModel(httpSession,model);
            model.addAttribute("loginFail","로그인을 해주세요");
            return "/user/login";
        }
        if(!SessionUtil.matchLoginUser(httpSession,loginUser)){
            commonApi.setCommonModel(httpSession,model);
            model.addAttribute("loginFail","현재 로그인된 정보가 맞지 않습니다. 다시 로그인 해주세요");
            return "/user/login";
        }
        model.addAttribute("carts",loginUser.getCart().getItem());
        return "/cart/myCart";
    }
}