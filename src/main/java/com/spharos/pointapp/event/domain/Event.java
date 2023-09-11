package com.spharos.pointapp.event.domain;

import com.spharos.pointapp.config.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

//  생성날짜, 수정날짜 필요하므로 base entity

@Entity
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Event extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false, length = 45 , name = "event_name")
    private String eventName; // varchar는 java에서 string

    @Column(nullable = false, name = "reg_date")
    private String regDate;

    @Column(nullable = false, name = "end_date")
    private String endDate;

    @Column(nullable = false, length = 500, name = "event_desc")
    private String eventDesc;

    @Column(nullable = false, name = "event_type")
    private Integer eventType; // tinyint

    @Column(nullable = false, length = 50, name = "prize_type")
    private String prizeType;

//    img url을 받음
    @Column(nullable = false, length = 50, name = "img")
    private String img;

}
