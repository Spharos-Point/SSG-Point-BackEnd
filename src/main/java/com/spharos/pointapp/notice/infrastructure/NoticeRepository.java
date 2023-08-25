package com.spharos.pointapp.notice.infrastructure;

import com.spharos.pointapp.notice.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
//    Notice save(Notice build);
}
