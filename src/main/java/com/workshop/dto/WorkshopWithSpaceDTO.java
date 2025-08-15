package com.workshop.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class WorkshopWithSpaceDTO {
    private Integer id;
    private String title;
    private Date date;
    private String level;
    private String category;
    private String instructor;
    private String link;
    private Integer spaceId;
    private String space;
    private String location;

    public WorkshopWithSpaceDTO(Integer workshopId, String title, Date date, String level,
                                String category, String instructor, String link,
                                Integer spaceId, String space, String location) {
        this.id = workshopId;
        this.title = title;
        this.date = date;
        this.level = level;
        this.category = category;
        this.instructor = instructor;
        this.link = link;
        this.spaceId = spaceId;
        this.space = space;
        this.location = location;
    }

    // Getters and setters
    // ...
}
