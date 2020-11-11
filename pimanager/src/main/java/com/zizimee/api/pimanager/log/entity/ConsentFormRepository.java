package com.zizimee.api.pimanager.log.entity;

import com.zizimee.api.pimanager.enterprise.entity.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsentFormRepository extends JpaRepository<ConsentForm, Long> {

    ConsentForm findByFormId(ConsentForm formId);
    ConsentForm findByEnterpriseId(Enterprise enterpriseId);
}
