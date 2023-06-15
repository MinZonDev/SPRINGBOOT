package com.example.WEBBANMOHINH.models;

import lombok.Data;

@Data
public class CartItem {

    //Product info
    private Integer id;
    private String name;
    private String image;

    private long price;

    //Quantity
    private int quantity;

    public long getAmount() {
        return (long) quantity * price;
    }
}
