package com.example.demo.api;

import com.example.demo.model.Item;
import com.example.demo.model.Review;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.CategoryRepository;
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

    @Autowired
    CategoryRepository categoryRepository;

    public List<Item> findAll(){
        return itempRepository.findAll();
    }

    public Item findOne(Long id){
        return itempRepository.findOne(id);
    }

    public Item getDetail(Long id){
        Item item = itempRepository.findOne(id);
        item.setReviewStar();
        item.setColorList();
        item.setSizeList();
        item.setReviewCount();
        item.setAverage();
        return item;
    }

    public List<Item> getItemsByCategory(Long id){
        return itempRepository.findByCategory(categoryRepository.findOne(id));
    }

    public Item save(Item item){
        return itempRepository.save(item);
    }

}
