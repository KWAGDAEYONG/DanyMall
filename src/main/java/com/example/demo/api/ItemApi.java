package com.example.demo.api;

import com.example.demo.model.Cart;
import com.example.demo.model.Item;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.ItempRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemApi {

    @Autowired
    ItempRepository itempRepository;

    @Autowired
    CartRepository cartRepository;

    public List<Item> findAll(){
        return itempRepository.findAll();
    }

    public Item findOne(Long id){
        return itempRepository.findOne(id);
    }


}
