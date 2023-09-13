package com.spharos.pointapp.event.domain;

import com.spharos.pointapp.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

//  생성날짜, 수정날짜 필요하므로 base entity

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EventList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
//    @ManyToOne(fetch = FetchType.LAZY) user 정보에 관한 내용은 토큰이 필요하므로 우선 lazy 빼고 진행

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

//    당첨여부 당첨시 true, 미당첨시 false
    @Column(nullable = false, name = "prize", columnDefinition = "boolean default false")
    private Boolean prize;

}

