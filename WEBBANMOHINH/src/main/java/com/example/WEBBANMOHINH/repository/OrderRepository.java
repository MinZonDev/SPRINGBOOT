package com.example.WEBBANMOHINH.repository;

import com.example.WEBBANMOHINH.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long> {
}
