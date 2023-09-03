package com.spharos.pointapp.board.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Board{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer categoryId;
    @Column(nullable = false, length = 45 , name = "title")
    private String title;
    @Column(nullable = false, length = 500 , name = "context")
    private String context;

    public void createBoard(String title, String context){
        this.title = title;
        this.context = context;
    }
//    todo : BaseEntity extends and be create BaseEntity.
}
