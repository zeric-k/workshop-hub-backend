package com.workshop.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class WorkshopRequest {
    private String title;
    private LocalDate date;
    private String level;
    private String category;
    private String instructor;
    private String link;
    private Integer spaceId; // IMPORTANT for mapping to space
}
