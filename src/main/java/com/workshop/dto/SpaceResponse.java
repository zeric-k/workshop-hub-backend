package com.workshop.dto;

import com.workshop.model.Space;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SpaceResponse {
    private Integer totalCount;
    private List<Space> spaces;
}
