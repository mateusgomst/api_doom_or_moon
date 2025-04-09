package DoomOrMoon.api_doom_or_moon.repositories;

import DoomOrMoon.api_doom_or_moon.models.Bitcoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface BitcoinRepository extends JpaRepository<Bitcoin, Long> {

    @Query("SELECT b FROM Bitcoin b")
    List<Bitcoin> findAllBitcoins();

    @Modifying
    @Transactional
    @Query(value = "ALTER SEQUENCE bitcoin_id_seq RESTART WITH 1", nativeQuery = true)
    void resetAutoIncrement();

}
