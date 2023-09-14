package com.spharos.pointapp.faq.domain;

import com.spharos.pointapp.admin.administrator.domain.Administrator;
import com.spharos.pointapp.user.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FaqList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "administator_id")
    private Administrator administrator;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faq_id")
    private Faq faq;
}