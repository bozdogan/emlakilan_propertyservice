package org.bozdgn.propertyservice.messaging;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${app.report-queue}")
    private String queueName;

    @Bean
    public Queue queue() {
        return new org.springframework.amqp.core.Queue(queueName, true);
    }
}
