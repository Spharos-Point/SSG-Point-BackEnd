package com.spharos.pointapp.board.infrastructure;

import com.spharos.pointapp.board.domain.BoardList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardListRepository extends JpaRepository<BoardList, Long> {
}
