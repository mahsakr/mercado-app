package com.dynamodb.springbootdynamodb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class Product {
    private String sku;
    private String title;
    private String description;
    private int price;
    private int quantity;

}
