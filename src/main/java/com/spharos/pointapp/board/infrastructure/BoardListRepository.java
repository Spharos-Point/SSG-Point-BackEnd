package com.spharos.pointapp.board.infrastructure;

import com.spharos.pointapp.board.domain.BoardList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardListRepository extends JpaRepository<BoardList, Long> {
    List<BoardList> findByAdministratorId(Long administratorId);
}
