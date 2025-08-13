package com.workshop.service;

import com.workshop.model.Space;
import com.workshop.repository.SpaceRepository;
import com.workshop.repository.WorkshopRepository;
import com.workshop.model.Workshop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public Page<Workshop> getWorkshops(String category, String level, Integer spaceId, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize); // default sort by date DESC handled in query
        return workshopRepository.findWorkshopsWithFilters(category, level, spaceId, pageable);
    }

    public Space createSpace(Space space) {
        return spaceRepository.save(space);
    }


    @Transactional
    public Workshop createWorkshop(Workshop workshop, Integer spaceId) {
        if (spaceId == null) {
            throw new IllegalArgumentException("spaceId must be provided");
        }
        Space space = new Space();
        space.setId(spaceId);

        workshop.setSpaceId(spaceId);
        return workshopRepository.save(workshop);
    }
}

