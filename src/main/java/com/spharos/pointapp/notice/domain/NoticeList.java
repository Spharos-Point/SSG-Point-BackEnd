package com.spharos.pointapp.notice.domain;

import com.spharos.pointapp.admin.administrator.domain.Administrator;
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
public class NoticeList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = true, name = "can_view", columnDefinition = "boolean default true")
    private Boolean canView;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Administrator administrator;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "notice_id")
    private Notice notice;

}
