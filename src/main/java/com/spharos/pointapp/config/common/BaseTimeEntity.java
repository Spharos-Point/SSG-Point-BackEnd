package com.spharos.pointapp.config.common;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.EntityListeners;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass // 멤버 변수가 컬럼이 되도록 해줌.
@EntityListeners(AuditingEntityListener.class) // 변경되었을 때 자동으로 기록
public abstract class BaseTimeEntity {

    @CreatedDate // 최초 생성 시점
    @Column(updatable = false)
    private LocalDateTime createAt;

    @LastModifiedDate  // 마지막 변경 시점
    private LocalDateTime updateAt;

}