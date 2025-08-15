package com.workshop.service;

import com.workshop.dto.SpaceResponse;
import com.workshop.dto.WorkshopResponse;
import com.workshop.model.Space;
import com.workshop.repository.SpaceRepository;
import com.workshop.repository.WorkshopRepository;
import com.workshop.model.Workshop;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

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
        return WorkshopResponse.builder().workshops(workshopRepository.findWorkshopsWithFilters(category, level, spaceId, (pageNo - 1)*pageSize, pageSize))
                .totalCount(totalCount).build();
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

