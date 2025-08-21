package com.workshop.service;

import com.workshop.dto.SpaceResponse;
import com.workshop.dto.WorkshopResponse;
import com.workshop.dto.WorkshopWithSpaceDTO;
import com.workshop.model.Space;
import com.workshop.model.Workshop;
import com.workshop.repository.SpaceRepository;
import com.workshop.repository.WorkshopRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class WorkshopService {

    private final WorkshopRepository workshopRepository;
    private final SpaceRepository spaceRepository;
    private final EntityManager em;

    public WorkshopService(WorkshopRepository workshopRepository, SpaceRepository spaceRepository, EntityManager em) {
        this.workshopRepository = workshopRepository;
        this.spaceRepository = spaceRepository;
        this.em = em;
    }

    public WorkshopResponse getWorkshops(String category, String level, Integer spaceId, int pageNo, int pageSize) {

        Integer totalCount = workshopRepository.countWorkshopsWithFilters(category, level, spaceId);
        return WorkshopResponse.builder().workshops(mapResult(category, level, spaceId, pageNo, pageSize))
                .totalCount(totalCount).build();
    }

    private List<WorkshopWithSpaceDTO> mapResult(String category, String level, Integer spaceId, int pageNo, int pageSize) {
        List<WorkshopWithSpaceDTO> dtos = new ArrayList<>();
        for (Object[] row : workshopRepository.findWorkshopsWithFilters(category, level, spaceId, (pageNo - 1)*pageSize, pageSize)) {
            dtos.add(new WorkshopWithSpaceDTO(
                    (Integer) row[0],
                    (String) row[1],
                    (Date) row[2],
                    (String) row[3],
                    (String) row[4],
                    (String) row[5],
                    (String) row[6],
                    (Integer) row[7],
                    (String) row[8],
                    (String) row[9],
                    (Integer) row[10]
            ));
        }
        return dtos;
    }



    public SpaceResponse getSpaces(int pageNo, int pageSize) {

        return SpaceResponse.builder().spaces(spaceRepository.findPaginatedSpace((pageNo - 1)*pageSize, pageSize))
                .totalCount((int) spaceRepository.count()).build();
    }

    public Space createSpace(Space space) {
        return spaceRepository.save(space);
    }


    @Transactional
    public int createWorkshop(Workshop workshop, Integer spaceId) {
        if (spaceId == null) {
            throw new IllegalArgumentException("spaceId must be provided");
        }
        Space space = new Space();
        space.setId(spaceId);

        workshop.setSpaceId(spaceId);
        return workshopRepository.insertWorkshop(workshop);
    }
}

