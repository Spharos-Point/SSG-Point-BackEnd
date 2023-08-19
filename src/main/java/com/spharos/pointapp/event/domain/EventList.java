package com.spharos.pointapp.event.domain;

import com.spharos.pointapp.user.domain.User;
import jakarta.persistence.*;

@Entity
public class EventList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    private Event event;
}
