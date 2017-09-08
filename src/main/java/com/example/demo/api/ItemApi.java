package com.example.demo.api;

import com.example.demo.criteria.PageCriteria;
import com.example.demo.criteria.SearchCriteria;
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

    public Item save(Item item){
        return itemRepository.save(item);
    }

    public List<Item> getNewArrivals(){
        return itemRepository.getNewArrivals();
    }

    public List<Item> search(SearchCriteria searchCriteria){
        return itemRepository.search(searchCriteria);
    }

    public Long getTotalCount(SearchCriteria searchCriteria){
        return itemRepository.getTotalCount(searchCriteria);
    }

}
