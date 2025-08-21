package com.workshop.repository;

import com.workshop.model.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface WorkshopRepository extends JpaRepository<Workshop, Integer> {

    @Query(value = "SELECT w.id, w.title, w.date, w.level, w.category, w.instructor, w.link, w.spaceId, s.space, s.location, w.price  FROM Workshop w inner join Space s on (w.spaceId=s.id) WHERE ((:#{#category == null} = 1) OR w.category = :#{#category}) AND ((:#{#level == null} = 1) OR w.level = :#{#level}) " +
    "AND ((:#{#spaceId == null} = 1) OR w.spaceId = :#{#spaceId}) ORDER BY w.date DESC OFFSET :offset ROWS FETCH NEXT :pageSize ROW ONLY", nativeQuery = true)
    List<Object[]> findWorkshopsWithFilters(@Param("category") String category, @Param("level") String level, @Param("spaceId") Integer spaceId, @Param("offset") int offset, @Param("pageSize") int pageSize);

    @Query(value = "SELECT count(*) FROM Workshop w WHERE ((:#{#category == null} = 1) OR w.category = :#{#category}) AND ((:#{#level == null} = 1) OR w.level = :#{#level}) " +
                            "AND ((:#{#spaceId == null} = 1) OR w.spaceId = :#{#spaceId}) ", nativeQuery = true)
    Integer countWorkshopsWithFilters(@Param("category") String category, @Param("level") String level, @Param("spaceId") Integer spaceId);

    @Modifying
    @Transactional
    @Query(value = "Insert into Workshop (title, date, level, category, instructor, spaceId, link, price) values " +
            "(:#{#workshop.title}, :#{#workshop.date}, :#{#workshop.level}, " +
            ":#{#workshop.category}, :#{#workshop.instructor}, :#{#workshop.spaceId}, :#{#workshop.link}, :#{#workshop.price})", nativeQuery = true)
     int insertWorkshop(@Param("workshop") Workshop workshop);
}
