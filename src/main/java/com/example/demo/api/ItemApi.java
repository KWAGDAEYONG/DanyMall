package com.example.demo.api;

import com.example.demo.model.Item;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemApi {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public List<Item> findAll(){
        return itemRepository.findAll();
    }

    public Item findOne(Long id){
        return itemRepository.findOne(id);
    }

    public Item getDetail(Long id){
        Item item = itemRepository.findOne(id);
        item.setReviewStar();
        item.setColorList();
        item.setSizeList();
        item.setReviewCount();
        item.setAverage();
        return item;
    }

    public List<Item> getItemsByCategory(Long id){
        return itemRepository.findByCategory(categoryRepository.findOne(id));
    }

    public Item save(Item item){
        return itemRepository.save(item);
    }

    public List<Item> getNewArrivals(){
        return itemRepository.getNewArrivals();
    }

    public List<Item> search(String price, String weather, String style, String gender){
        return itemRepository.search(price, weather, style, gender);
    }
}
