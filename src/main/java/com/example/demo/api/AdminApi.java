package com.example.demo.api;

import com.example.demo.model.Admin;
import com.example.demo.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminApi {

    @Autowired
    AdminRepository adminRepository;

    public List<Admin> getAdminList(){
        return adminRepository.findAll();
    }
}
