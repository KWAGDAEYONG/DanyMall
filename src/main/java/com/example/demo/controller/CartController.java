package com.example.demo.controller;

import com.example.demo.api.CartApi;
import com.example.demo.api.ItemApi;
import com.example.demo.api.UserApi;
import com.example.demo.model.Cart;
import com.example.demo.model.Item;
import com.example.demo.model.User;
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

    @GetMapping("/putCart/{id}")
    public String putCart(@PathVariable Long id, HttpSession httpSession){
        Item target = itemApi.findOne(id);
        User loginUser = (User)httpSession.getAttribute("loginUser");
        if(loginUser==null){
            return "redirect:/login";
        }
        cartApi.addItemIntoCart(loginUser.getCart(),target);
        return "redirect:/";
    }

    @GetMapping("/myCart/{id}")
    public String myCart(@PathVariable Long id, Model model, HttpSession httpSession){
        userApi.setModelFromLoginUserSession(httpSession,model);
        User loginUser = userApi.findOne(id);
        Cart cart = loginUser.getCart();
        model.addAttribute("carts",cart.getItem());
        return "/cart/myCart";
    }
}
