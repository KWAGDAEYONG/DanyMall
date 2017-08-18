package com.example.demo.staticUtility;

import com.example.demo.model.User;

import javax.servlet.http.HttpSession;

public class SessionUtil {
    private static final String SESSION_KEY = "loginUser";

    public static boolean isLogin(HttpSession httpSession){
        return httpSession.getAttribute(SESSION_KEY)!=null;
    }

    public static boolean matchLoginUser(HttpSession httpSession, User user){
        return user.getUserId().equals(((User)httpSession.getAttribute(SESSION_KEY)).getUserId());
    }
}
