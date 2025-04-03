package DoomOrMoon.api_doom_or_moon.repositories;

import DoomOrMoon.api_doom_or_moon.models.Bitcoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface BitcoinRepository extends JpaRepository<Bitcoin, Long> {

    // Retorna o PRIMEIRO registro (ordenado por ID crescente)
    Optional<Bitcoin> findTopByOrderByIdAsc();

}
