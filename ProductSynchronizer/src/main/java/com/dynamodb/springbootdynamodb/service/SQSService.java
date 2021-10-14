package com.dynamodb.springbootdynamodb.service;

import com.dynamodb.springbootdynamodb.dto.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.messaging.core.QueueMessagingTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class SQSService implements MQService{
    private static final Logger logger = LoggerFactory.getLogger(SQSService.class);

    @Autowired
    private QueueMessagingTemplate queueMessagingTemplate;

    @Value("${cloud.aws.sqs.endpoint}")
    private String sqsEndpoint;

    @Override
    public void pushToMQ(Product product) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(product);
        queueMessagingTemplate.send(sqsEndpoint, MessageBuilder.withPayload(jsonString).build());
        logger.info("Message sent successfully  " + jsonString);
    }
}
