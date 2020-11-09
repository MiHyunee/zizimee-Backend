package com.zizimee.api.pimanager.request.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.zizimee.api.pimanager.enterprise.entity.Enterprise;
import java.time.LocalDate;
import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long>{
    @Query("SELECT p FROM Request p ORDER BY p.id DESC")
    List<Request> findAllDesc();

    @Query(nativeQuery = true,
            value = "select r.CONTENT from Request r WHERE ENTERPRISE_ID = ?1 AND (r.START_DATE BETWEEN ?2 AND ?3)")
    List<String> getContents(Long enterprise_id, LocalDate begin, LocalDate end);

    Long countByEnterpriseIdAndTypeAndStartDateBetween(Enterprise enterprise, LocalDate begin, LocalDate end);
}