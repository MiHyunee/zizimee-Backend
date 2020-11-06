package com.zizimee.api.pimanager.log.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface LogRepository extends JpaRepository<Log, Long>{
    @Query("SELECT p FROM Log p ORDER BY p.id DESC")
    List<Log> findAllDesc();
}
