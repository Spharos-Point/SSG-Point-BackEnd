package com.spharos.pointapp.event.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 45 , name = "event_name")
    private String eventName; // varchar는 java에서 string

    @Column(nullable = false, length = 500, name = "event_desc")
    private String eventDesc;

    @Column(nullable = false, name = "event_reg_date")
    private LocalDateTime eventRegDate;

    @Column(nullable = false, name = "event_end_date")
    private LocalDateTime eventEndDate;

    @Column(nullable = false, name = "event_update")
    private LocalDateTime eventUpdate;

    @Column(nullable = false, name = "event_type")
    private Integer eventType; // tinyint

    @Column(nullable = false, length = 50, name = "prize_type")
    private String prizeType;

}
