package com.example.WEBBANMOHINH.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date order_date;

    @Column
    private Boolean isPaid;

    @Column(name = "total_amount") // Add the total amount column
    private long totalAmount;

    // Other order properties (e.g., customer information, shipping address, etc.)
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrdersDetail> orderDetails;
}