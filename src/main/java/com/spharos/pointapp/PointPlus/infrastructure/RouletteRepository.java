package com.spharos.pointapp.PointPlus.infrastructure;

import com.spharos.pointapp.PointPlus.domain.Attend;
import com.spharos.pointapp.PointPlus.domain.Roulette;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouletteRepository extends JpaRepository<Roulette, Long> {
}
