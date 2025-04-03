package DoomOrMoon.api_doom_or_moon.repositories;

import DoomOrMoon.api_doom_or_moon.models.Bitcoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface BitcoinRepository extends JpaRepository<Bitcoin, Long> {
    @Query("SELECT b FROM Bitcoin b WHERE b.createdAt BETWEEN :start AND :end ORDER BY b.createdAt DESC LIMIT 1")
    Optional<Bitcoin> findByDate(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query("SELECT e FROM Bitcoin e WHERE e.id = (SELECT MAX(e2.id) FROM Bitcoin e2)")
    Optional<Bitcoin> findUltimoRegistro();

}
