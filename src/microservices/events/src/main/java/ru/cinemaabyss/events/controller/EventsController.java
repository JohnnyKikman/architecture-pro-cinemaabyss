package ru.cinemaabyss.events.controller;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.*;
import ru.cinemaabyss.events.dto.*;
import ru.cinemaabyss.events.service.EventProducer;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventsController {

    private final EventProducer eventProducer;

    @PostMapping("/movie")
    public ResponseEntity<EventResponse> createMovieEvent(@RequestBody MovieEvent movieEvent) {
        String eventId = "movie-" + UUID.randomUUID();
        try {
            SendResult<String, Object> result = eventProducer.sendEvent("movie-events", movieEvent);
            Event event = eventProducer.createEvent(eventId, "movie", movieEvent);
            RecordMetadata metadata = result.getRecordMetadata();
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new EventResponse("success", metadata.partition(), metadata.offset(), event));
        } catch (InterruptedException | ExecutionException e) {
            Event event = eventProducer.createEvent(eventId, "movie", movieEvent);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new EventResponse("error", -1, -1, event));
        }
    }

    @PostMapping("/user")
    public ResponseEntity<EventResponse> createUserEvent(@RequestBody UserEvent userEvent) {
        String eventId = "user-" + UUID.randomUUID();
        try {
            SendResult<String, Object> result = eventProducer.sendEvent("user-events", userEvent);
            Event event = eventProducer.createEvent(eventId, "user", userEvent);
            RecordMetadata metadata = result.getRecordMetadata();
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new EventResponse("success", metadata.partition(), metadata.offset(), event));
        } catch (InterruptedException | ExecutionException e) {
            Event event = eventProducer.createEvent(eventId, "user", userEvent);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new EventResponse("error", -1, -1, event));
        }
    }

    @PostMapping("/payment")
    public ResponseEntity<EventResponse> createPaymentEvent(@RequestBody PaymentEvent paymentEvent) {
        String eventId = "payment-" + UUID.randomUUID();
        try {
            SendResult<String, Object> result = eventProducer.sendEvent("payment-events", paymentEvent);
            Event event = eventProducer.createEvent(eventId, "payment", paymentEvent);
            RecordMetadata metadata = result.getRecordMetadata();
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new EventResponse("success", metadata.partition(), metadata.offset(), event));
        } catch (InterruptedException | ExecutionException e) {
            Event event = eventProducer.createEvent(eventId, "payment", paymentEvent);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new EventResponse("error", -1, -1, event));
        }
    }
}
