package com.zizimee.api.pimanager.report.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ReportRepository extends JpaRepository<Report, Long>{
    @Query("SELECT p FROM Report p ORDER BY p.id DESC")
    List<Report> findAllDesc();
}

