package com.zizimee.api.pimanager.log.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ConsentFormRepository extends JpaRepository<ConsentForm, Long> {

    Optional findById(Long formId);

    @Query(nativeQuery = true,
            value = "SELECT * " +
            "FROM ConsentForm F " +
            "WHERE F.Enterprise_Id = ?1 " +
            "ORDER BY F.date DESC ")
    Optional<ConsentForm> findRecentByEntId(Long entId);}
