package com.example.demo.repository;

import com.example.demo.model.Item;

import java.util.List;

public interface ItemRepositoryQueryDsl {
    List<Item> getNewArrivals();
    List<Item> search(String price, String weather, String style, String gender);
}
