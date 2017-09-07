package com.example.demo.api;

import com.example.demo.model.Cart;
import com.example.demo.model.User;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ItemRepository;
import com.example.demo.staticUtility.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

@Service
public class CommonApi {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ItemRepository itemRepository;

    public void setCommonModel(HttpSession httpSession, Model model){
        User user =(User)httpSession.getAttribute("loginUser");
        model.addAttribute("category",categoryRepository.findAll());
        Cart cart = cartRepository.findByUser(user);
        if(cart!=null){
            cart.setCount();
            model.addAttribute("cartCount",cart.getCount());
        }
        model.addAttribute("cartList", itemRepository.findByCart(cart));
        if(SessionUtil.isLogin(httpSession)) {
            model.addAttribute("isLogin", user);
        }

    }
}
