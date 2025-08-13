package com.workshop.repository;

import com.workshop.model.Workshop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkshopRepository extends JpaRepository<Workshop, Integer> {

    @Query(
            value = "SELECT * FROM workshop w " +
                    "WHERE (:category IS NULL OR w.category = :category) " +
                    "AND (:level IS NULL OR w.level = :level) " +
                    "AND (:spaceId IS NULL OR w.spaceId = :spaceId) " +
                    "ORDER BY w.date DESC",
            nativeQuery = true
    )
    Page<Workshop> findWorkshopsWithFilters(
            @Param("category") String category,
            @Param("level") String level,
            @Param("spaceId") Integer spaceId,
            Pageable pageable
    );
}
