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
        commonApi.setCommonModel(httpSession,model);
        Item target = itemApi.findOne(id);
        User loginUser = (User)httpSession.getAttribute("loginUser");
        if(!SessionUtil.isLogin(httpSession)){
            model.addAttribute("loginFail","로그인을 해주세요");
            return "/user/login";
        }
        if(!SessionUtil.matchLoginUser(httpSession,loginUser)){
            model.addAttribute("loginFail","현재 로그인된 정보가 맞지 않습니다. 다시 로그인 해주세요");
            return "/user/login";
        }
        String result = cartApi.addItemIntoCart(loginUser,target);
        if(!"success".equals(result)){
            model.addAttribute("putCart",result);
            return "/error";
        }
        return "redirect:/myCart/"+loginUser.getId();
    }

    @GetMapping("/myCart/{id}")
    public String myCart(@PathVariable Long id, Model model, HttpSession httpSession){
        commonApi.setCommonModel(httpSession,model);
        User user = userApi.findOne(id);
        if(!SessionUtil.isLogin(httpSession)){
            model.addAttribute("loginFail","로그인을 해주세요");
            return "/user/login";
        }
        if(!SessionUtil.matchLoginUser(httpSession,user)){
           model.addAttribute("loginFail","현재 로그인된 정보가 맞지 않습니다. 다시 로그인 해주세요");
            return "/user/login";
        }

        model.addAttribute("carts",cartApi.getMyCartList(user));
        return "/cart/myCart";
    }

    @GetMapping("/removeCart/{id}")
    public String removeCart(@PathVariable Long id, HttpSession httpSession, Model model){
        User user = (User)httpSession.getAttribute("loginUser");
        cartApi.removeCart(user,itemApi.findOne(id));
        return "redirect:/myCart/"+user.getId();
    }
}
