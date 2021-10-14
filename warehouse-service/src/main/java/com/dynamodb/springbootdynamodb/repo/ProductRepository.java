package com.dynamodb.springbootdynamodb.repo;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.dynamodb.springbootdynamodb.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {
    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public Product save(Product product) {
        dynamoDBMapper.save(product);
        return product;
    }

    public Product findBySku(String sku) {
        return dynamoDBMapper.load(Product.class, sku);
    }

    public List<Product> findAll() {
        return dynamoDBMapper.scan(Product.class, new DynamoDBScanExpression());
    }

    public String update(String sku, Product product) {
        dynamoDBMapper.save(product, new DynamoDBSaveExpression()
                .withExpectedEntry("sku", new ExpectedAttributeValue(
                        new AttributeValue().withS(sku)
                )));
        return sku;
    }

    public String delete(String sku) {
        dynamoDBMapper.delete(findBySku(sku));
        return "Product deleted successfully" + sku;
    }
}
