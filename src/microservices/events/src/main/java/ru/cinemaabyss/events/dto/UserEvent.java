package ru.cinemaabyss.events.dto;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class UserEvent {
    private int userId;
    private String username;
    private String email;
    private String action;
    private ZonedDateTime timestamp;
}
