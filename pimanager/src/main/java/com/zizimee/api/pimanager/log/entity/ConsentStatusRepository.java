package com.zizimee.api.pimanager.log.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ConsentStatusRepository extends JpaRepository<ConsentStatus, Long> {

    @Query(nativeQuery = true,
            value = "SELECT * " +
            "FROM consentStatus c " +
            "ORDER BY ABS(DATEDIFF(date, c.date)) " +
            "LIMIT 1")
    ConsentStatus findByDate(LocalDate date);

    ConsentStatus findBySignId(Long signId);
}
