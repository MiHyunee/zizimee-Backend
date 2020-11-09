package com.zizimee.api.pimanager.log.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsentFormRepository extends JpaRepository<ConsentForm, Long> {

    ConsentForm findByFormId(ConsentForm formId);
}
