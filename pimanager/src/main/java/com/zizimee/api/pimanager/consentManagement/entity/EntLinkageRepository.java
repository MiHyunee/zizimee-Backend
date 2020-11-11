package com.zizimee.api.pimanager.consentManagement.entity;

import com.zizimee.api.pimanager.enterprise.entity.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EntLinkageRepository extends JpaRepository<EntLinkage, Long> {
    @Query(nativeQuery = true,
            value = "SELECT * FROM EntLinakge E" +
                    "WHERE E.Id_Enterprise = ?1 AND E.Uid = ?2")
    Optional<EntLinkage> findByEntIdANDUid(Enterprise entId, String uid);
}
