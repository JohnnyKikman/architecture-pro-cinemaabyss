package ru.cinemaabyss.events.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class MovieEvent {
    private int movieId;
    private String title;
    private String action;
    private Integer userId;
    private BigDecimal rating;
    private List<String> genres;
    private String description;
}
