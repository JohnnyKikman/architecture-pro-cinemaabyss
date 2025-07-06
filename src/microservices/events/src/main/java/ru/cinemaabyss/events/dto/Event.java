package ru.cinemaabyss.events.dto;

import lombok.Data;

import java.time.ZonedDateTime;
import java.util.Map;

@Data
public class Event {
    private String id;
    private String type;
    private ZonedDateTime timestamp;
    private Map<String, Object> payload;
}
