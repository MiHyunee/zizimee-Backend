package com.zizimee.api.pimanager.log.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface FormRepository extends JpaRepository<ConsentForm, Long>{
    @Query("SELECT MAX(F.date) FROM ConsentForm F")
    ConsentForm findRecent();

    ConsentForm findByEnterpriseId(Long entId);
}
