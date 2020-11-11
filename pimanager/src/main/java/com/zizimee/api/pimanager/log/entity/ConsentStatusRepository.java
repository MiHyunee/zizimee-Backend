package com.zizimee.api.pimanager.log.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ConsentStatusRepository extends JpaRepository<ConsentStatus, Long> {

    @Query(nativeQuery = true,
            value = "SELECT * " +
            "FROM Consent_Status " +
            "ORDER BY ABS(DATEDIFF(day, ?1, date)) " +
            "LIMIT 1")
    Optional<ConsentStatus> findByDate(LocalDate date);

    @Query(nativeQuery = true,
            value = "SELECT * FROM Consent_Status S" +
                    "WHERE S.Sign_Id = ?1")
    Optional<ConsentStatus> findBySignId(Long signId);
}
