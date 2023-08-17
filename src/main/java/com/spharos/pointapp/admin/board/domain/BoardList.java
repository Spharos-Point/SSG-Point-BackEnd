package com.spharos.pointapp.admin.board.domain;

import com.spharos.pointapp.admin.administrator.domain.Administrator;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class BoardList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Administrator administrator;
    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;
}
