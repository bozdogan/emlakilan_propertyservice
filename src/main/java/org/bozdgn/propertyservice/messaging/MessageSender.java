package org.bozdgn.propertyservice.messaging;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.bozdgn.propertyservice.dto.messaging.PropertyMessage;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageSender {
    private final RabbitTemplate rabbitTemplate;
    private final Queue reportQueue;

    public MessageSender(RabbitTemplate rabbitTemplate, @Qualifier("reportQueue") Queue reportQueue) {
        this.rabbitTemplate = rabbitTemplate;
        this.reportQueue = reportQueue;
    }

    public void sendPropertyInfoMessage(PropertyMessage output) {
        try {
            ObjectMapper json = new ObjectMapper();
            json.registerModule(new JavaTimeModule());
            String payload = json.writeValueAsString(output);
            rabbitTemplate.convertAndSend(reportQueue.getName(), payload);
        } catch (JsonProcessingException e) {
            log.error("Error on serializing to JSON: {}", e.getMessage());
        }
    }
}
