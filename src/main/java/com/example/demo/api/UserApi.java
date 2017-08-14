package com.example.demo.api;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class UserApi {

    @Autowired
    UserRepository userRepository;

    public void signUp(User user){
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


}
