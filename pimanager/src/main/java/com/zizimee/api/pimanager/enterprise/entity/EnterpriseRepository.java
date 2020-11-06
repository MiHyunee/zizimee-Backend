package com.zizimee.api.pimanager.enterprise.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EnterpriseRepository extends JpaRepository<Enterprise, Long> {
    Optional<Enterprise> findByRegisterNmb(String registerNmb);
    Optional<Enterprise> findBySignUpId(String signUpId);
    Optional<Enterprise> findByRegisterNmbAndSignUpId(String registerNmb, String signUpId);

    Enterprise findByName(String name);

    @Query(nativeQuery = true,
            value = "SELECT p.fcm_Token " +
                    "FROM Enterprise p " +
                    "WHERE p.fcm_Token is not NULL ")
    List<String> getFcmTokens();
}
