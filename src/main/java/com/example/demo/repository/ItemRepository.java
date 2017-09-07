package com.example.demo.repository;

import com.example.demo.model.Cart;
import com.example.demo.model.Category;
import com.example.demo.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item,Long>, ItemRepositoryQueryDsl {
    List<Item> findByCategory(Category category);
    List<Item> findByCart(Cart cart);
}
