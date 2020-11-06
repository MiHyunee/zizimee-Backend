package com.zizimee.api.pimanager.user.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(nativeQuery = true,
            value = "SELECT p.fcm_Token " +
            "FROM User p " +
            "WHERE p.fcm_Token is not NULL ")
    List<String> getFcmTokens();

    Optional<User> findByUid(String uid);

    User findByName(String name);
}
