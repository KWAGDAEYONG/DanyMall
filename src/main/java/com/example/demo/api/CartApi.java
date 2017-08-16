package com.example.demo.api;


import com.example.demo.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartApi {


    @Autowired
    CartRepository cartRepository;


}
