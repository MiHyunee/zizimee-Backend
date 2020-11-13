package com.zizimee.api.pimanager.report.entity;

import com.zizimee.api.pimanager.log.entity.ConsentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ReportRepository extends JpaRepository<Report, Long>{
    @Query(nativeQuery = true,
            value = "SELECT * FROM ConsentStatus S" +
                    "WHERE S.Form_Id = ?1")
    List<ConsentStatus> findByFormId(Long formId);
}

