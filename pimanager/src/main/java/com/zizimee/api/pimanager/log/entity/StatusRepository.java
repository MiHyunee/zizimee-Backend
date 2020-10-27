package com.zizimee.api.pimanager.log.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<ConsentStatus, Long> {
    ConsentStatus findBySignId(Long signId);

}
