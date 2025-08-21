package com.workshop.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "workshop")
public class Workshop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "level", length = 50)
    private String level;

    @Column(name = "category", length = 100)
    private String category;

    @Column(name = "instructor", length = 100)
    private String instructor;

    @Column(name = "spaceId")
    private Integer spaceId;

    @Column(name = "link", length = 255)
    private String link;

    @Column(name = "price")
    private Integer price;


    @Column(name = "createdAt", columnDefinition = "DATETIME DEFAULT GETDATE()")
    private LocalDateTime createdAt;

    @Column(name = "updatedAt", columnDefinition = "DATETIME DEFAULT GETDATE()")
    private LocalDateTime updatedAt;

    @Column(name = "updatedBy", length = 50, columnDefinition = "VARCHAR(50) DEFAULT 'SYSTEM'")
    private String updatedBy;

    // Constructors
    public Workshop() {}

    public Workshop(String title, LocalDate date, Integer spaceId) {
        this.title = title;
        this.date = date;
        this.spaceId = spaceId;
        this.updatedBy = "SYSTEM";
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
