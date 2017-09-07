package com.example.demo.repository.impl;

import com.example.demo.model.Item;
import com.example.demo.model.QItem;
import com.example.demo.repository.ItemRepositoryQueryDsl;
import com.querydsl.core.BooleanBuilder;
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
    public List<Item> search(String price, String weather, String style, String gender){
        QItem qItem =QItem.item;
        BooleanBuilder br = new BooleanBuilder();
        if(!"".equals(price)) {
            price = price.trim();
            price = price.replace(" ", "");

            String between[] = price.split("-");
            between[0] = between[0].substring(1);
            between[1] = between[1].substring(1);
            br.and(qItem.price.between(Integer.parseInt(between[0]),Integer.parseInt(between[1])));
        }

        if(!"".equals(weather)){
            br.and(qItem.weather.eq(weather));
        }

        if(!"".equals(style)){
            br.and(qItem.style.eq(style));
        }

        if(!"".equals(gender)){
            br.and(qItem.gender.eq(gender));
        }
        return from(qItem).where(br).fetch();
    }
}
