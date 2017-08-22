package com.example.demo.controller;

import com.example.demo.api.ReviewApi;
import com.example.demo.model.Review;
import com.example.demo.model.User;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class ReviewController {

    @Autowired
    ReviewApi reviewApi;

    @PostMapping("/write/{id}")
    public Review write(@PathVariable Long id, Review review, HttpSession httpSession){
        User writer = (User)httpSession.getAttribute("loginUser");
        return reviewApi.writeReview(writer,review,id);
    }
}
