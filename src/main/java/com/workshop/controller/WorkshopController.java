package com.workshop.controller;

import com.workshop.dto.Response;
import com.workshop.dto.WorkshopRequest;
import com.workshop.model.Space;
import com.workshop.model.Workshop;
import com.workshop.service.WorkshopService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class WorkshopController {

    private final WorkshopService workshopService;

    public WorkshopController(WorkshopService workshopService) {
        this.workshopService = workshopService;
    }

    @GetMapping("/workshops")
    public ResponseEntity getWorkshops(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String level,
            @RequestParam(required = false) Integer spaceId,
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) Long userId
    ) {
        return ResponseEntity.ok(Response.builder().payload(workshopService.getWorkshops(category, level, spaceId, pageNo, pageSize, userId))
                .status("SUCCESS").build());
    }

    @GetMapping("/spaces")
    public ResponseEntity getWorkshops(
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        return ResponseEntity.ok(Response.builder().payload(workshopService.getSpaces(pageNo, pageSize))
                .status("SUCCESS").build());
    }

    @PostMapping("/spaces")
    public ResponseEntity<Space> createSpace(@RequestBody Space space) {
        Space savedSpace = workshopService.createSpace(space);
        return ResponseEntity.ok(savedSpace);
    }

    // API to create a Workshop
    @PostMapping("/workshops")
    public ResponseEntity createWorkshop(@RequestBody WorkshopRequest request) {
        Workshop workshop = new Workshop();
        workshop.setTitle(request.getTitle());
        workshop.setDate(request.getDate());
        workshop.setLevel(request.getLevel());
        workshop.setCategory(request.getCategory());
        workshop.setInstructor(request.getInstructor());
        workshop.setLink(request.getLink());
        workshop.setPrice(request.getPrice());

        if(workshopService.createWorkshop(workshop, request.getSpaceId()) >= 1) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.internalServerError().build();
    }
}
