package com.dynamodb.springbootdynamodb.service;

import com.dynamodb.springbootdynamodb.dto.Product;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface MQService {
    void pushToMQ(Product product) throws JsonProcessingException;
}
