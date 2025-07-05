package ru.cinemaabyss.events.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import ru.cinemaabyss.events.dto.Event;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public SendResult<String, Object> sendEvent(String topic, Object event) throws ExecutionException, InterruptedException {
        log.info("Sending message {} to topic {}", event, topic);
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, event);
        return future.get(); // blocks until send is done
    }

    public Event createEvent(String id, String type, Object payload) {
        Map<String, Object> payloadMap = new HashMap<>();
        for (var field : payload.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                payloadMap.put(field.getName(), field.get(payload));
            } catch (IllegalAccessException e) {
                log.warn("Failed to extract field: {}", field.getName(), e);
            }
        }

        Event event = new Event();
        event.setId(id);
        event.setType(type);
        event.setTimestamp(ZonedDateTime.now());
        event.setPayload(payloadMap);
        return event;
    }
}