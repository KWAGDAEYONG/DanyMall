package com.example.demo.api;

import com.example.demo.model.Review;
import com.example.demo.model.User;
import com.example.demo.repository.ItempRepository;
import com.example.demo.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewApi {

    @Autowired
    ItempRepository itempRepository;

    @Autowired
    ReviewRepository reviewRepository;

    public Review writeReview(User writer, Review review, Long itemId){
        review.setWriter(writer);
        review.setItem(itempRepository.findOne(itemId));
        System.out.println(review);
        return reviewRepository.save(review);
    }
}
