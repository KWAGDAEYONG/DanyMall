package com.example.demo.api;

import com.example.demo.model.Sold;
import com.example.demo.repository.SoldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoldApi {

    @Autowired
    SoldRepository soldRepository;

    public List<Sold> getSoldMerchandiseList(){
        return soldRepository.findAll();
    }

    public Sold getSold(Long id){
        return soldRepository.findOne(id);
    }

    public Sold save(Sold sold){
        return soldRepository.save(sold);
    }
}
