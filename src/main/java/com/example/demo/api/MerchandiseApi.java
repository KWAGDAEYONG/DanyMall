package com.example.demo.api;

import com.example.demo.model.Merchandise;
import com.example.demo.repository.MerchandiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchandiseApi {

    @Autowired
    MerchandiseRepository merchandiseRepository;

    public List<Merchandise> findAll(){
        return merchandiseRepository.findAll();
    }

    public Merchandise findOne(Long id){
        return merchandiseRepository.findOne(id);
    }
}
