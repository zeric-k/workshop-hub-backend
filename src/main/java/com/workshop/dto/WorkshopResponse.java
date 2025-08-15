package com.workshop.dto;

import com.workshop.model.Workshop;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class WorkshopResponse {
    private Integer totalCount;
    private List<WorkshopWithSpaceDTO> workshops;
}
