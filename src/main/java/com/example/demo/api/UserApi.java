package com.example.demo.api;

import com.example.demo.model.Cart;
import com.example.demo.model.Merchandise;
import com.example.demo.model.User;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

@Service
public class UserApi {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CartRepository cartRepository;

    public void signUp(User user){
        Cart cart = new Cart();
        user.setCart(cart);
        userRepository.save(user);
    }

    public String login(String userId, String password, HttpSession httpSession){
        String result = "";
        User dbUser = userRepository.findByUserId(userId);
        if(dbUser==null){
            result = "아이디가 없습니다.";
            return result;
        }

        if(!dbUser.isSamePassword(dbUser,password)){
            result = "비밀번호가 틀립니다";
            return result;
        }

        if(httpSession.getAttribute("loginUser")!=null){
            httpSession.removeAttribute("loginUser");
        }
        httpSession.setAttribute("loginUser",dbUser);
        result = "success";
        return result;
    }

    public void logout(HttpSession httpSession){
        httpSession.removeAttribute("loginUser");
    }

    public void setModelFromLoginUserSession(HttpSession httpSession, Model model){
        if(httpSession.getAttribute("loginUser")!=null) {
            model.addAttribute("isLogin", (User)httpSession.getAttribute("loginUser"));
        }
    }

    public User findOne(Long id){
        return userRepository.findOne(id);
    }

}
