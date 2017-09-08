package com.example.demo.repository.impl;

import com.example.demo.criteria.PageCriteria;
import com.example.demo.criteria.SearchCriteria;
import com.example.demo.model.Item;
import com.example.demo.model.QItem;
import com.example.demo.repository.ItemRepositoryQueryDsl;
import org.h2.store.Page;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemRepositoryImpl extends QueryDslRepositorySupport implements ItemRepositoryQueryDsl {
    public ItemRepositoryImpl(){
        super(Item.class);
    }

    @Override
    public List<Item> getNewArrivals(){
        QItem qItem =QItem.item;
        return from(qItem).orderBy(qItem.id.desc()).limit(4).fetch();
    }

    @Override
    public List<Item> search(SearchCriteria searchCriteria){
        QItem qItem =QItem.item;
        return from(qItem)
                .where(searchCriteria.getWhere())
                .offset(searchCriteria.getPageStart())
                .limit(searchCriteria.getPerPageNum())
                .fetch();
    }

    @Override
    public Long getTotalCount(SearchCriteria searchCriteria){
        QItem qItem =QItem.item;
        return from(qItem)
                .where(searchCriteria.getWhere())
                .offset(searchCriteria.getPageStart())
                .limit(searchCriteria.getPerPageNum())
                .fetchCount();
    }

}
