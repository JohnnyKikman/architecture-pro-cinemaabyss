package ru.cinemaabyss.events.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.cinemaabyss.events.dto.MovieEvent;
import ru.cinemaabyss.events.dto.UserEvent;
import ru.cinemaabyss.events.dto.PaymentEvent;

@Slf4j
@Component
public class EventConsumer {

    @KafkaListener(topics = "movie-events", groupId = "cinemaabyss-group", containerFactory = "movieKafkaListenerContainerFactory")
    public void receiveMovieEvent(MovieEvent event) {
        log.info("Received MovieEvent: {}", event);
    }

    @KafkaListener(topics = "user-events", groupId = "cinemaabyss-group", containerFactory = "userKafkaListenerContainerFactory")
    public void receiveUserEvent(UserEvent event) {
        log.info("Received UserEvent: {}", event);
    }

    @KafkaListener(topics = "payment-events", groupId = "cinemaabyss-group", containerFactory = "paymentKafkaListenerContainerFactory")
    public void receivePaymentEvent(PaymentEvent event) {
        log.info("Received PaymentEvent: {}", event);
    }
}
