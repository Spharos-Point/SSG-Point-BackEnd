package com.spharos.pointapp.store.domain;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;


    @Column(nullable = false, name = "use_place")
    @Convert(converter = StoreListConverter.class)
    private StoreList storeList;
}
