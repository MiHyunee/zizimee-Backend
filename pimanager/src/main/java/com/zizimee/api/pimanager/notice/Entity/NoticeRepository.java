package com.zizimee.api.pimanager.notice.Entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long>{
}
