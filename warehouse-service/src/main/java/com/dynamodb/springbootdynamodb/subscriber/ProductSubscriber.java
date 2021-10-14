package com.dynamodb.springbootdynamodb.subscriber;

import com.dynamodb.springbootdynamodb.domain.Product;
import com.dynamodb.springbootdynamodb.repo.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductSubscriber {
    private static final Logger logger = LoggerFactory.getLogger(ProductSubscriber.class);

    @Autowired
    private ProductRepository productRepository;

    @SqsListener("products-queue")
    public void receiveMessage(String jsonString) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Product product = mapper.readValue(jsonString, Product.class);
            productRepository.save(product);
            logger.info("Message Received using SQS Listner " + product);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
