package com.example.demo.api;

import com.example.demo.model.Cart;
import com.example.demo.model.User;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.staticUtility.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class UserApi {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CartRepository cartRepository;

    public User signUp(User user){
        Cart cart = new Cart();
        user.setCart(cart);
        cart.setUser(user);
        return userRepository.save(user);
    }

    public boolean modify(Long id, User user, HttpSession httpSession){

        User dbUser = userRepository.findOne(id);
        if(!SessionUtil.matchLoginUser(httpSession,dbUser)){
            return false;
        }
        dbUser.update(user);
        userRepository.save(user);
        return true;
    }

    public String login(String userId, String password, HttpSession httpSession){
        User dbUser = userRepository.findByUserId(userId);
        if(dbUser==null){
            return "아이디가 없습니다.";
        }

        if(!dbUser.isSamePassword(password)){
            return "비밀번호가 틀립니다";
        }

        if(SessionUtil.isLogin(httpSession)){
            httpSession.removeAttribute("loginUser");
        }
        httpSession.setAttribute("loginUser",dbUser);
        return "success";
    }
    public void logout(HttpSession httpSession){
        httpSession.removeAttribute("loginUser");
    }


    public User findOne(Long id){
        return userRepository.findOne(id);
    }

    public User findByUserId(String userId){
        return userRepository.findByUserId(userId);
    }

}
