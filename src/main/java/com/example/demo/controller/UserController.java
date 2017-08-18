package com.example.demo.controller;

import com.example.demo.api.CommonApi;
import com.example.demo.api.UserApi;
import com.example.demo.model.User;
import com.example.demo.staticUtility.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    UserApi userApi;

    @Autowired
    CommonApi commonApi;

    @PostMapping("/login")
    public String loginProcess(String userId, String password, HttpSession httpSession, Model model){
        String result = userApi.login(userId, password, httpSession);
        if(!"success".equals(result)) {
            commonApi.setCommonModel(httpSession,model);
            model.addAttribute("loginFail",result);
            return "/user/login";
        }
        return "redirect:/";

    }

    @PostMapping("/modifyUserInfo")
    public String modifyUserInfo(String userId, String password, Model model, HttpSession httpSession){
        User dbUser = userApi.findByUserId(userId);
        commonApi.setCommonModel(httpSession,model);
        if(!SessionUtil.matchLoginUser(httpSession,dbUser)){
            model.addAttribute("loginFail","현재 로그인된 정보가 맞지 않습니다. 다시 로그인 해주세요");
            return "/user/login";
        }
        if(dbUser==null||!dbUser.isSamePassword(password)){
            model.addAttribute("loginBeforeModifyFail","입력하신 정보가 일치하지 않습니다.");
            return "/user/loginBeforeModify";
        }
        model.addAttribute("modifyUser",dbUser);
        return "/user/modify";
    }
    @GetMapping("/loginBeforeModify")
    public String loginBeforeModify(HttpSession httpSession,Model model){
        commonApi.setCommonModel(httpSession,model);
        return "/user/loginBeforeModify";
    }

    @PostMapping("/modify/{id}")
    public String modify(@PathVariable Long id, User user, HttpSession httpSession, Model model){
        commonApi.setCommonModel(httpSession, model);
        if(!SessionUtil.isLogin(httpSession)){
            return "/user/login";
        }
        if(!userApi.modify(id, user, httpSession)){
            model.addAttribute("loginFail","현재 로그인된 정보가 맞지 않습니다. 다시 로그인 해주세요");
            return "/user/login";
        }
        return "redirect:/";
    }

    @PostMapping("/signupProcess")
    public String signupProcess(User user){
        userApi.signUp(user);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession){
        userApi.logout(httpSession);
        return "redirect:/";
    }
    @GetMapping("/login")
    public String login(HttpSession httpSession, Model model){
        commonApi.setCommonModel(httpSession,model);
        return "/user/login";
    }

    @GetMapping("/signup")
    public String signUp(HttpSession httpSession, Model model){
        commonApi.setCommonModel(httpSession,model);
        return "/user/signup";
    }

}
