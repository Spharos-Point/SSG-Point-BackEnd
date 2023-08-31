package com.spharos.pointapp.faq.infrastructure;

import com.spharos.pointapp.faq.domain.FaqList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FaqListRepository extends JpaRepository<FaqList, Long> {
   List<FaqList> findByUserId(Long userId);
}
