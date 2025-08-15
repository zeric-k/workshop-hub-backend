package com.workshop.repository;

import com.workshop.model.Space;
import com.workshop.model.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpaceRepository extends JpaRepository<Space, Integer> {
    @Query(
            value =
                    "SELECT * " +
                            "FROM Space s " +
                            "ORDER BY s.id asc OFFSET :offset ROWS FETCH NEXT :pageSize ROW ONLY",
            nativeQuery = true
    )
    List<Space> findPaginatedSpace(
            @Param("offset") int offset,
            @Param("pageSize") int pageSize
    );
}
