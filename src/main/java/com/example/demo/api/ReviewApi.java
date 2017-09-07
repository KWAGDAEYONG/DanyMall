package com.example.demo.api;

import com.example.demo.model.Review;
import com.example.demo.model.User;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewApi {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ReviewRepository reviewRepository;

    public Review writeReview(User writer, Review review, Long itemId){
        review.setWriter(writer);
        review.setItem(itemRepository.findOne(itemId));
        review.setStar();
        return reviewRepository.save(review);
    }
}
