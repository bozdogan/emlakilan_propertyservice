package org.bozdgn.propertyservice.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.bozdgn.propertyservice.dto.messaging.UpdateRequestMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageReceiver {
    @RabbitListener(queues = "${app.property-queue}")
    public void receive(@Payload String payload) {
        try {
            ObjectMapper json = new ObjectMapper();
            json.registerModule(new JavaTimeModule());
            UpdateRequestMessage updateRequest = json.readValue(payload, UpdateRequestMessage.class);
            log.info(updateRequest.toString());
        } catch (JsonProcessingException e) {
            log.error("Error on deserializing to JSON: {}", e.getMessage());
        }
    }
}
