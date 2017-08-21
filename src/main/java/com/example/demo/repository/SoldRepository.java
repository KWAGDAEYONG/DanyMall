package com.example.demo.repository;

import com.example.demo.model.Sold;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SoldRepository extends JpaRepository<Sold, Long> {
}
