package com.example.demo.repository.impl;

import com.example.demo.model.QSold;
import com.example.demo.model.Sold;
import com.example.demo.repository.SoldRepositoryQueryDsl;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class SoldRepositoryImpl extends QueryDslRepositorySupport implements SoldRepositoryQueryDsl {
    public SoldRepositoryImpl(){
        super(Sold.class);
    }

    @Override
    public List<Sold> getSoldByDate(Date date1, Date date2){

        QSold qSold = QSold.sold;
        return from(qSold).where(qSold.soldDate.between(date1,date2)).fetch();
    }
}
