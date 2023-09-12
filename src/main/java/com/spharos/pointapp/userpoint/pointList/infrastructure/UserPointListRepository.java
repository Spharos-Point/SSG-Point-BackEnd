package com.spharos.pointapp.userpoint.pointList.infrastructure;

import com.spharos.pointapp.userpoint.pointList.domain.UserPointList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserPointListRepository extends JpaRepository<UserPointList, Long> {

    Optional<UserPointList> findTopByUuidOrderByCreateAtDesc(String uuid);
    UserPointList findTopByPointIdOrderByCreateAtDesc(Long point_id);

}
