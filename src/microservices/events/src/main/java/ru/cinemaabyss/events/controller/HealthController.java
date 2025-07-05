package ru.cinemaabyss.events.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/events/health")
public class HealthController {

    @GetMapping
    public ResponseEntity<Map<String, Boolean>> getEventsServiceHealth() {
        return ResponseEntity.ok(Map.of("status", true));
    }
}