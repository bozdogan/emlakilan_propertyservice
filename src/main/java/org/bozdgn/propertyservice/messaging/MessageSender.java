package org.bozdgn.propertyservice.messaging;


import org.bozdgn.propertyservice.dto.messaging.PropertyMessage;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {
    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;

    public MessageSender(RabbitTemplate rabbitTemplate, Queue queue) {
        this.rabbitTemplate = rabbitTemplate;
        this.queue = queue;
    }

    public void sendPropertyAcceptedMessage(PropertyMessage output) {
        rabbitTemplate.convertAndSend(queue.getName(), output);
    }
}
