package com.example.demo.controller;

import com.example.demo.api.ReviewApi;
import com.example.demo.model.Review;
import com.example.demo.model.User;
import com.example.demo.staticUtility.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ReviewController {

    @Autowired
    ReviewApi reviewApi;

    @PostMapping("/write/{id}")
    public Review write(@PathVariable Long id, Review review, HttpSession httpSession){
        if(!SessionUtil.isLogin(httpSession)){
            review.setContent("비정상적인 접근입니다. 뒤로가기를 눌러주세요");
            review.setScore(0);
            return review;
        }
        User writer = (User)httpSession.getAttribute("loginUser");
        //List<Integer> stars = new ArrayList<Integer>();


        return reviewApi.writeReview(writer,review,id);
    }
}
