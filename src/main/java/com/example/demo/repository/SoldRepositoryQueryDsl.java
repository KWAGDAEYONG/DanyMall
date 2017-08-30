package com.example.demo.repository;

import com.example.demo.model.Sold;

import java.sql.Date;
import java.util.List;

public interface SoldRepositoryQueryDsl {
    List<Sold> getSoldByDate(Date date1, Date date2);
}
