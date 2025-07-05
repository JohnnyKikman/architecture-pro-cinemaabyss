package ru.cinemaabyss.events.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventResponse {
    private String status;
    private int partition;
    private long offset;
    private Event event;
}
