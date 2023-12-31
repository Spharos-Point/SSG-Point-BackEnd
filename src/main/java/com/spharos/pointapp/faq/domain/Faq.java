package com.spharos.pointapp.faq.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Faq {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false, length = 45, name = "title")
    private String title;

    @Column(nullable = false, length = 100, name = "context")
    private String context;
}
