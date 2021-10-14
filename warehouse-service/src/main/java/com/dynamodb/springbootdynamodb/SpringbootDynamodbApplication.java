package com.dynamodb.springbootdynamodb;

import io.awspring.cloud.autoconfigure.context.ContextInstanceDataAutoConfiguration;
import io.awspring.cloud.autoconfigure.context.ContextRegionProviderAutoConfiguration;
import io.awspring.cloud.autoconfigure.context.ContextStackAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
        ContextInstanceDataAutoConfiguration.class, ContextStackAutoConfiguration.class, ContextRegionProviderAutoConfiguration.class
})
public class SpringbootDynamodbApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDynamodbApplication.class, args);
    }

}
