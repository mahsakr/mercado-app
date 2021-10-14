package com.dynamodb.springbootdynamodb.controller;

import com.dynamodb.springbootdynamodb.domain.Product;
import com.dynamodb.springbootdynamodb.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product) {
        return this.productRepository.save(product);
    }

    @PutMapping("{sku}")
    @ResponseStatus(HttpStatus.OK)
    public String update(@RequestBody Product product, @PathVariable("sku") String sku) {
        Product dto = productRepository.findBySku(sku);
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setQuantity(product.getQuantity());
        dto.setTitle(product.getTitle());
        return this.productRepository.update(sku, dto);
    }

    @GetMapping("{sku}")
    @ResponseStatus(HttpStatus.OK)
    public Product get(@PathVariable("sku") String sku) {
        return productRepository.findBySku(sku);
    }
}
