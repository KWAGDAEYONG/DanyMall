package com.example.demo.api;

import com.example.demo.repository.MerchandiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MerchandiseApi {

    @Autowired
    MerchandiseRepository merchandiseRepository;

}
