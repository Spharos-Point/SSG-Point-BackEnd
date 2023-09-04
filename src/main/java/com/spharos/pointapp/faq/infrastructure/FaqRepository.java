package com.spharos.pointapp.faq.infrastructure;

import com.spharos.pointapp.faq.domain.Faq;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaqRepository extends JpaRepository<Faq, Long> {
}
