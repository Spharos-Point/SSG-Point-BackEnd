package com.spharos.pointapp.notice.infrastructure;

import com.spharos.pointapp.notice.domain.NoticeList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NoticeListRepository extends JpaRepository<NoticeList, Long>{
    Optional<NoticeList> findByNoticeId(Long noticeId);
    List<NoticeList> findAllByCanViewTrue();
}
