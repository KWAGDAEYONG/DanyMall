package com.example.demo.repository;

import com.example.demo.criteria.PageCriteria;
import com.example.demo.criteria.SearchCriteria;
import com.example.demo.model.Item;

import java.util.List;

public interface ItemRepositoryQueryDsl {
    List<Item> getNewArrivals();
    List<Item> search(SearchCriteria searchCriteria);
    Long getTotalCount(SearchCriteria searchCriteria);
}
