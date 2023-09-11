package com.spharos.pointapp.notice.infrastructure;

import com.spharos.pointapp.notice.domain.NoticeList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeListRepository extends JpaRepository<NoticeList, Long> {
    List<NoticeList> findByUserId(Long userId);
}
